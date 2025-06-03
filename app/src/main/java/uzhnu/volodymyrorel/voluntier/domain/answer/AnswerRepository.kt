package uzhnu.volodymyrorel.voluntier.domain.answer

import uzhnu.volodymyrorel.voluntier.domain.answer.entity.Answer

interface AnswerRepository {

    suspend fun getAnswersFromUserOnDemand(userId: String, demandId: String): List<Answer>

    suspend fun createFundraisingAnswer(demandId: String, sum: Double, description: String?): Unit?

    suspend fun createVolunteersAnswer(demandId: String, description: String?): Unit?

    suspend fun createMaterialAnswer(demandId: String, description: String?): Unit?

    suspend fun getAnswersFromCurrentUser(): List<Answer>
}