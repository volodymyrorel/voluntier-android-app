package uzhnu.volodymyrorel.voluntier.data.demand

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import uzhnu.volodymyrorel.voluntier.data.demand.mapper.DemandMapper
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.demand.DemandRepository
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DemandRepositoryImpl @Inject constructor(
    val authHelper: AuthHelper,
    val firestore: FirebaseFirestore,
    val demandMapper: DemandMapper
): DemandRepository {

    override suspend fun createNewDemand(type: String, title: String, description: String?, sum: Double?): Unit? {
        return suspendCoroutine { continuation ->
            val demandDocument = hashMapOf(
                "ownerId" to authHelper.user.id,
                "type" to type,
                "title" to title,
                "description" to description,
                "targetSum" to sum,
                "currentSum" to 0.0,
                "createdAt" to System.currentTimeMillis(),
                "updatedAt" to System.currentTimeMillis()
            )
            firestore
                .collection("demands")
                .add(demandDocument)
                .addOnSuccessListener {
//                    Log.d("test", "User data has been stored. User ID: ${}")
                    continuation.resume(Unit)
                }
                .addOnFailureListener { error ->
//                    Log.d("test", "User data not stored. Error: ${error.message}")
                    continuation.resume(null)
                }
        }
    }

    override suspend fun getOrganizationDemands(orgId: String): List<Demand> {
        return suspendCoroutine { continuation ->
            firestore
                .collection("demands")
                .whereEqualTo("ownerId", orgId)
                .get()
                .addOnSuccessListener { response ->
                    continuation.resume(demandMapper.mapToDemand(response.documents))
                }
                .addOnFailureListener { error ->
                    continuation.resume(emptyList())
                }
        }
    }
}