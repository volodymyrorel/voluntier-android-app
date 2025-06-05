package uzhnu.volodymyrorel.voluntier.domain.answer

import uzhnu.volodymyrorel.voluntier.domain.answer.entity.Answer
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer.CreateNewAnswerStateUi
import javax.inject.Inject

class GetDemandAnswersDataUseCase @Inject constructor(
    private val answerRepository: AnswerRepository,
    private val authHelper: AuthHelper
) {

    suspend operator fun invoke(
        demandId: String
    ): List<CreateNewAnswerStateUi.AnswerUi> {
        val answers = answerRepository.getAnswersOnDemand(demandId).sortedByDescending { it.createdAt }.map { answer ->
            map(answer)
        }
        return answers
    }

    private suspend fun map(answer: Answer): CreateNewAnswerStateUi.AnswerUi {
        val userData = authHelper.getUserData(answer.userId)!!
        return CreateNewAnswerStateUi.AnswerUi(
            userSurname = userData.surname,
            userName = userData.name,
            userFatherName = userData.fatherName,
            userEmail = userData.email,
            answer = answer
        )
    }
}