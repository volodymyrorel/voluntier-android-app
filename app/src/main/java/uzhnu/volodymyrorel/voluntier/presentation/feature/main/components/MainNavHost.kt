package uzhnu.volodymyrorel.voluntier.presentation.feature.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_org.HomeOrgRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_org.homeOrgRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_user.HomeUserRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_user.homeUserRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.profile.ProfileRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.profile.profileRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.OrganizationsRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.organizationsRoute

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    role: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (role == "user") homeUserRoute else homeOrgRoute
    ) {
        composable(homeUserRoute) {
            HomeUserRoute()
        }
        composable(homeOrgRoute) {
            HomeOrgRoute()
        }
        composable(organizationsRoute) {
            OrganizationsRoute()
        }
        composable(profileRoute) {
            ProfileRoute()
        }
    }
}