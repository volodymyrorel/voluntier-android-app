package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val navigator: Navigator
) : ViewModel() {
    fun onCreateNewRequestClicked() {
        navigator.navigateToCreateNewRequestScreen()
    }
}