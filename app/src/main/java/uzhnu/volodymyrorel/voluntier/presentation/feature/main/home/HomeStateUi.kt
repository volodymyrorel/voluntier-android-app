package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home

import uzhnu.volodymyrorel.voluntier.domain.answer.entity.Answer
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand

data class HomeStateUi(
    val userRole: String,
    val orgDemandsList: List<Demand>,
    val userAnswersList: List<AnswerUi>
) {
    data class AnswerUi(
        val orgName: String,
        val demand: Demand,
        val answer: Answer
    )

    companion object {
        val DEFAULT = HomeStateUi(
            userRole = "user",
            orgDemandsList = emptyList(),
            userAnswersList = emptyList()
        )
    }
}
