package de.promotos.sirbookmark.controller

import de.promotos.sirbookmark.bean.UserBean
import de.promotos.sirbookmark.dto.NewUser
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.util.stream.Collectors


@Controller
class CreateUserController : BaseController() {

    @Autowired
    private lateinit var userBean: UserBean

    @GetMapping("/create_user")
    fun createUserView(model: Model): String {
        model.addAttribute("new_user", NewUser())
        return "create_user"
    }

    @PostMapping("/do_create_user")
    fun createNewUserFunction(@Valid newUser: NewUser, errors: Errors, model: Model): String {
        model.addAttribute("new_user", newUser)

        if (errors.hasErrors()) {
            model.addAttribute(
                "errors",
                errors.allErrors.stream().map { error -> error.defaultMessage }.collect(Collectors.toUnmodifiableList()).asReversed()
            )
            return "create_user"
        }

        if (newUser.password != newUser.passwordRepeat) {
            model.addAttribute("errors", listOf("Entered passwords does not match."))
            return "create_user"
        }

        if (newUser.callengeUserAnswer != newUser.challengeCorrectAnswer) {
            model.addAttribute("errors", listOf("Challenge answer is not correct."))
            return "create_user"
        }

        if (userBean.userExists(newUser.userName)) {
            model.addAttribute("errors", listOf("Username '${newUser.userName}' is already taken."))
            return "create_user"
        }

        return if (!userBean.createUser(newUser)) {
            model.addAttribute("errors", listOf("User could not be created. Please try again."))
            "create_user"
        } else {
            model.addAttribute("errors", emptyList<String>())
            "login"
        }
    }

}