package rekkursion.view.prac.probpage

import rekkursion.enumerate.PracticeType

class SpellingPage(numOfProblems: Int): ProblemPage(PracticeType.SPELLING, numOfProblems) {
    init {
        // determine the problems according to the type & the number of problems
        generateProblemsAndShowTheFirst()
    }

    /* ======================================== */

    override fun generateProblemsAndShowTheFirst() {

    }

    override fun showNextProblem() {

    }
}