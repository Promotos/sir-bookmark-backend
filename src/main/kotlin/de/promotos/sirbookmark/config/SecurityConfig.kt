package de.promotos.sirbookmark.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun users(dataSource: DataSource): UserDetailsManager {
        val user = User.builder()
            .username("user")
            .password("{bcrypt}$2a$10\$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
            .roles("USER")
            .build();

        val admin = User.builder()
            .username("admin")
            .password("{bcrypt}$2a$10\$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
            .roles("USER", "ADMIN")
            .build();

        val users = JdbcUserDetailsManager(dataSource)

        if (!users.userExists(admin.username)) {
            users.createUser(admin)
        }

        if (!users.userExists(user.username)) {
            users.createUser(user)
        }
        
        return users
    }

}