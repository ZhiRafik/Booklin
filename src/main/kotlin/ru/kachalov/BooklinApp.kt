package ru.kachalov

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BooklinApp

fun main(args: Array<String>){
    runApplication<BooklinApp>(*args)
}