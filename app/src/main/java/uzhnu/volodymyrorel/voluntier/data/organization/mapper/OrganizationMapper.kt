package uzhnu.volodymyrorel.voluntier.data.organization.mapper

import com.google.firebase.firestore.DocumentSnapshot
import uzhnu.volodymyrorel.voluntier.domain.organization.entity.Organization
import uzhnu.volodymyrorel.voluntier.domain.utils.ktx.toLocalDateTime
import javax.inject.Inject

class OrganizationMapper @Inject constructor() {

    fun mapToOrganization(documents: List<DocumentSnapshot>) : List<Organization> {
        return documents.map { mapToOrganization(it) }
    }

    fun mapToOrganization(document: DocumentSnapshot) : Organization {
        return Organization(
            uid = document.id,
            email = document.data?.get("email") as String? ?: "",
            publicName = document.data?.get("orgPublicName") as String? ?: "",
            govName = document.data?.get("orgGovName") as String? ?: "",
            type = document.data?.get("orgType") as String? ?: "",
            code = document.data?.get("orgCode") as String? ?: "",
            description = document.data?.get("description") as String?,
            isVerified = document.data?.get("isVerified") as Boolean? ?: false,
            createdAt = ((document.data?.get("createdAt") as Long?) ?: 0L).toLocalDateTime(),
            updatedAt = ((document.data?.get("updatedAt") as Long?) ?: 0L).toLocalDateTime()
        )
    }
}