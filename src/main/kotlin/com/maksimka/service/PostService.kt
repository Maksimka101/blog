package com.maksimka.service

import com.maksimka.models.Post
import com.maksimka.repositories.PostRepository
import org.springframework.stereotype.Service

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


    fun getOrDefault(id: Long): Post = getOptional(id).orElse(Post())

    fun getOptional(id: Long) = postRepository.findById(id)

    // gain post, return new post and delete old post
    fun update(uuid: String, post: Post): Post {
        var finalPost = Post()
        postRepository.findById(post.id).ifPresent { oldPost ->
            userService.getOptional(uuid).ifPresent { user ->
                if (user.isAuthorized) {
                    finalPost = postRepository.save(oldPost.copyFromPost(post))
                    postRepository.delete(oldPost)
                    val userPosts = user.posts
                            .filterNot { it.content == oldPost.content && it.title == oldPost.title }
                            .toSet()
                            .plus(finalPost)
                    val copyUser = user.copy(posts = userPosts)
                    userService.save(copyUser)
                }
            }
        }
        return finalPost
    }

    fun delete(id: Long) = postRepository.deleteById(id)

}