package com.maksimka

import com.maksimka.models.Comment
import com.maksimka.models.CompactUser
import com.maksimka.models.Post
import com.maksimka.models.User
import com.maksimka.service.CommentService
import com.maksimka.service.PostService
import com.maksimka.service.UserService
import junit.framework.TestCase.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class BlogApplicationTests {

    @Autowired
    lateinit var userService: UserService

    @Test
    fun userService() {
        userService.save(User())
        var user1 = userService.getOrDefault("")
        assertEquals(user1, User())

        var user2 = User(uuid = "uuid", name = "maxim")
        userService.save(user2)
        assertEquals(user2, userService.getAllLike("max"))

        assertEquals(listOf(user1, user2), userService.getAllById(listOf("", "uuid")))

        userService.edit("", CompactUser(name = "ma"))
        user1 = user1.copy(name = "ma")
        assertEquals(user1, userService.getOrDefault(""))

        userService.subscribe(user1.uuid, user2.uuid)
        user1 = user1.copy(subscribers = user1.subscribers.plus(user2.uuid).toHashSet())
        user2 = user2.copy(subscription = user2.subscription.plus(user1.uuid).toHashSet())
        assertEquals(user1, userService.getOrDefault(user1.uuid))
        assertEquals(user2, userService.getOrDefault(user2.uuid))

        userService.unsubscribe(user1.uuid, user2.uuid)
        user1 = user1.copy(subscribers = user1.subscribers.minus(user2.uuid).toHashSet())
        user2 = user2.copy(subscription = user2.subscription.minus(user1.uuid).toHashSet())
        assertEquals(user1, userService.getOrDefault(user1.uuid))
        assertEquals(user2, userService.getOrDefault(user2.uuid))
    }

    @Autowired
    lateinit var postService: PostService

    @Test
    fun postService() {
        var user = userService.save(User(uuid = "id", isAuthorized = true))
        val post = Post(content = "content")
        postService.create(user.uuid, post)
        assertEquals(post, postService.getOrDefault(post.id))

        val updatedPost = post.copy(title = "title")
        postService.update(updatedPost)
        assertEquals(updatedPost, postService.getOrDefault(updatedPost.id))

        user = user.copy(posts = user.posts.plus(updatedPost))
        assertEquals(user, userService.getOrDefault(user.uuid))

        val user2 = User()
        val post2 = Post(id = 2)
        userService.save(user2)
        postService.create(user2.uuid, post2)
        assertFalse(postService.getOptional(2).isPresent)
    }

    @Autowired
    lateinit var commentService: CommentService

    @Test
    fun commentService() {
        val post = Post(id = 4)
        val comment = Comment()
        val user = userService.save(User(isAuthorized = true))
        postService.create(user.uuid, post)
        commentService.create(post.id, comment)
        val post1 = post.copy(comments = post.comments.plus(comment).toHashSet())
        assertEquals(post1, postService.getOrDefault(post.id))
    }

}
