package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_org

import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand

data class HomeOrgStateUi(
    val demands: List<Demand>
) {
    companion object {
        val DEFAULT = HomeOrgStateUi(
            demands = emptyList()
        )
    }
}
