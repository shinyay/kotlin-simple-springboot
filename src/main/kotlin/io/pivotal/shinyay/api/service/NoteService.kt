package io.pivotal.shinyay.api.service

import io.pivotal.shinyay.api.data.Note
import io.pivotal.shinyay.api.data.NoteDTO
import io.pivotal.shinyay.api.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service("Note service")
class NoteService {

    @Autowired
    lateinit var repository: NoteRepository

    fun getNotes(): Iterable<NoteDTO> = repository.findAll().map { it -> NoteDTO(it) }

    fun insertNote(note: NoteDTO) = NoteDTO(
            repository.save(
                    Note(
                            title = note.title,
                            message = note.message,
                            location = note.location
                    )
            )
    )

    fun deleteNote(id: String) = repository.deleteById(id)

    fun updateNote(noteDto: NoteDTO): NoteDTO {
        val note = repository.findById(noteDto.id).get()
        note.title = noteDto.title
        note.message = noteDto.message
        note.location = noteDto.location
        note.modified = Date()
        return NoteDTO(repository.save(note))
    }

    fun findByTitle(title: String): Iterable<NoteDTO> {
        return repository.findByTitle(title).map { it -> NoteDTO(it) }
    }
}