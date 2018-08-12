package io.pivotal.shinyay.api.data

data class Note (
        var id : String,
        var title : String,
        var message : String,
        var location : String = ""
)