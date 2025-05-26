package uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.mapper

import uzhnu.volodymyrorel.voluntier.domain.organization.entity.Organization
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.OrganizationsStateUi

internal fun Organization.mapToUi(): OrganizationsStateUi.OrgStateUi {
    return OrganizationsStateUi.OrgStateUi(
        uid = uid,
        name = publicName,
        description = description,
        createdAt = createdAt
    )
}