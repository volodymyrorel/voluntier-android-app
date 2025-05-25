package uzhnu.volodymyrorel.voluntier.presentation.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator,
    private val authHelper: AuthHelper
): ViewModel() {

    init {
        viewModelScope.launch {
            delay(600)
            if (authHelper.isLoggedIn()) {
//                navigator.navigateToHomeScreen()
                navigator.navigateToMainScreen()
            } else {
                navigator.navigateToLoginScreen()
            }
        }
    }
}