package de.promotos.sirbookmark.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class IndexController : BaseController() {

    @GetMapping("/", "/index", "index.html")
    fun indexView(model: Model): ModelAndView {
        val mav = ModelAndView("index")
        mav.addObject("foo", "bar")
        return mav
    }

}