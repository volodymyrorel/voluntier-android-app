package uzhnu.volodymyrorel.voluntier.domain.answer

import uzhnu.volodymyrorel.voluntier.domain.answer.entity.Answer
import uzhnu.volodymyrorel.voluntier.domain.demand.DemandRepository
import uzhnu.volodymyrorel.voluntier.domain.organization.OrganizationRepository
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_user.HomeUserStateUi
import javax.inject.Inject

class GetHomeUserScreenDataUseCase @Inject constructor(
    private val answerRepository: AnswerRepository,
    private val demandRepository: DemandRepository,
    private val organizationRepository: OrganizationRepository
) {

    suspend operator fun invoke(): HomeUserStateUi {
        val answersUi = answerRepository.getAnswersFromCurrentUser().map { answer ->
            map(answer)
        }
        return HomeUserStateUi(
            answers = answersUi
        )
    }

    private suspend fun map(
        answer: Answer
    ) : HomeUserStateUi.AnswerUi {
        val demand = demandRepository.getDemandById(answer.demandId)!!
        val org = organizationRepository.getOrganization(demand.ownerId)!!
        return HomeUserStateUi.AnswerUi(
            orgName = org.publicName,
            demandTitle = demand.title,
            demandType = demand.type,
            demandDescription = demand.description,
            answerDescription = answer.description,
            answerSum = answer.sum.toString(),
            answerCreatedAt = answer.createdAt
        )
    }
}