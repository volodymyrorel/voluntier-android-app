package uzhnu.volodymyrorel.voluntier.domain.answer

import uzhnu.volodymyrorel.voluntier.domain.answer.entity.Answer

interface AnswerRepository {

    suspend fun getAnswersFromUserOnDemand(userId: String, demandId: String): List<Answer>

    suspend fun createFinancialAnswer(demandId: String, sum: Double): Unit?
}