package de.promotos.sirbookmark.controller

import de.promotos.sirbookmark.dto.NewUser
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.util.stream.Collectors

@Controller
class CreateUserController : BaseController() {

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


        return "create_user"
    }

}