package com.maksimka.repositories

import com.maksimka.models.Post
import org.springframework.data.repository.CrudRepository

interface PostRepository : CrudRepository<Post, Long> {
}