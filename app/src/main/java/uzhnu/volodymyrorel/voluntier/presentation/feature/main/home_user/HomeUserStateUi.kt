package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_user

import uzhnu.volodymyrorel.voluntier.domain.answer.entity.Answer
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import java.time.LocalDateTime

data class HomeUserStateUi(
    val answers: List<AnswerUi>
) {
    data class AnswerUi(
        val orgName: String,
        val demandTitle: String,
        val demandDescription: String?,
        val demandType: String,
        val answerSum: String?,
        val answerDescription: String?,
        val answerCreatedAt: LocalDateTime
    )

    companion object {
        val DEFAULT = HomeUserStateUi(
            answers = emptyList()
        )
    }
}
