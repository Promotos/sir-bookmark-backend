package de.promotos.sirbookmark.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity()
class UserAccount(
    @Id var username: String = "",
    @Column(nullable = false) var email: String = "",
    @Column(nullable = false) var createdAt: LocalDateTime = LocalDateTime.now()
)