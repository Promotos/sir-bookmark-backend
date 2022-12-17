package de.promotos.sirbookmark.business

class ChallengeGenerator {

    val challenges: List<Pair<String, Int>> = listOf(
        Pair("How may characters 'o' are in 'Hello World'", 2),
        Pair("What is '4+2'", 6),
        Pair("What is '11-3'", 8),
        Pair("Count the '1' in '100110'", 3)
    )


    fun newChallenge(): Pair<String, Int> {
        return challenges.shuffled().take(1)[0]
    }

}