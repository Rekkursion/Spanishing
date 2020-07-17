package rekkursion.manager

import rekkursion.model.problem.Problem

object StatisticsManager {
    // the statistics report of a list of problems
    class Report(problems: ArrayList<Problem>) {
        // the total number of problems
        private val mNumOfTotal = problems.size
        val totalNum get() = mNumOfTotal

        // those correct-answered vocabularies
        private val mCorrect = problems.filter { it.ansResult.isCorrect() }.map { it.copy() }
        val correctVocs get() = mCorrect
        val numOfCorrect get() = correctVocs.size

        // those wrong-answered vocabularies
        private val mWrong = problems.filter { it.ansResult.isWrong() }.map { it.copy() }
        val wrongVocs get() = mWrong
        val numOfWrong get() = wrongVocs.size

        // those no answered vocabularies
        private val mNoAnswered = problems.filter { it.ansResult.isNoAnswered() }.map { it.copy() }
        val noAnsweredVocs get() = mNoAnswered
        val numOfNoAnswered get() = noAnsweredVocs.size

        // the correct rate
        val mCorrectRate get() = numOfCorrect.toDouble() / totalNum.toDouble()
        val mCorrectRateWithPercentage get() = String.format("%.2f%%", mCorrectRate * 100.0)
    }

    /* ======================================== */

    // create a statistics report through the passed problem-list
    fun hacer(problems: ArrayList<Problem>): Report = Report(problems)
}