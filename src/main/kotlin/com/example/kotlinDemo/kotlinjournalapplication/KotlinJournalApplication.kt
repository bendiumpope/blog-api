package com.example.kotlinDemo.kotlinjournalapplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class KotlinJournalApplication

fun main(args: Array<String>) {
	runApplication<KotlinJournalApplication>(*args)
}
