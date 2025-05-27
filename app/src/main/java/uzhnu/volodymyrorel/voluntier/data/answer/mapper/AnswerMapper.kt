package uzhnu.volodymyrorel.voluntier.data.answer.mapper

import com.google.firebase.firestore.DocumentSnapshot
import uzhnu.volodymyrorel.voluntier.domain.answer.entity.Answer
import javax.inject.Inject

class AnswerMapper @Inject constructor() {

    fun mapToAnswer(documents: List<DocumentSnapshot>) : List<Answer> {
        return documents.map { mapToAnswer(it) }
    }

    fun mapToAnswer(document: DocumentSnapshot) : Answer {
        return Answer(
            id = document.id,
            userId = document.data?.get("userId") as String? ?: "",
            demandId = document.data?.get("demandId") as String? ?: "",
            sum = document.data?.get("sum") as Double?,
            description = document.data?.get("description") as String?
        )
    }
}