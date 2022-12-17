package de.promotos.sirbookmark.controller

import de.promotos.sirbookmark.dto.NewUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class CreateUserController : BaseController() {

    @GetMapping("/create_user")
    fun createUserView(model: Model): String {
        model.addAttribute("new_user", NewUser())
        return "create_user"
    }

    @PostMapping("/do_create_user")
    fun createNewUserFunction(@ModelAttribute newUser: NewUser, model: Model): String {
        model.addAttribute("new_user", newUser)
        return "create_user"
    }

}