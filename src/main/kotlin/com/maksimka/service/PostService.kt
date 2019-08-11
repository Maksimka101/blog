package com.maksimka.service

import com.maksimka.models.Post
import com.maksimka.repositories.PostRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(val postRepository: PostRepository, val userService: UserService) {

    // gain user uuid and post
    // save post to user ( load user and save post )
    // save user and save post
    fun create(uuid: String, post: Post) = userService.getOptional(uuid).ifPresent {
        if (it.isAuthorized) {
            postRepository.save(post)
            val copyUser = it.copy(posts = it.posts.plusElement(post))
            userService.save(copyUser)
        }
    }


    fun getOrDefault(id: Long) = getOptional(id).orElse(Post())

    fun getOptional(id: Long) = postRepository.findById(id)

    // gain post
    // copy all post data without id and comments
    // but id must be present
    fun update(post: Post) = postRepository.findById(post.id).ifPresent {
        val save = it.copyFromPost(post)
        println("from repo: " + postRepository.save(save))
        println("save: $save")
    }

    fun delete(id: Long) = postRepository.deleteById(id)

}