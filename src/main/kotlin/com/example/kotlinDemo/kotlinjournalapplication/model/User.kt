package com.example.kotlinDemo.kotlinjournalapplication.model

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "users")
data class User(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id:Long =0,
        @get: NotBlank val firstname:String = "",
        @get: NotBlank val lastname:String="",
        @get: NotBlank val email:String="",
        @get: NotBlank val password:String="",
        @get: NotNull val phone:Long=0,
        val createdAt:Instant = Instant.now()

) {
}