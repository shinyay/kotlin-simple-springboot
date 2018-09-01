package io.pivotal.shinyay.api.mail

import org.hibernate.validator.constraints.Email
import javax.validation.constraints.NotNull

data class MailMessage(

        @Email
        @NotNull
        val to: String,
        val subject: String,
        val text: String
)