package io.pivotal.shinyay.api.reactor

interface NotificationService<in T> {

    fun notify(notification: T)
}