package de.promotos.sirbookmark.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
            session.sessionCreationPolicy(
                SessionCreationPolicy.ALWAYS
            )
        }

        http.sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
            session.invalidSessionUrl(
                "/index"
            )
        }

        http.authorizeHttpRequests { authorizeHttpRequests ->
            authorizeHttpRequests.requestMatchers("/u/**").hasRole("USER").and().formLogin()
            authorizeHttpRequests.requestMatchers("/**").permitAll()
        }

        http.formLogin { form: FormLoginConfigurer<HttpSecurity?> ->
            form.loginPage(
                "/login"
            ).defaultSuccessUrl("/", true)
                .defaultSuccessUrl("/index.html")
                .permitAll()
        }

        http.logout { logout: LogoutConfigurer<HttpSecurity?> ->
            logout.logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index.html")
        }
        return http.build()
    }

    @Bean
    fun users(dataSource: DataSource): UserDetailsManager {
        return JdbcUserDetailsManager(dataSource)
    }

}