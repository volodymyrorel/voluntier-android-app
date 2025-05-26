package uzhnu.volodymyrorel.voluntier.presentation.feature.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.home.HomeRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.home.homeRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.profile.ProfileRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.profile.profileRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.OrganizationsRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.organizationsRoute

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = homeRoute
    ) {

        composable(homeRoute) {
            HomeRoute()
        }

        composable(organizationsRoute) {
            OrganizationsRoute()
        }

        composable(profileRoute) {
            ProfileRoute()
        }
    }
}