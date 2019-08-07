package com.maksimka.service

import com.maksimka.models.Post
import com.maksimka.repositories.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(val postRepository: PostRepository) {

    fun create(post: Post) = postRepository.save(post)

    fun update(post: Post) = postRepository.save(post)

    fun delete(id: Long) = postRepository.deleteById(id)

}