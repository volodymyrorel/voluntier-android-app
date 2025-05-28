package uzhnu.volodymyrorel.voluntier.data.navigation

import android.app.Activity
import android.util.Log
import androidx.navigation.NavHostController
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import uzhnu.volodymyrorel.voluntier.presentation.feature.auth.login.LoginConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.auth.signup.SignUpConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer.CreateNewAnswerConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_request.CreateNewRequestConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.MainScreenConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page.OrgPageScreenConstants
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
        navHostController?.navigate(route = SignUpConstants.Args)
    }

    override fun navigateToMainScreen() {
        navHostController?.navigate(route = MainScreenConstants.Args)
        Log.d("test", "user is navigated to HomeScreen")
    }

    override fun navigateToCreateNewRequestScreen() {
        navHostController?.navigate(route = CreateNewRequestConstants.Args)
    }

    override fun navigateToOrgPageScreen(uid: String) {
        navHostController?.navigate(route = OrgPageScreenConstants.Args(uid))
    }

    override fun navigateToCreateNewAnswerScreen(demandId: String) {
        navHostController?.navigate(route = CreateNewAnswerConstants.Args(demandId))
    }
}