package uzhnu.volodymyrorel.voluntier.domain.answer.entity

import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import uzhnu.volodymyrorel.voluntier.domain.organization.entity.Organization

data class AnswerData(
    val answer: Answer,
    val demand: Demand,
    val org: Organization
)
