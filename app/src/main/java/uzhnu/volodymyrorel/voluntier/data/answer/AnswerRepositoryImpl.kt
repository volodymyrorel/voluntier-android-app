package uzhnu.volodymyrorel.voluntier.data.answer

import com.google.firebase.firestore.FirebaseFirestore
import uzhnu.volodymyrorel.voluntier.data.answer.mapper.AnswerMapper
import uzhnu.volodymyrorel.voluntier.domain.answer.AnswerRepository
import uzhnu.volodymyrorel.voluntier.domain.answer.entity.Answer
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AnswerRepositoryImpl @Inject constructor(
    val firestore: FirebaseFirestore,
    val answerMapper: AnswerMapper,
    val authHelper: AuthHelper
) : AnswerRepository {

    override suspend fun getAnswersFromUserOnDemand(
        userId: String,
        demandId: String
    ): List<Answer> {

        return suspendCoroutine { continuation ->
            firestore
                .collection("answers")
                .whereEqualTo("userID", userId)
                .whereEqualTo("demandId", demandId)
                .get()
                .addOnSuccessListener { response ->
                    continuation.resume(answerMapper.mapToAnswer(response.documents))
                }
                .addOnFailureListener { error ->
                    continuation.resume(emptyList())
                }
        }
    }

    override suspend fun createFinancialAnswer(demandId: String, sum: Double) : Unit? {
        return suspendCoroutine { continuation ->
            val answerDocument = hashMapOf(
                "demandId" to demandId,
                "userId" to authHelper.user.id,
                "sum" to sum
            )
            firestore
                .collection("answers")
                .add(answerDocument)
                .addOnSuccessListener {
                    continuation.resume(Unit)
                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
        }
    }
}