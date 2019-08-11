package com.maksimka

import com.maksimka.models.User
import com.maksimka.service.UserService
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class BlogApplicationTests {

    @InjectMocks
    lateinit var service: UserService

    @Test
    fun userController() {
        service.save(User())
        val user = service.get("")
        assertEquals(user.get(), User())
    }

}
