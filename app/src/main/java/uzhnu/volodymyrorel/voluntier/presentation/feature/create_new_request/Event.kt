package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_request

sealed interface Event {

    data class ShowToast(/*@StringRes val resId: Int*/ val message: String) : Event

}