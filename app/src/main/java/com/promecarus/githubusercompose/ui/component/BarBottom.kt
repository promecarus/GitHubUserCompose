package com.promecarus.githubusercompose.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.promecarus.githubusercompose.ui.helper.NavigationTmpl
import com.promecarus.githubusercompose.ui.helper.Screen
import com.promecarus.githubusercompose.ui.helper.Screen.Companion.ABOUT
import com.promecarus.githubusercompose.ui.helper.Screen.Companion.FAVORITE
import com.promecarus.githubusercompose.ui.helper.Screen.Companion.LIST

@Composable
fun BarBottom(
    navHostController: NavHostController,
    currentRoute: String,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        listOf(
            NavigationTmpl(label = LIST, imageVector = Icons.Default.List, route = Screen.List),
            NavigationTmpl(
                label = FAVORITE, imageVector = Icons.Default.Favorite, route = Screen.Favorite
            ),
            NavigationTmpl(
                label = ABOUT, imageVector = Icons.Default.AccountCircle, route = Screen.About
            ),
        ).forEach {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = it.imageVector,
                        contentDescription = it.route.contentDescription
                    )
                },
                modifier = Modifier.testTag(it.label),
                label = { Text(it.label) },
                selected = currentRoute == it.route.route,
                onClick = {
                    navHostController.apply {
                        navigate(it.route.route) {
                            popUpTo(graph.findStartDestination().id) { saveState = true }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                })
        }
    }
}
