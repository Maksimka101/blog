package com.maksimka.models

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "comment")
data class Comment(
        @Id
        @Column(name = "id")
        @JsonProperty(value = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @JoinColumn(name = "post")
        @ManyToOne
        val post: Post = Post(),

        @Column(name = "name")
        @JsonProperty(value = "name")
        val name: String = "",

        @Column(name = "imageUrl")
        @JsonProperty(value = "imageUrl")
        val imageUrl: String = "",

        @Column(name = "uuid")
        @JsonProperty(value = "uuid")
        val uuid: String = "",

        @Column(name = "text")
        @JsonProperty(value = "text")
        val text: String = ""

)
