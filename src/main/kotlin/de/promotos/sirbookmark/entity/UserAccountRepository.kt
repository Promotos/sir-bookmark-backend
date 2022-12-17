package de.promotos.sirbookmark.entity

import org.springframework.data.repository.CrudRepository

interface UserAccountRepository : CrudRepository<UserAccount, String> {
}