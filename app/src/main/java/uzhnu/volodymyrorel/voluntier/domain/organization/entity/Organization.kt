package uzhnu.volodymyrorel.voluntier.domain.organization.entity

import java.time.LocalDateTime

data class Organization(
    val uid: String,
    val email: String,
    val publicName: String,
    val govName: String,
    val type: String,
    val code: String,
    val description: String?,
    val isVerified: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
