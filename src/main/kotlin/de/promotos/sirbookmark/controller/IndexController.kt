package de.promotos.sirbookmark.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    @GetMapping("/")
    fun indexView(model: Model): String {
        model.addAttribute("foo", "bar")
        return "index"
    }

}