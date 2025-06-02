package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer

import uzhnu.volodymyrorel.voluntier.domain.answer.entity.Answer

data class CreateNewAnswerStateUi(
    val demandId: String,
    val demandTitle: String = "",
    val demandDescription: String? = null,
    val currentSum: Double? = null,
    val targetSum: Double? = null,
    val type: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val userAnswers: List<Answer> = emptyList(),
    val answerDescription: String = "",
    val answerSum: String = "",
)
