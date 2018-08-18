package io.pivotal.shinyay.api.repository

import io.pivotal.shinyay.api.security.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {

    fun findOneByEmail(email: String): User?
}