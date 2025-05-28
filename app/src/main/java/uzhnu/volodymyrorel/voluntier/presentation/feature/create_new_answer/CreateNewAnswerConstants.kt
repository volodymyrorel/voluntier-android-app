package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer

import kotlinx.serialization.Serializable

object CreateNewAnswerConstants {

    @Serializable
    data class Args(
        val uid: String
    )
}