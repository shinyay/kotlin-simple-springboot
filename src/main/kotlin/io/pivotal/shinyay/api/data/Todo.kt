package io.pivotal.shinyay.api.data

data class Todo(
        var id: String = "",
        var title: String,
        var message: String,
        var schedule: Long,

        var location: String = ""
)