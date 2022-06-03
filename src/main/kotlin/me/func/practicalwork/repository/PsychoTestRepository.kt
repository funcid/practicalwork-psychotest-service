package me.func.practicalwork.repository

import me.func.practicalwork.entity.PsychoTest
import me.func.practicalwork.entity.Student
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PsychoTestRepository : CrudRepository<PsychoTest, Long> {

    fun findByOwner(owner: Student): Iterable<PsychoTest>

}