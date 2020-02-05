package com.example.kotlinDemo.kotlinjournalapplication.model

import org.springframework.http.HttpStatus

class UserResponse(var status: HttpStatus = HttpStatus.BAD_REQUEST, var massage:String="", var user:User?=null) {
}