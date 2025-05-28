package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer

import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand

data class CreateNewAnswerStateUi(
    val demandId: String,
    val demandDescription: String? = null,
    val currentSum: Double? = null,
    val targetSum: Double? = null,
    val type: String = "",
    val answerDescription: String = "",
    val answerSum: String = ""
)
