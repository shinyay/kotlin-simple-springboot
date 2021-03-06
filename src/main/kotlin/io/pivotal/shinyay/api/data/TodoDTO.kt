package io.pivotal.shinyay.api.data

import java.util.*

data class TodoDTO(
        var title: String,
        var message: String,
        var schedule: Long,
        var location: String = ""
) {

    constructor() : this("", "", -1, "")

    var id: String = ""
    var created: Date = Date()
    var modified: Date = Date()

    constructor(todo: Todo) : this(
            todo.title,
            todo.message,
            todo.schedule,
            todo.location
    ) {
        id = todo.id
        created = todo.created
        modified = todo.modified
    }
}