package de.promotos.sirbookmark.dto

import de.promotos.sirbookmark.business.ChallengeGenerator

data class NewUser(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val passwordRepeat: String = "",
    var challengeDesc: String = "",
    var callengeUserAnswer: Int = 0,
    var challengeCorrectAnswer: Int = 0
) {
    init {
        val challenge = ChallengeGenerator().newChallenge()
        challengeDesc = challenge.first
        challengeCorrectAnswer = challenge.second
    }

}