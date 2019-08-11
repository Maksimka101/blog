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
        @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val posts: Set<Post> = setOf(),

        @Column(name = "subscribers")
        @JsonProperty(value = "subscribers")
        val subscribers: HashSet<String> = hashSetOf(),

        @Column(name = "subscription")
        @JsonProperty(value = "subscription")
        val subscription: HashSet<String> = hashSetOf(),

        @Column(name = "isAuthorized")
        @JsonProperty(value = "isAuthorized")
        val isAuthorized: Boolean = false

) {
        fun copyFromCompactUser(user: CompactUser) = copy(name = user.name, uuid = user.uuid, isAuthorized = user.isAuthorized)
}

class CompactUser (

        @JsonProperty(value = "uuid")
        val uuid: String = "",

        @JsonProperty(value = "name")
        val name: String = "",

        @JsonProperty(value = "isAuthorized")
        val isAuthorized: Boolean = false

)

/*

 */