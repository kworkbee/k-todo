package me.g1tommy.ktodo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KTodoApplication

fun main(args: Array<String>) {
	runApplication<KTodoApplication>(*args)
}
