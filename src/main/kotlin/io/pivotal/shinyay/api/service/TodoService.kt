package io.pivotal.shinyay.api.service

import io.pivotal.shinyay.api.data.Todo
import org.springframework.stereotype.Service
import java.util.*

@Service("Todo Service")
class TodoService {

    fun getTodo(): List<Todo> = listOf(
            Todo(
                    UUID.randomUUID().toString(),
                    "My first ToDo",
                    "This is a message for the 1st Todo",
                    System.currentTimeMillis()
            ),
            Todo(
                    UUID.randomUUID().toString(),
                    "My second ToDo",
                    "This is a message for the 2nd Todo",
                    System.currentTimeMillis()
            )
    )

    fun insertTodo(todo: Todo): Todo{
        todo.id = UUID.randomUUID().toString()
        return todo
    }

    fun updateTodo(todo: Todo): Boolean = true

    fun deleteTodo(id: String): Boolean = false
}