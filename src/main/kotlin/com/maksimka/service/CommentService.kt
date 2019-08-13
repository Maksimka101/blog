package com.maksimka.service

import com.maksimka.models.Comment
import com.maksimka.repositories.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(val commentRepository: CommentRepository, val postService: PostService) {

    fun create(postId: Long, comment: Comment) = postService.getOptional(postId).ifPresent {
        commentRepository.save(comment)
        val copyPost = it.copy(comments = it.comments.plus(comment))
        postService.update(comment.uuid, copyPost)
    }

    fun delete(id: Long) = commentRepository.deleteById(id)

    fun update(comment: Comment) = commentRepository.save(comment)

}