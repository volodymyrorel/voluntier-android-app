package uzhnu.volodymyrorel.voluntier.domain.answer

import uzhnu.volodymyrorel.voluntier.domain.answer.entity.AnswerData
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import uzhnu.volodymyrorel.voluntier.domain.demand.DemandRepository
import uzhnu.volodymyrorel.voluntier.domain.organization.OrganizationRepository
import javax.inject.Inject

class GetCurrentUserAnswersUseCase @Inject constructor(
    private val answerRepository: AnswerRepository,
    private val demandRepository: DemandRepository,
    private val organizationRepository: OrganizationRepository
) {

    suspend operator fun invoke() : List<AnswerData> {
        val result = mutableListOf<AnswerData>()
        val answers = answerRepository.getAnswersFromCurrentUser()
        answers.forEach { answer ->
            val answerDemand = demandRepository.getDemandById(answer.demandId)
            val demandOrg = organizationRepository.getOrganization(answerDemand?.ownerId ?: "")
            if (answerDemand != null && demandOrg != null) {
                result.add(AnswerData(
                    answer = answer,
                    demand = answerDemand,
                    org = demandOrg
                ))
            }
        }
        return result
    }
}