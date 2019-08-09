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
    fun create(uuid: String, post: Post) = userService.get(uuid).ifPresent {
        val copyUser = it.copy(posts = it.posts.plusElement(post))
        postRepository.save(post)
        userService.save(copyUser)
    }


    fun getById(id: Long): Optional<Post> = postRepository.findById(id)

    // gain post
    // copy all post data without id and comments
    // but id must be present
    fun update(post: Post) = postRepository.findById(post.id).ifPresent { postRepository.save(it.copyFromPost(post)) }

    fun delete(id: Long) = postRepository.deleteById(id)

}