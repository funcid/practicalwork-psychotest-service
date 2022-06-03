package me.func.practicalwork.entity

import java.util.*
import javax.persistence.*

@Entity
data class PsychoTest(
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    val owner: Student = Student(),
    val date: Date = Date(0L),
    @Column(length = 25500)
    val data: String = "",
    @Column(length = 25500)
    val result: String = "",
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = -1
) {
    override fun toString() = """{"id":$id,"date":"$date","data":"$data","result":"$result","owner":"$owner"}"""
}