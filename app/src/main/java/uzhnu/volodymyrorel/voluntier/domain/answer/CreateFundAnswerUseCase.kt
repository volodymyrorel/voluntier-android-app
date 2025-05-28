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
        sum: Double
    ) : Unit? {
        val currentSum = demandRepository.getDemandCurrentSum(demandId)
        val result1 = answerRepository.createFinancialAnswer(
            demandId = demandId,
            sum = sum
        )
        val result2 = demandRepository.updateDemandCurrentSum(demandId = demandId, sum = currentSum + sum)
        if (result1 != null && result2 != null)
            return Unit
        else return null
    }
}