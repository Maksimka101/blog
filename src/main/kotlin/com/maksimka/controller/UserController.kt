package com.maksimka.controller

import com.maksimka.models.CompactUser
import com.maksimka.models.User
import com.maksimka.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController(private val userService: UserService) {

    @PutMapping("update/{uuid}")
    fun updateById(@PathVariable uuid: String, @RequestBody user: CompactUser) = userService.edit(uuid, user)

    @PutMapping("create")
    fun create(@RequestBody user: User) = userService.save(user)

    @GetMapping("get/{uuid}")
    fun get(@PathVariable uuid: String) = userService.get(uuid)

    @PutMapping("delete/{uuid}")
    fun delete(@PathVariable uuid: String) = userService.remove(uuid)

    @GetMapping("sample")
    fun sample() = User()

    // Todo
    @GetMapping("nameContains/{name}")
    fun getAllUsersLike(@PathVariable name: String) = "soon"

}