package com.maksimka.controller

import com.maksimka.models.Comment
import com.maksimka.repositories.CommentRepository
import com.maksimka.service.PostService
import org.springframework.stereotype.Service

@Service
class CommentService(val commentRepository: CommentRepository, val postService: PostService) {

    fun create(postId: Long, comment: Comment) = postService.getById(postId).ifPresent {
        commentRepository.save(comment)
        val copyPost = it.copy(comments = it.comments.plusElement(comment))
        postService.update(copyPost)
    }

    fun delete(id: Long) = commentRepository.deleteById(id)

    fun update(comment: Comment) = commentRepository.save(comment)

}