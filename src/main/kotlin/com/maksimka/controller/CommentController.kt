package com.maksimka.controller

import com.maksimka.models.Comment
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("comment")
class CommentController(val commentService: CommentService) {

    @PutMapping("create")
    fun create(@RequestBody comment: Comment, @PathParam("postId") postId: Long) = commentService.create(postId, comment)

    @DeleteMapping("delete/{id}")
    fun delete(@PathVariable id: Long) = commentService.delete(id)

    @PutMapping("update")
    fun update(@RequestBody comment: Comment) = commentService.update(comment)

}