package io.pivotal.shinyay.api.controller

import io.pivotal.shinyay.api.data.Note
import io.pivotal.shinyay.api.data.NoteDTO
import io.pivotal.shinyay.api.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/notes")
@EnableAutoConfiguration
class NoteController {

    @Autowired
    private lateinit var service: NoteService

    @GetMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNotes() = service.getNotes()

    @PutMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertNote(@RequestBody note: NoteDTO) = service.insertNote(note)

    @DeleteMapping(
            value = "/{id}",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteNote(@PathVariable(name = "id") id : String) = service.deleteNote(id)

    @PostMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateNote(@RequestBody note : NoteDTO) = service.updateNote(note)

    @PostMapping(
            value = "/by_title",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)

    )
    fun getTodosLaterThan(
            @RequestBody payload: NoteFindByTitleRequest
    ): Iterable<NoteDTO> = service.findByTitle(payload.title)
}