package uzhnu.volodymyrorel.voluntier.data.organization

import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import uzhnu.volodymyrorel.voluntier.data.organization.mapper.OrganizationMapper
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.organization.OrganizationRepository
import uzhnu.volodymyrorel.voluntier.domain.organization.entity.Organization
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class OrganizationRepositoryImpl @Inject constructor(
    val firestore: FirebaseFirestore,
    val authHelper: AuthHelper,
    val organizationMapper: OrganizationMapper
) : OrganizationRepository {

    override suspend fun getOrganizations(): List<Organization> {
        return suspendCoroutine { continuation ->
            firestore
                .collection("users")
                .where(Filter.and(
                    Filter.notEqualTo("__name__", authHelper.user.id),
                    Filter.equalTo("role", "organization")
                ))
                .get()
                .addOnSuccessListener { response ->
                    continuation.resume(organizationMapper.mapToOrganization(response.documents))
                }
                .addOnFailureListener { error ->
                    continuation.resume(emptyList())
                }
        }
    }
}