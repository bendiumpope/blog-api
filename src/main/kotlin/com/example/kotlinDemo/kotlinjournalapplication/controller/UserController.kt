package com.example.kotlinDemo.kotlinjournalapplication.controller

import com.example.kotlinDemo.kotlinjournalapplication.model.UserResponse
import com.example.kotlinDemo.kotlinjournalapplication.model.User
import com.example.kotlinDemo.kotlinjournalapplication.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class UserController(@Autowired private val userRepository: UserRepository) {

    //gets all users
    @GetMapping("/users")
    fun getAllUsers(): List<User> = userRepository.findAll()

    //create a user
    @PostMapping("/users")
    fun createUser(@Valid @RequestBody user: User, responseMassage: UserResponse): ResponseEntity<UserResponse> {
        userRepository.save(user)

        responseMassage.status=HttpStatus.OK
        responseMassage.massage="successful"
        responseMassage.user=user

        return ResponseEntity.status(HttpStatus.OK).body(responseMassage)
    }

    //get a single user
    @GetMapping("/users/{userId}")
    fun getUserById(@PathVariable userId: Long): ResponseEntity<User> = userRepository.findById(userId).map {
                ResponseEntity.ok(it)
            }.orElse(ResponseEntity.notFound().build())

    //update a user
    @PutMapping("/users/{userId}")
    fun updateUser(@PathVariable userId:Long, @Valid @RequestBody updatedUser: User, responseMassage: UserResponse):ResponseEntity<UserResponse>
            = userRepository.findById(userId).map{
        val newUser = it.copy(firstname = updatedUser.firstname, lastname = updatedUser.lastname, email=updatedUser.email,
                password = updatedUser.password, phone = updatedUser.phone)
        userRepository.save(newUser)

        responseMassage.status=HttpStatus.OK
        responseMassage.massage="successful"
        responseMassage.user=newUser

        ResponseEntity.status(HttpStatus.OK).body(responseMassage)
            }.orElse(ResponseEntity.notFound().build())


    //delete a user
    @DeleteMapping("/users/{userId}")
    fun deleteUser(@PathVariable userId: Long, responseMassage: UserResponse): ResponseEntity<UserResponse> = userRepository.findById(userId).map {
        userRepository.delete(it)

        responseMassage.status=HttpStatus.OK
        responseMassage.massage="successful"
        ResponseEntity.status(HttpStatus.OK).body(responseMassage)
    }.orElse(ResponseEntity.notFound().build())


    //delete all user
    @DeleteMapping("/users")
    fun deleteAllUser()= userRepository.deleteAll()

}