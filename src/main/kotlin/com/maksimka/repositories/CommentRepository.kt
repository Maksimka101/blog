package com.maksimka.repositories

import com.maksimka.models.Comment
import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comment, Long> {
}