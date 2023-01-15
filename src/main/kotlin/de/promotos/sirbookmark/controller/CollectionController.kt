package de.promotos.sirbookmark.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class CollectionController : BaseController() {

    @GetMapping("/u/collection")
    fun collectionView(model: Model): ModelAndView {
        val mav = ModelAndView("/collection")
        return mav
    }

}