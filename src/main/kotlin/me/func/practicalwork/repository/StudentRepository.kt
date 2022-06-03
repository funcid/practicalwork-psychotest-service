package me.func.practicalwork.repository

import me.func.practicalwork.entity.Student
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : CrudRepository<Student, Long> {

    fun findByLastName(lastName: String): Iterable<Student>

    fun findByFirstNameAndLastName(firstName: String, lastName: String): Iterable<Student>

}