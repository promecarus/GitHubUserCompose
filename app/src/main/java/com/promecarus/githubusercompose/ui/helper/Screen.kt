package com.promecarus.githubusercompose.ui.helper

sealed class Screen(val route: String, val contentDescription: String = "") {
    data object About : Screen(ABOUT, CONTENT_DESCRIPTION_ABOUT)
    data object Favorite : Screen(FAVORITE, CONTENT_DESCRIPTION_FAVORITE)
    data object List : Screen(LIST, CONTENT_DESCRIPTION_LIST)
    data object Setting : Screen(SETTING, CONTENT_DESCRIPTION_SETTING)
    data object Detail : Screen("$LIST/{$USERNAME}") {
        fun createRoute(username: String) = "$LIST/$username"
    }

    companion object {
        const val CONTENT_DESCRIPTION_ABOUT = "about_page"
        const val CONTENT_DESCRIPTION_FAVORITE = "favorite_page"
        const val CONTENT_DESCRIPTION_LIST = "list_page"
        const val CONTENT_DESCRIPTION_SETTING = "setting_page"
        const val ABOUT = "About"
        const val FAVORITE = "Favorite"
        const val LIST = "List"
        const val SETTING = "Setting"
        const val USERNAME = "username"
    }
}
