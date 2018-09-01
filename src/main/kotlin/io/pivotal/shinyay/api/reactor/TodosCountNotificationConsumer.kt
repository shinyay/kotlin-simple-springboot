package io.pivotal.shinyay.api.reactor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.bus.Event

@Service
class TodosCountNotificationConsumer : NotificationConsumer<TodosCountNotification> {

    @Autowired
    lateinit var service: TodosCountNotificationService

    override fun accept(t: Event<TodosCountNotification>?) {
        val data = t?.data
        data?.let {
            service.notify(data)
        }
    }

}