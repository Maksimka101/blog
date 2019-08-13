package com.maksimka.repositories

import com.maksimka.models.Post
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface PostRepository : CrudRepository<Post, Long> {
//    @Transactional
//    @Modifying
//    @Query("update Post p set p.content = :content, p.imageUrl = :imageUrl, p.title = :title where p.id = :id")
//    fun update(@Param("id") id: Long, @Param("title") title: String, @Param("content") content: String, @Param("imageUrl") imageUrl: String)
}