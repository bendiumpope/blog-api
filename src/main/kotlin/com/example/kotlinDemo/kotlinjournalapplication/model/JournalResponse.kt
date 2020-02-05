package com.example.kotlinDemo.kotlinjournalapplication.model

import org.springframework.http.HttpStatus

class JournalResponse(var status: HttpStatus = HttpStatus.BAD_REQUEST, var massage:String="", var journal: Journal?=null) {
}