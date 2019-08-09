package com.maksimka.service

import com.maksimka.models.CompactUser
import com.maksimka.models.Post
import com.maksimka.models.User
import com.maksimka.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun get(uuid: String) = userRepository.findById(uuid)

    fun save(user: User) = userRepository.save(user)

    fun edit(uuid: String, compactUser: CompactUser) {
        userRepository.findById(uuid).ifPresent {
            userRepository.save(it.copyFromCompactUser(compactUser))
        }
    }

    fun remove(uuid: String) = userRepository.deleteById(uuid)

}