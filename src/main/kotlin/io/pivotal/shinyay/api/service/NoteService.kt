package io.pivotal.shinyay.api.service

import io.pivotal.shinyay.api.data.Note
import org.springframework.stereotype.Service
import java.util.*

@Service("Note Service")
class NoteService {
    fun getNote() : List<Note> = listOf(
            Note(
                    UUID.randomUUID().toString(),
                    "My first note",
                    "This is a message for the 1st note."
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "My second note",
                    "This is a message for the 2nd note."
            )
    )

    fun insertNote(note : Note) : Note {
        note.id = UUID.randomUUID().toString()
        return note
    }

    fun updateNote(note: Note): Boolean = true
    fun deleteNote(id: String): Boolean = false
}