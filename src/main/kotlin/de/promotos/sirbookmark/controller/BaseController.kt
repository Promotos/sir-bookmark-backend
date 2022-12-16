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
    var buildProperties: BuildProperties? = null;

    /**
     * Return the version number defined in the build gradle file.
     */
    @ModelAttribute("version")
    fun getVersion(): String {
        return buildProperties?.version ?: ""
    }

    @ModelAttribute("buildTimestamp")
    fun getBuildTimestamp(): String {
        if (buildProperties != null) {
            return buildProperties?.time.toString()
        }
        return ""
    }
}