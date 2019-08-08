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
        val postId: Long = 0,

        @Column(name = "imageUrl")
        @JsonProperty(value = "imageUrl")
        val imageUrl: String = "",

        @Column(name = "title")
        @JsonProperty(value = "title")
        val title: String = "",

        @Column(name = "commentIds")
        @JsonProperty(value = "commentIds")
        @OneToMany(orphanRemoval = true)
        val commentIds: Set<Comment> = setOf()

)
