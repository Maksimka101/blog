package com.maksimka

import javax.persistence.*

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        val id: Int,

        @Column(name = "name", length = 20)
        val name: String
)