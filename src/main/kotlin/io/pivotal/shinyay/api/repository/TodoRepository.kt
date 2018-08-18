package io.pivotal.shinyay.api.repository

import io.pivotal.shinyay.api.data.Todo
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface TodoRepository : CrudRepository<Todo, String> {

    @Query("from Todo t where t.schedule > ?1")
    fun findScheduledLaterThan(date: Date): Iterable<Todo>
}