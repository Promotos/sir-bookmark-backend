package de.promotos.sirbookmark

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

/**
 * Start the Sir-Bookmark application and provide the command line args [args] to the spring application.
 */
fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
