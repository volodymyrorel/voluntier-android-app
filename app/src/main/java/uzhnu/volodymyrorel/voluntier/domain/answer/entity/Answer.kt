package uzhnu.volodymyrorel.voluntier.domain.answer.entity

import java.time.LocalDateTime

data class Answer(
    val id: String,
    val userId: String,
    val demandId: String,
    val sum: Double?,
    val description: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
