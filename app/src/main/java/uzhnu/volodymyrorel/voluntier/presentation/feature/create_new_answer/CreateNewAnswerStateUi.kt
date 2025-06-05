package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer

import uzhnu.volodymyrorel.voluntier.domain.answer.entity.Answer

data class CreateNewAnswerStateUi(
    val demandOrgId: String = "",
    val demandId: String,
    val demandTitle: String = "",
    val demandDescription: String? = null,
    val currentSum: Double? = null,
    val targetSum: Double? = null,
    val type: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val demandAnswers: List<AnswerUi> = emptyList(),
    val userAnswers: List<Answer> = emptyList(),
    val answerDescription: String = "",
    val answerSum: String = "0.0",
    val isOrg: Boolean = false
) {
    data class AnswerUi(
        val userSurname: String,
        val userName: String,
        val userFatherName: String,
        val userEmail: String,
        val answer: Answer
    )
}
