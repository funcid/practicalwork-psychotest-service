package me.func.practicalwork

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class PracticalworkApplication

fun main(args: Array<String>) {
	runApplication<PracticalworkApplication>(*args)
}