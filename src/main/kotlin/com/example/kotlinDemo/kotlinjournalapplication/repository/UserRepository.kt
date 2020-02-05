package com.example.kotlinDemo.kotlinjournalapplication.repository

import com.example.kotlinDemo.kotlinjournalapplication.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :JpaRepository<User, Long> {


}