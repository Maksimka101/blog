package com.maksimka.controller

import com.maksimka.models.Post
import com.maksimka.service.PostService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("post")
class PostController(val postService: PostService) {

    @GetMapping("create")
    fun create(@RequestBody post: Post) = postService.create(post)

    @GetMapping("update")
    fun update(@RequestBody post: Post) = postService.update(post)

    // Todo так же удалять все медиа
    @GetMapping("delete/{id}")
    fun delete(@PathVariable id: Long) = postService.delete(id)

    @GetMapping("getSample")
    fun getSample() = Post()

}