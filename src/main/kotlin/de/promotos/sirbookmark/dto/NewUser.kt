package de.promotos.sirbookmark.dto

import de.promotos.sirbookmark.business.ChallengeGenerator
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class NewUser(
    @get: NotBlank(message = "{newUser.userName.required}")
    @get: Size(min = 3, max = 15, message = "{newUser.userName.size}")
    val userName: String = "",

    @get: NotBlank(message = "{newUser.email.required}")
    @get: Email(message = "{newUser.email.format}")
    val email: String = "",

    @get: NotBlank(message = "{newUser.password.required}")
    @get: Size(min = 5, max = 20, message = "{newUser.password.size}")
    val password: String = "",

    @get: NotBlank(message = "{newUser.passwordRepeat.required}")
    val passwordRepeat: String = "",
    var challengeDesc: String = "",

    @get: Positive(message = "{newUser.callengeUserAnswer.required}")
    var callengeUserAnswer: Int = 0,
    var challengeCorrectAnswer: Int = 0
) {
    init {
        if (challengeCorrectAnswer == 0) {
            val challenge = ChallengeGenerator().newChallenge()
            challengeDesc = challenge.first
            challengeCorrectAnswer = challenge.second
        }
    }

}