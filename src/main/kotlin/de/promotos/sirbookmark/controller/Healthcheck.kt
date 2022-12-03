package de.promotos.sirbookmark.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
/**
 * Used to check if the server is able to respond to a REST call.
 */
class Healthcheck {

    /**
     * Health check endpoint, simply returns the string 'ok' with
     * status code 200.
     */
    @GetMapping("/health")
    fun health(): String {
        return "ok"
    }

}