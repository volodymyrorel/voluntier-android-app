package uzhnu.volodymyrorel.voluntier.domain.answer.entity

data class Answer(
    val id: String,
    val userId: String,
    val demandId: String,
    val sum: Double?,
    val description: String?
)
