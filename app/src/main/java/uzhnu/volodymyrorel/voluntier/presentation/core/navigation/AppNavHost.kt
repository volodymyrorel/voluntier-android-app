package uzhnu.volodymyrorel.voluntier.presentation.core.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uzhnu.volodymyrorel.voluntier.presentation.feature.auth.login.LoginConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.auth.login.LoginRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.auth.signup.SignUpConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.auth.signup.SignUpRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer.CreateNewAnswerConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer.CreateNewAnswerRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_request.CreateNewRequestConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_request.CreateNewRequestRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.MainRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.MainScreenConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page.OrgPageRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page.OrgPageScreenConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.splash.SplashConstants
import uzhnu.volodymyrorel.voluntier.presentation.feature.splash.SplashRoute


@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = SplashConstants.Args,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable<SplashConstants.Args> {
            SplashRoute()
        }
        composable<LoginConstants.Args> {
            LoginRoute()
        }
        composable<SignUpConstants.Args> {
            SignUpRoute()
        }
        composable<MainScreenConstants.Args> {
            MainRoute()
        }
        composable<CreateNewRequestConstants.Args> {
            CreateNewRequestRoute()
        }
        composable<OrgPageScreenConstants.Args> {
            OrgPageRoute()
        }
        composable<CreateNewAnswerConstants.Args> {
            CreateNewAnswerRoute()
        }
    }
}