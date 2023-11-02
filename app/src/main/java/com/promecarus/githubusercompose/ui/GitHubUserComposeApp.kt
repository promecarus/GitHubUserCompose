package com.promecarus.githubusercompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.promecarus.githubusercompose.data.model.Detail
import com.promecarus.githubusercompose.ui.component.BarBottom
import com.promecarus.githubusercompose.ui.component.BarTop
import com.promecarus.githubusercompose.ui.component.ProfileUser
import com.promecarus.githubusercompose.ui.helper.Screen
import com.promecarus.githubusercompose.ui.helper.Screen.Companion.USERNAME
import com.promecarus.githubusercompose.ui.helper.State
import com.promecarus.githubusercompose.ui.screen.DetailScreen
import com.promecarus.githubusercompose.ui.screen.FavoriteScreen
import com.promecarus.githubusercompose.ui.screen.ListScreen
import com.promecarus.githubusercompose.ui.screen.SettingScreen
import com.promecarus.githubusercompose.ui.viewmodel.DetailViewModel
import com.promecarus.githubusercompose.ui.viewmodel.FavoriteViewModel
import com.promecarus.githubusercompose.ui.viewmodel.ListViewModel
import com.promecarus.githubusercompose.ui.viewmodel.SettingViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.koinViewModel

@Composable
fun GitHubUserComposeApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = Screen.List.route,
) {
    val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route
        ?: startDestination

    Scaffold(modifier = modifier, topBar = {
        if (currentRoute != Screen.List.route) BarTop(title = currentRoute,
            onNavigationClick = { navHostController.navigateUp() })
    }, bottomBar = {
        if (currentRoute != Screen.Detail.route && currentRoute != Screen.Setting.route) BarBottom(
            navHostController = navHostController, currentRoute = currentRoute
        )
    }) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = Screen.List.route) {
                val viewModel: ListViewModel = koinViewModel()
                ListScreen(
                    state = viewModel.state.collectAsState(initial = State.Loading).value,
                    query = viewModel.query,
                    onQueryChange = viewModel::search,
                    refresh = viewModel::refresh,
                    history = viewModel.getHistory().collectAsState(initial = emptyList()).value,
                    setHistory = viewModel::setHistory,
                    navigateToDetail = { username ->
                        navHostController.navigate(Screen.Detail.createRoute(username = username))
                    },
                    navigateToSetting = { navHostController.navigate(Screen.Setting.route) },
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument(USERNAME) { type = NavType.StringType })
            ) {
                val viewModel: DetailViewModel = koinViewModel()
                DetailScreen(
                    stateDetail = viewModel.stateDetail.collectAsState(initial = State.Loading).value,
                    stateFollowing = viewModel.stateFollowing.collectAsState(initial = State.Loading).value,
                    stateFollowers = viewModel.stateFollowers.collectAsState(initial = State.Loading).value,
                    refresh = viewModel::refresh,
                    navigateToDetail = { username ->
                        navHostController.navigate(Screen.Detail.createRoute(username = username))
                    },
                    username = it.arguments?.getString(USERNAME) ?: "",
                    getDetail = viewModel::getDetail,
                    getFollowers = viewModel::getFollowers,
                    getFollowing = viewModel::getFollowing,
                    isFavorite = viewModel.checkFavorite(it.arguments?.getString(USERNAME) ?: "")
                        .collectAsState(initial = 0).value,
                    addToFavorite = viewModel::addToFavorite,
                    removeFromFavorite = viewModel::removeFromFavorite
                )
            }
            composable(route = Screen.Favorite.route) {
                val viewModel: FavoriteViewModel = koinViewModel()
                FavoriteScreen(
                    users = viewModel.getAllFavorite().collectAsState(initial = emptyList()).value,
                    navigateToDetail = { username ->
                        navHostController.navigate(Screen.Detail.createRoute(username = username))
                    },
                    removeAllFavorite = viewModel::removeAllFavorite,
                )
            }
            composable(route = Screen.About.route) {
                ProfileUser(
                    detail = Detail(
                        id = 34930290,
                        username = "promecarus",
                        avatarUrl = "https://avatars.githubusercontent.com/u/34930290?v=4",
                        name = "Muhammad Haikal Al Rasyid",
                        email = "haikalslipi@gmail.com",
                    ),
                    modifier = Modifier.fillMaxSize(),
                )
            }
            composable(route = Screen.Setting.route) {
                val viewModel: SettingViewModel = koinViewModel()
                SettingScreen(
                    setting = runBlocking { viewModel.getSetting().first() },
                    onValueChange = viewModel::setSetting,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GitHubUserComposeAppPreview() {
    GitHubUserComposeApp()
}
