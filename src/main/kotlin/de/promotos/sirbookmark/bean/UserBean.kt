package de.promotos.sirbookmark.bean

import de.promotos.sirbookmark.dto.NewUser
import de.promotos.sirbookmark.entity.UserAccount
import de.promotos.sirbookmark.entity.UserAccountRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Component

@Component
class UserBean {

    @Autowired
    private lateinit var userAccountRepo: UserAccountRepository

    @Autowired
    private lateinit var userDetailManager: UserDetailsManager

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Transactional
    fun createUser(newUser: NewUser): Boolean {
        return if (!userExists(newUser.userName)) {
            val udmNewUser = User.builder()
                .username(newUser.userName)
                .password(passwordEncoder.encode(newUser.password))
                .roles("USER")
                .build()
            val newUserAccount = UserAccount(username = newUser.userName, email = newUser.email)
            userDetailManager.createUser(udmNewUser)
            userAccountRepo.save(newUserAccount)
            true
        } else {
            false
        }
    }

    fun userExists(userName: String): Boolean {
        val existingUser = userAccountRepo.findById(userName)

        return if (existingUser.isEmpty) {
            if (userDetailManager.userExists(userName)) {
                throw IllegalStateException("User Repo and User Detail Manager mismatch for user $userName.")
            }
            false
        } else {
            if (!userDetailManager.userExists(userName)) {
                throw IllegalStateException("User Repo and User Detail Manager mismatch for user $userName.")
            }
            true
        }
    }

}