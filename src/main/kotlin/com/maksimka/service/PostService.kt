package com.maksimka.service

import com.maksimka.models.Post
import com.maksimka.repositories.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(val postRepository: PostRepository, val userService: UserService) {

    // gain user uuid and post
    // save post to user ( load user and save post )
    // save user and save post
    fun create(uuid: String, post: Post) = userService.get(uuid).ifPresent {
        val copyUser = it.copy(posts = it.posts.plusElement(post))
        postRepository.save(post)
        userService.save(copyUser)
    }

    fun update(post: Post) = postRepository.save(post)

    fun delete(id: Long) = postRepository.deleteById(id)

}