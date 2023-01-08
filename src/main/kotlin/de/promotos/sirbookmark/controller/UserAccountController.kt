package de.promotos.sirbookmark.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class UserAccountController : BaseController() {

    @GetMapping("/u/account")
    fun indexView(model: Model): ModelAndView {
        val mav = ModelAndView("/user_account")
        return mav
    }

}