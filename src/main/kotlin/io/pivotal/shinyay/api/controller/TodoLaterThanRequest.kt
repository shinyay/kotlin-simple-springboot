package io.pivotal.shinyay.api.controller

import java.util.*

data class TodoLaterThanRequest(val date: Date? = null) {
    constructor() : this(null)
}