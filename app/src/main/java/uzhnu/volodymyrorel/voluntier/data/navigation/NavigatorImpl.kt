package uzhnu.volodymyrorel.voluntier.data.navigation

import android.app.Activity
import androidx.navigation.NavHostController
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import uzhnu.volodymyrorel.voluntier.presentation.feature.auth.login.LoginConstants
import javax.inject.Inject

internal class NavigatorImpl @Inject constructor() : Navigator {

    private var navHostController: NavHostController? = null
    private var activity: Activity? = null

    private val backResultsMap = mutableMapOf<String, Any>()

    override fun attach(_navHostController: NavHostController, _activity: Activity) {
        navHostController = _navHostController
        activity = _activity
    }

    override fun detach() {
        navHostController = null
        activity = null
    }

    override fun popBackStack() {
        navHostController?.popBackStack()
    }

    override fun setBackResult(key: String, value: Any) {
        backResultsMap[key] = value
    }

    override fun navigateToLoginScreen() {
        navHostController?.navigate(route = LoginConstants.Args)
    }

    override fun navigateToSignUpScreen() {

    }

    override fun navigateToHomeScreen() {

    }
}