package io.pivotal.shinyay.api.controller

import io.pivotal.shinyay.api.data.Todo
import io.pivotal.shinyay.api.data.TodoDTO
import io.pivotal.shinyay.api.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = "/todos")
class TodoController {

    @Autowired
    private lateinit var service: TodoService

    @GetMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getTodos() = service.getTodos()

    @PutMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertTodo(@RequestBody todo: TodoDTO) = service.insertTodo(todo)

    @DeleteMapping(
            value = "/{id}",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteTodo(@PathVariable(name = "id") id : String) = service.deleteTodo(id)

    @PostMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateTodo(@RequestBody todo: TodoDTO) = service.updateTodo(todo)

    @PostMapping(
            value = "/later_than",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getTodosLaterThan(@RequestBody payload: TodoLaterThanRequest): Iterable<TodoDTO> =
            service.getScheduledLaterThan(payload.date)
}