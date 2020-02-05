package com.example.kotlinDemo.kotlinjournalapplication.repository

import com.example.kotlinDemo.kotlinjournalapplication.model.Journal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface JournalRepository : JpaRepository<Journal, Long> {
}