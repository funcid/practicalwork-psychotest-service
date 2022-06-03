package me.func.practicalwork.controller

import me.func.practicalwork.entity.Student
import me.func.practicalwork.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class RegistrationController {
    @Autowired
    lateinit var repository: StudentRepository

    @GetMapping("/registration")
    fun addUser(
        @RequestParam firstName: String,
        @RequestParam lastName: String,
        @RequestParam bornDate: Long,
        @RequestParam email: String,
        @RequestParam password: String,
    ): String {
        if (repository.findByFirstNameAndLastName(firstName, lastName).any())
            return "Пользователь с таким именем и фамилией существует!"
        val student = repository.save(Student(firstName, lastName, Date(bornDate), password, email))
        return "Успешно, уникальный номер вашего пользователя ${student.id}"
    }

    @GetMapping("/findall")
    fun findAll() = repository.findAll()

    @GetMapping("/findbyid/{id}")
    fun findById(@PathVariable id: Long) = repository.findById(id)

    @GetMapping("/removeall")
    fun findById() = repository.deleteAll()

    @GetMapping("/find")
    fun findByLastName(@RequestParam firstName: String, @RequestParam lastName: String) =
        repository.findByFirstNameAndLastName(firstName, lastName).lastOrNull()?.id ?: -1
}