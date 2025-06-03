package uzhnu.volodymyrorel.voluntier.data.answer

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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
                .whereEqualTo("userId", userId)
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

    override suspend fun createFundraisingAnswer(demandId: String, sum: Double, description: String?) : Unit? {
        return suspendCoroutine { continuation ->
            val answerDocument = hashMapOf(
                "demandId" to demandId,
                "userId" to authHelper.user.id,
                "sum" to sum,
                "description" to description,
                "createdAt" to System.currentTimeMillis(),
                "updatedAt" to System.currentTimeMillis()
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

    override suspend fun createVolunteersAnswer(demandId: String, description: String?): Unit? {
        return suspendCoroutine { continuation ->
            val answerDocument = hashMapOf(
                "demandId" to demandId,
                "userId" to authHelper.user.id,
                "description" to description,
                "createdAt" to System.currentTimeMillis(),
                "updatedAt" to System.currentTimeMillis()
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

    override suspend fun createMaterialAnswer(demandId: String, description: String?): Unit? {
        return suspendCoroutine { continuation ->
            val answerDocument = hashMapOf(
                "demandId" to demandId,
                "userId" to authHelper.user.id,
                "description" to description,
                "createdAt" to System.currentTimeMillis(),
                "updatedAt" to System.currentTimeMillis()
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

    override suspend fun getAnswersFromCurrentUser(): List<Answer> {
        return suspendCoroutine { continuation ->
            firestore
                .collection("answers")
                .whereEqualTo("ownerId", authHelper.user.id)
                .orderBy("updatedAt", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { response ->
                    continuation.resume(answerMapper.mapToAnswer(response.documents))
                }
                .addOnFailureListener { e ->
                    continuation.resume(emptyList())
                }
        }
    }
}