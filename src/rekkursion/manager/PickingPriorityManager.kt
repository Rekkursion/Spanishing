package rekkursion.manager

import rekkursion.enumerate.PickingScope
import rekkursion.model.Vocabulary
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.min

object PickingPriorityManager {
    // the priority-queue prioritized by the weights of vocabularies
    private val mPQ = PriorityQueue<PrioritizedVocabulary>()

    // the average weight among all vocabularies
    private var mAvg = 1.0

    // the decreasing factor applied when a to-be-picked vocabulary passes the picking test
    private const val decreasingFactor = 0.5

    // the threshold of the average weight to increase the lowest-weighted vocabulary
    private const val avgThreshold = 0.5

    init {
        VocManager.copiedVocList.forEach { mPQ.offer(PrioritizedVocabulary(it)) }
    }

    /* ======================================== */

    // randomly but NOT uniformly pick a vocabulary
    fun pickVoc(scope: PickingScope): Vocabulary {
        val collectedList = VocManager.copiedCollectedList
        val arr = mPQ.toTypedArray()
        val size = arr.size
        while (true) {
            val idx = ThreadLocalRandom.current().nextInt(0, size)
            if (scope == PickingScope.ALL_VOCS || collectedList.contains(arr[idx].voc))
                if (arr[idx].check()) {
                    return arr[idx].voc
                }
        }
    }

    // set the collected-status of a certain vocabulary
    fun setCollected(voc: Vocabulary, isCollected: Boolean) {
        mPQ.filter { it.voc == voc }.getOrNull(0)?.let {
            it.isCollected = isCollected
        }
    }

    /* ======================================== */

    // the vocabulary with a prioritized weight
    private class PrioritizedVocabulary(voc: Vocabulary): Comparable<PrioritizedVocabulary> {
        // the vocabulary
        private val mVoc = voc.copy()
        val voc get() = mVoc.copy()
        var isCollected get() = mVoc.isCollected; set(value) { mVoc.isCollected = value }

        // the weight used when picking vocabularies
        private var mWeight = 1.0

        /* ======================================== */

        // the picking test
        fun check(): Boolean = if (mWeight >= ThreadLocalRandom.current().nextDouble()) {
            var prevWeight = mWeight
            mWeight *= decreasingFactor
            mAvg = ((mAvg * mPQ.size) - prevWeight + mWeight) / mPQ.size
            if (mAvg < avgThreshold) {
                val v = mPQ.poll()
                //if (v.mWeight >= 1.0)
                    //break
                prevWeight = v.mWeight
                v.mWeight = min(1.0, v.mWeight * (1.0 / decreasingFactor))
                mPQ.offer(v)
                mAvg = ((mAvg * mPQ.size) - prevWeight + v.mWeight) / mPQ.size
            }
            true
        } else false

        /* ======================================== */

        override fun compareTo(other: PrioritizedVocabulary): Int = when {
            mWeight < other.mWeight -> -1
            mWeight > other.mWeight -> 1
            else -> (if (ThreadLocalRandom.current().nextDouble() < 0.5) -1 else 1)
        }
    }
}