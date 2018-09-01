package io.pivotal.shinyay.api

import io.pivotal.shinyay.api.data.TodoDTO
import io.pivotal.shinyay.api.service.TodoService
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [ApiApplication::class])
class TodoTest {

    @Autowired
    private lateinit var service: TodoService

    private val todos = mutableListOf<TodoDTO>()

    @Before
    fun prepare() {
        Assert.assertNotNull(service)

        (0..10).mapTo(todos) {
            TodoDTO(
                    "Stub todo title: $it",
                    "Stub todo message: $it",
                    System.currentTimeMillis()
            )
        }
    }

    @Test
    fun crud() {
        cleanup()
        insert()
        update()
        select()
        delete()
    }

    @After
    fun cleanup() {
        service.getTodos().forEach { todo ->
            service.deleteTodo(todo.id)
        }
    }

    fun insert() {
        todos.forEach { todo ->
            val result = service.insertTodo(todo)
            Assert.assertNotNull(result)
            Assert.assertNotNull(result.id)
            Assert.assertFalse(result.id.isEmpty())
            Assert.assertTrue(result.schedule > 0)
            todo.id = result.id
        }
    }

    fun update() {
        todos.forEach { todo ->
            todo.title = "updated"
            todo.message = "updated"
            val result = service.updateTodo(todo)
            Assert.assertNotNull(result)
            Assert.assertNotNull(result.id)
            Assert.assertFalse(result.id.isEmpty())
            Assert.assertEquals("updated", result.title)
            Assert.assertEquals("updated", result.message)
            Assert.assertTrue(result.schedule > 0)
        }
    }

    fun delete() {
        todos.forEach { todo ->
            println("Removing todo with id: ${todo.id}")
            service.deleteTodo(todo.id)
        }
    }

    fun select() {
        val result = service.getTodos()
        result.forEach { todo ->
            Assert.assertNotNull(todo)
            Assert.assertNotNull(todo.id)
            Assert.assertFalse(todo.id.isEmpty())
            Assert.assertEquals("updated", todo.title)
            Assert.assertEquals("updated", todo.message)
            Assert.assertTrue(todo.schedule > 0)
        }
    }
}