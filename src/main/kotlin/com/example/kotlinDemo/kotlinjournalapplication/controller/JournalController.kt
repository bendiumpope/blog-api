package com.example.kotlinDemo.kotlinjournalapplication.controller

import com.example.kotlinDemo.kotlinjournalapplication.model.Journal
import com.example.kotlinDemo.kotlinjournalapplication.model.JournalResponse
import com.example.kotlinDemo.kotlinjournalapplication.model.UserResponse
import com.example.kotlinDemo.kotlinjournalapplication.repository.JournalRepository
import com.example.kotlinDemo.kotlinjournalapplication.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class JournalController(@Autowired private val journalRepository: JournalRepository, @Autowired private val userRepository: UserRepository) {

    //gets all journals
    @GetMapping("/journals")
    fun getAllJournals(): List<Journal> = journalRepository.findAll()

    //create a journal
//    @PostMapping("/journals")
//    fun createJournal(@Valid @RequestBody journal: Journal): Journal = journalRepository.save(journal)

    @PostMapping("/journals/{userId}")
    fun createJournal(@PathVariable userId:Long, @RequestBody journal: Journal, responseMassage: JournalResponse):ResponseEntity<JournalResponse> {
        val user = userRepository.findById(userId)
        journal.userId = userId
        journalRepository.save(journal)

        responseMassage.status= HttpStatus.OK
        responseMassage.massage="successful"
        responseMassage.journal=journal
        return ResponseEntity.status(HttpStatus.OK).body(responseMassage)
    }

    //gets a single journal
    @GetMapping("/journals/{journalId}")
    fun getJournalById(@PathVariable journalId: Long): ResponseEntity<Journal> =
            journalRepository.findById(journalId).map{
                ResponseEntity.ok(it)
            }.orElse(ResponseEntity.notFound().build())


    //updates a journal
    @PutMapping("/journals/{journalId}")
    fun updateJournal(@PathVariable journalId: Long, @Valid @RequestBody updatedJournal: Journal, responseMassage: JournalResponse)
            :ResponseEntity<JournalResponse> = journalRepository.findById(journalId).map{
        val newJournal = it.copy(title = updatedJournal.title, content = updatedJournal.content)
        journalRepository.save(newJournal)

        responseMassage.status= HttpStatus.OK
        responseMassage.massage="successful"
        responseMassage.journal=newJournal

        ResponseEntity.status(HttpStatus.OK).body(responseMassage)
    }.orElse(ResponseEntity.notFound().build())


    //deletes a journal
    @DeleteMapping("/journals/{journalId}")
    fun deleteJournal(@PathVariable journalId: Long, responseMassage: JournalResponse): ResponseEntity<JournalResponse> =
            journalRepository.findById(journalId).map{
                journalRepository.delete(it)

                responseMassage.status= HttpStatus.OK
                responseMassage.massage="successful"
                ResponseEntity.status(HttpStatus.OK).body(responseMassage)

            }.orElse(ResponseEntity.notFound().build())

    //delete all user
    @DeleteMapping("/journals")
    fun deleteAllUser()= journalRepository.deleteAll()


}