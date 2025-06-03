package uzhnu.volodymyrorel.voluntier.domain.answer

import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.demand.DemandRepository
import javax.inject.Inject

class CreateFundAnswerUseCase @Inject constructor(
    val authHelper: AuthHelper,
    val demandRepository: DemandRepository,
    val answerRepository: AnswerRepository
) {

    suspend operator fun invoke(
        demandId: String,
        sum: Double,
        description: String?
    ) : Unit? {
        val currentSum = demandRepository.getDemandCurrentSum(demandId)
        val result1 = answerRepository.createFundraisingAnswer(
            demandId = demandId,
            sum = sum,
            description = description
        )
        val result2 = demandRepository.updateDemandCurrentSum(demandId = demandId, sum = currentSum + sum)
        return if (result1 != null && result2 != null)
            Unit
        else null
    }
}