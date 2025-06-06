package uzhnu.volodymyrorel.voluntier.presentation.feature.org_page.mapper

import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import uzhnu.volodymyrorel.voluntier.presentation.feature.org_page.OrgPageStateUi

internal fun List<Demand>.mapToUi(): List<OrgPageStateUi.DemandUi> {
    return this.map { it.mapToUi() }
}
internal fun Demand.mapToUi(): OrgPageStateUi.DemandUi {
    return OrgPageStateUi.DemandUi(
        id = uid,
        type = type,
        title = title,
        description = description,
        targetSum = targetSum,
        currentSum = currentSum,
    )
}