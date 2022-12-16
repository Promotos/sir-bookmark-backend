package de.promotos.sirbookmark.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ModelAttribute

/**
 * Base class for all controller.
 * Used to define some layout template required model attributes.
 */
@Component
class BaseController {

    @Autowired
    lateinit var buildProperties: BuildProperties

    /**
     * Return the version number defined in the build gradle file.
     */
    @ModelAttribute("version")
    fun getVersion(): String {
        return buildProperties.version
    }

    @ModelAttribute("buildTimestamp")
    fun getBuildTimestamp(): String {
        return buildProperties.time.toString()
    }
}