package io.pivotal.shinyay.api.reactor

import io.pivotal.shinyay.api.mail.MailMessage
import io.pivotal.shinyay.api.mail.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodosCountNotificationServiceImpl : TodosCountNotificationService {

    @Autowired
    private lateinit var mailService : MailService

    override fun notify(notification: TodosCountNotification) {
        val to = "syanagihara@pivotal.io"
        val subject = "Notes count notification"
        val text = "Todos reached ${notification.todosCount} count."
        val message = MailMessage(to, subject, text)
        mailService.sendMessage(message)
    }
}