package de.promotos.sirbookmark.controller

import org.springframework.web.bind.annotation.ModelAttribute

open class BaseController {

    @ModelAttribute("version")
    fun getVersion(): String? {
        return "1.0"
    }
}