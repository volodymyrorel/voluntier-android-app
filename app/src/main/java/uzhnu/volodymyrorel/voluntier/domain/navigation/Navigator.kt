package uzhnu.volodymyrorel.voluntier.domain.navigation

import android.app.Activity
import androidx.navigation.NavHostController

interface Navigator {

    fun attach(_navHostController: NavHostController, _activity: Activity)
    fun detach()
    fun popBackStack()
    //    fun popBackStack(key: String, value: String)
    fun setBackResult(key: String, value: Any)

    fun navigateToLoginScreen()
    fun navigateToSignUpScreen()
    fun navigateToMainScreen()
    fun navigateToCreateNewRequestScreen()
    fun navigateToOrgPageScreen(uid: String)
}