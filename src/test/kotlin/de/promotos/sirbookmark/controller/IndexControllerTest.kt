package de.promotos.sirbookmark.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders


class IndexControllerTest {
    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(IndexController()).build()
    }

    @Test
    fun getIndex() {
        mockMvc.get("/")
            .andDo { print() }
            .andExpect {
                status { isOk() }
            }.andExpect {
                model { attribute("foo", "bar") }
            }
    }
}