package uzhnu.volodymyrorel.voluntier.presentation.feature.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_org.homeOrgRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_user.homeUserRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.organizationsRoute
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.profile.profileRoute

@Composable
fun MainBottomBar(
    navHostController: NavHostController,
    role: String
) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isHomeSelected = currentDestination?.hierarchy?.any { it.route == homeUserRoute || it.route == homeOrgRoute} == true
    val iSearchSelected = currentDestination?.hierarchy?.any { it.route == organizationsRoute } == true
    val isUserPageSelected = currentDestination?.hierarchy?.any { it.route == profileRoute } == true

    NavigationBar {
        NavigationBarItem(
            selected = isHomeSelected,
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            onClick = { navHostController.navigate(if (role == "user") homeUserRoute else homeOrgRoute) }
        )
        NavigationBarItem(
            selected = iSearchSelected,
            icon = { Icon(imageVector = Icons.Default.Menu, contentDescription = null) },
            onClick = { navHostController.navigate(organizationsRoute) }
        )
        NavigationBarItem(
            selected = isUserPageSelected,
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            onClick = { navHostController.navigate(profileRoute) }
        )
    }
}