package de.promotos.sirbookmark.dto

data class NewUser(val userName: String = "", val email: String = "", val password: String = "", val passwordRepeat: String = "")