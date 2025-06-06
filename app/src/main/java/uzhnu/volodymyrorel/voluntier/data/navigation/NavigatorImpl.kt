package uzhnu.volodymyrorel.voluntier.data.navigation

import android.app.Activity
import androidx.navigation.NavHostController
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import uzhnu.volodymyrorel.voluntier.presentation.feature.auth.login.LoginConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.auth.signup.SignUpConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer.CreateNewAnswerConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_request.CreateNewRequestConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.MainConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.org_page.OrgPageConstants
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
        navHostController?.navigate(route = MainConstants.Args)
    }

    override fun navigateToCreateNewRequestScreen() {
        navHostController?.navigate(route = CreateNewRequestConstants.Args)
    }

    override fun navigateToOrgPageScreen(uid: String) {
        navHostController?.navigate(route = OrgPageConstants.Args(uid))
    }

    override fun navigateToCreateNewAnswerScreen(demandId: String) {
        navHostController?.navigate(route = CreateNewAnswerConstants.Args(demandId))
    }
}