package com.maksimka.service

import com.maksimka.models.CompactUser
import com.maksimka.models.Post
import com.maksimka.models.User
import com.maksimka.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun getOptional(uuid: String) = userRepository.findById(uuid)

    fun getOrDefault(uuid: String) = getOptional(uuid).orElse(User())

    fun getAllLike(name: String) = userRepository.findAllByNameStartingWith(name)

    fun getAllById(uuid: List<String>) = userRepository.findAllById(uuid)

    fun save(user: User) = userRepository.save(user)

    fun edit(uuid: String, compactUser: CompactUser) {
        userRepository.findById(uuid).ifPresent {
            userRepository.save(it.copyFromCompactUser(compactUser))
        }
    }

    fun remove(uuid: String) = userRepository.deleteById(uuid)

    fun subscribe(uuid: String, subscriberUUID: String) = userRepository.findById(uuid).ifPresent { user ->
        userRepository.findById(subscriberUUID).ifPresent { subscriber ->
            val user1 = user.copy(subscribers = user.subscribers.plusElement(subscriberUUID).toHashSet())
            val subscriber1 = subscriber.copy(subscription = subscriber.subscription.plusElement(uuid).toHashSet())
            userRepository.save(user1)
            userRepository.save(subscriber1)
        }
    }

    fun unsubscribe(uuid: String, subscriberUUID: String) = userRepository.findById(uuid).ifPresent { user ->
        userRepository.findById(subscriberUUID).ifPresent { subscriber ->
            val user1 = user.copy(subscribers = user.subscribers.minusElement(subscriber.uuid).toHashSet())
            val subscriber1 = subscriber.copy(subscription = subscriber.subscription.minusElement(user1.uuid).toHashSet())
            userRepository.save(user1)
            userRepository.save(subscriber1)
        }
    }

}