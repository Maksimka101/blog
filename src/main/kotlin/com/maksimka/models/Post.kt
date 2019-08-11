package com.maksimka.models

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "post")
data class Post(
        @Id
        @Column(name = "postId")
        @JsonProperty(value = "postId")
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 1,

        @Column(name = "imageUrl")
        @JsonProperty(value = "imageUrl")
        val imageUrl: String = "",

        @Column(name = "title")
        @JsonProperty(value = "title")
        val title: String = "",

        @Column(name = "content")
        @JsonProperty(value = "content")
        val content: String = "",

        @Column(name = "comments")
        @JsonProperty(value = "comments")
        @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val comments: Set<Comment> = setOf()
) {
        fun copyFromPost(post: Post) = copy(imageUrl = post.imageUrl, title = post.title, content = post.content)
}
