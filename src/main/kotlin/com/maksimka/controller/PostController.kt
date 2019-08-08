package com.maksimka.controller

import com.maksimka.models.Post
import com.maksimka.service.PostService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("post")
class PostController(val postService: PostService) {

    @PutMapping("create")
    fun create(@RequestBody post: Post, @RequestParam("uuid") uuid: String) = postService.create(uuid, post)

    @PutMapping("update")
    fun update(@RequestBody post: Post) = postService.update(post)

    // Todo так же удалять все медиа
    @DeleteMapping("delete/{id}")
    fun delete(@PathVariable id: Long) = postService.delete(id)

    @GetMapping("sample")
    fun getSample() = Post()

}