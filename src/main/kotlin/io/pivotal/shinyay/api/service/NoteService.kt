package io.pivotal.shinyay.api.service

import io.pivotal.shinyay.api.data.Note
import io.pivotal.shinyay.api.data.NoteDTO
import io.pivotal.shinyay.api.reactor.NotesCountNotification
import io.pivotal.shinyay.api.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.bus.Event
import reactor.bus.EventBus
import java.util.*

@Service("Note Service")
class NoteService {

    @Autowired
    private lateinit var repository: NoteRepository

    @Autowired
    private lateinit var eventBus: EventBus

    fun getNotes() : Iterable<NoteDTO> = repository.findAll().map { it -> NoteDTO(it) }

    fun insertNote(note: NoteDTO): NoteDTO {
        val result = NoteDTO(
                repository.save(
                        Note(
                                title = note.title,
                                message = note.message,
                                location = note.location
                        )
                )
        )
        val count = getNotes().count()
        if (count > 10) {
            val notification = NotesCountNotification(count)
            eventBus.notify("notesCountNotificationConsumer", Event.wrap(notification))
        }
        return result
    }

    fun updateNote(noteDto: NoteDTO): NoteDTO {
        var note = repository.findOne(noteDto.id)
        note.title = noteDto.title
        note.message = noteDto.message
        note.location = noteDto.location
        note.modified = Date()
        note = repository.save(note)
        return NoteDTO(note)
    }

    fun deleteNote(id: String) = repository.delete(id)

    fun findByTitle(title: String): Iterable<NoteDTO> {
        return repository.findByTitle(title).map { it -> NoteDTO(it) }
    }
}