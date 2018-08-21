package io.pivotal.shinyay.api.service

import io.pivotal.shinyay.api.data.Todo
import io.pivotal.shinyay.api.data.TodoDTO
import io.pivotal.shinyay.api.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service("Todo Service")
class TodoService {

    @Autowired
    private lateinit var repository: TodoRepository

    fun getTodos(): Iterable<TodoDTO> = repository.findAll().map { it -> TodoDTO(it) }

    fun insertTodo(todo: TodoDTO): TodoDTO = TodoDTO(
            repository.save(
                    Todo(
                            title = todo.title,
                            message = todo.message,
                            location = todo.location,
                            schedule = todo.schedule
                    )
            )
    )

    fun updateTodo(todoDto: TodoDTO): TodoDTO {
        var todo = repository.findOne(todoDto.id)
        todo.title = todoDto.title
        todo.message = todoDto.message
        todo.location = todoDto.location
        todo.schedule = todoDto.schedule
        todo.modified = Date()
        todo = repository.save(todo)
        return TodoDTO(todo)
    }

    fun deleteTodo(id: String) = repository.delete(id)

    fun getScheduledLaterThan(date: Date): Iterable<TodoDTO> {
        return repository.findScheduledLaterThan(date).map { it -> TodoDTO(it) }
    }
}