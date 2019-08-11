package com.maksimka.controller

import com.maksimka.models.CompactUser
import com.maksimka.models.User
import com.maksimka.service.UserService
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("user")
class UserController(private val userService: UserService) {

    @PutMapping("update/{uuid}")
    fun updateById(@PathVariable uuid: String, @RequestBody user: CompactUser) = userService.edit(uuid, user)

    @PutMapping("create")
    fun create(@RequestBody user: User) = userService.save(user)

    @GetMapping("get/{uuid}")
    fun get(@PathVariable uuid: String) = userService.get(uuid)

    // uuid divided by ";"
    @GetMapping("get")
    fun getAll(@PathParam("uuid") uuid: String) = userService.getAll(uuid.split(";"))

    @PutMapping("delete/{uuid}")
    fun delete(@PathVariable uuid: String) = userService.remove(uuid)

    @GetMapping("sample")
    fun sample() = User()

    @PutMapping("subscribe")
    fun subscribe(@PathParam("userUUID") uuid: String, @PathParam("subscriberUUID") subscriberUUID: String) = userService.subscribe(uuid, subscriberUUID)

    @PutMapping("unsubscribe")
    fun unsubscribe(@PathParam("userUUID") uuid: String, @PathParam("subscriberUUID") subscriberUUID: String) = userService.unsubscribe(uuid, subscriberUUID)

    @GetMapping("nameContains/{name}")
    fun getAllUsersLike(@PathVariable name: String) = userService.getAllLike(name)

}