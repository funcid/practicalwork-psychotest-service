package me.func.practicalwork.controller

import me.func.practicalwork.entity.PsychoTest
import me.func.practicalwork.entity.Student
import me.func.practicalwork.repository.PsychoTestRepository
import me.func.practicalwork.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class UploadController {
    @Autowired
    lateinit var repository: PsychoTestRepository

    @Autowired
    lateinit var studentRepository: StudentRepository

    @GetMapping("/upload/{owner_id}")
    fun uploadTest(
        @PathVariable owner_id: Student,
        @RequestParam data: String,
    ): String {
        val array = data.split(",").filter { it.isNotEmpty() }.map { it.toInt() }
        var result = ""
        fun add(title: String, value: Int, low: String, medium: String, high: String) {
            result += "\n$title - ${
                when (value) {
                    in 0..7 -> low
                    in 8..14 -> medium
                    else -> high
                }
            }"
        }

        add(
            "I. Тревожность",
            array.take(10).sum(),
            "не тревожны",
            "тревожность средняя, допустимого уровня",
            "очень тревожны"
        )
        add(
            "II. Фрустрация",
            array.drop(10).take(10).sum(),
            "не имеете высокой самооценки, устойчивы к неудачам, не боитесь трудностей",
            "средний уровень, фрустрация имеет место",
            "у вас низкая самооценка, вы избегаете трудностей, боитесь неудач, фрустрированы"
        )
        add(
            "III. Агрессивность",
            array.drop(20).dropLast(10).sum(),
            "вы спокойны, выдержанны",
            "средний уровень агрессивности",
            "вы агрессивны, не выдержанны, есть трудности при общении и работе с людьми"
        )
        add(
            "IV. Ригидность",
            array.takeLast(10).sum(),
            "ригидности нет, легкая переключаемость",
            "средний уровень",
            "сильно выраженная ригидность, неизменность поведения, убеждений, взглядов, даже если они расходятся(не соответствуют реальной обстановке), жизни. Вам противопоказаны смена работы, изменения в семье"
        )

        repository.save(PsychoTest(owner_id, Date(), data, result))
        return result
    }

    @GetMapping("/download")
    fun getLastTest(
        @RequestParam firstName: String,
        @RequestParam lastName: String,
    ) = repository.findByOwner(studentRepository.findByFirstNameAndLastName(firstName, lastName).first()).lastOrNull()
        .toString()

    @RequestMapping(
        value = ["/download/{owner_id}"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun downloadLastTest(@PathVariable owner_id: Student) =
        repository.findByOwner(owner_id).lastOrNull().toString()

    @GetMapping("/all")
    fun findAll() = repository.findAll()

    @GetMapping("/findtestbyid/{id}")
    fun findById(@PathVariable id: Long) = repository.findById(id)

}