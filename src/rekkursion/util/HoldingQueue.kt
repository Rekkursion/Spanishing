package rekkursion.util

import java.util.*

class HoldingQueue<T>(holdingSize: Int) {
    // the max holding size of this holding-queue
    private val mHoldingSize = holdingSize

    // the holding-queue
    private val mQueue: Queue<T> = LinkedList<T>()

    // the current size of the queue
    private var mSize = 0

    /* ======================================== */

    // push a data into this holding-queue
    fun push(data: T) {
        mQueue.offer(data)
        ++mSize
        if (mSize > mHoldingSize)
            mQueue.poll()
    }

    // check if this holding-queue currently holds a certain data
    fun has(data: T): Boolean = mQueue.contains(data)
}