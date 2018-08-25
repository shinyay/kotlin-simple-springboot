package io.pivotal.shinyay.api.controller

data class NoteFindByTitleRequest(val title: String) {
    constructor() : this("")
}