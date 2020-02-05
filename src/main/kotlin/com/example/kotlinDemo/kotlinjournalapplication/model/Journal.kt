package com.example.kotlinDemo.kotlinjournalapplication.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@Entity(name="journals")
data class Journal(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long =0,
        @get: NotBlank val title:String = "",
        @get: NotBlank val content:String = "",
        @get: NotNull  var userId: Long = 0

) {
}