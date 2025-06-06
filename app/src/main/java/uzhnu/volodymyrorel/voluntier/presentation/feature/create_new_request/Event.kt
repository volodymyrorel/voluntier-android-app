package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_request

sealed interface Event {

    data class ShowToast(val message: String) : Event

}