package me.func.practicalwork.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Student(
    val firstName: String,
    val lastName: String,
    val bornDate: Date = Date(0L),
    val password: String = "",
    val email: String = "",
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = -1
) {

    constructor() : this("", "")

    override fun toString() = """{"id":$id,"firstName":"$firstName","lastName":"$lastName","bornDate":"$bornDate","password":"$password","email":"$email"}"""

}