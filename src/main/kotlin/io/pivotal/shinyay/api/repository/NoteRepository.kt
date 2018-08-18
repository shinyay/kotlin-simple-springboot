package io.pivotal.shinyay.api.repository

import io.pivotal.shinyay.api.data.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository : CrudRepository<Note, String> {
    fun findByTitle(title: String): Iterable<Note>
}