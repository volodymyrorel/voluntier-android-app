package uzhnu.volodymyrorel.voluntier.data.demand.mapper

import com.google.firebase.firestore.DocumentSnapshot
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import uzhnu.volodymyrorel.voluntier.domain.utils.ktx.toLocalDateTime
import javax.inject.Inject

class DemandMapper @Inject constructor() {

    fun mapToDemand(documents: List<DocumentSnapshot>) : List<Demand> {
        return documents.map { mapToDemand(it) }
    }

    fun mapToDemand(document: DocumentSnapshot) : Demand {
        return Demand(
            uid = document.id,
            ownerId = document.data?.get("ownerId") as String? ?: "",
            type = document.data?.get("type") as String? ?: "",
            title = document.data?.get("title") as String? ?: "",
            description = document.data?.get("description") as String?,
            targetSum = document.data?.get("targetSum") as Double?,
            currentSum = document.data?.get("currentSum") as Double?,
            createdAt = ((document.data?.get("createdAt") as Long?) ?: 0L).toLocalDateTime(),
            updatedAt = ((document.data?.get("updatedAt") as Long?) ?: 0L).toLocalDateTime()
        )
    }
}