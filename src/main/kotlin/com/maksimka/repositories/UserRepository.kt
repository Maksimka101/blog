package com.maksimka.repositories

import com.maksimka.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {
    fun findAllByNameStartingWith(nameStartsWith: String): User
}