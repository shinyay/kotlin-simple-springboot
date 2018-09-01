package io.pivotal.shinyay.api.mail

interface MailService {

    fun sendMessage(message: MailMessage)
}