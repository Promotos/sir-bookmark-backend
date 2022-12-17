package de.promotos.sirbookmark.controller

import de.promotos.sirbookmark.dto.NewUser
import de.promotos.sirbookmark.entity.UserAccount
import de.promotos.sirbookmark.entity.UserAccountRepository
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.util.stream.Collectors


@Controller
class CreateUserController : BaseController() {

    @Autowired
    private lateinit var userAccountRepo: UserAccountRepository

    @Autowired
    private lateinit var userDetailManager: UserDetailsManager

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @GetMapping("/create_user")
    fun createUserView(model: Model): String {
        model.addAttribute("new_user", NewUser())
        return "create_user"
    }

    @PostMapping("/do_create_user")
    @Transactional
    fun createNewUserFunction(@Valid newUser: NewUser, errors: Errors, model: Model): String {
        model.addAttribute("new_user", newUser)

        if (errors.hasErrors()) {
            model.addAttribute(
                "errors",
                errors.allErrors.stream().map { error -> error.defaultMessage }.collect(Collectors.toUnmodifiableList()).asReversed()
            )
            return "create_user"
        }

        if (!newUser.password.equals(newUser.passwordRepeat)) {
            model.addAttribute("errors", listOf("Entered passwords does not match."))
            return "create_user"
        }

        if (!newUser.callengeUserAnswer.equals(newUser.challengeCorrectAnswer)) {
            model.addAttribute("errors", listOf("Challenge answer is not correct."))
            return "create_user"
        }

        val existingUser = userAccountRepo.findById(newUser.userName)
        if (existingUser.isPresent || userDetailManager.userExists(newUser.userName)) {
            model.addAttribute("errors", listOf("Username '${newUser.userName}' is already taken."))
            return "create_user"
        }

        val udmNewUser = User.builder()
            .username(newUser.userName)
            .password(passwordEncoder.encode(newUser.password))
            .roles("USER")
            .build()
        val newUserAccount = UserAccount(username = newUser.userName, email = newUser.email)
        userDetailManager.createUser(udmNewUser)
        userAccountRepo.save(newUserAccount)
        model.addAttribute("errors", emptyList<String>())

        return "login"
    }

}