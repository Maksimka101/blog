package com.maksimka.models

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "user")
data class User (

        @Id
        @Column(length = 20)
        @JsonProperty(value = "uuid")
        val uuid: String = "",

        @JsonProperty(value = "name")
        val name: String = "",

        @JsonProperty(value = "posts")
        @OneToMany(orphanRemoval = true)
        val posts: Set<Post> = setOf(),

        @Column(name = "subscribers")
        @JsonProperty(value = "subscribers")
        val subscribers: Set<String> = setOf(),

        @Column(name = "subscription")
        @JsonProperty(value = "subscription")
        val subscription: Set<String> = setOf(),

        @Column(name = "isAuthorized")
        @JsonProperty(value = "isAuthorized")
        val isAuthorized: Boolean = false

) {
        fun copyFromCompactUser(user: CompactUser) = copy(name = user.name, uuid = user.uuid)
}

class CompactUser (

        @JsonProperty(value = "uuid")
        val uuid: String = "",

        @JsonProperty(value = "name")
        val name: String = ""

)

/*

 */