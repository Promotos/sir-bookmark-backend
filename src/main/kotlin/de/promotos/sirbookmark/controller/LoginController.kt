package de.promotos.sirbookmark.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class LoginController {

    @GetMapping("/login")
    fun loginView(model: Model): ModelAndView {
        val mav = ModelAndView("login")
        return mav
    }

}