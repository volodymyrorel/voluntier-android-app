package uzhnu.volodymyrorel.voluntier.domain.auth.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String
)