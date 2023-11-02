package com.promecarus.githubusercompose.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextReplacement
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.promecarus.githubusercompose.R
import com.promecarus.githubusercompose.ui.helper.Screen
import com.promecarus.githubusercompose.ui.helper.Screen.Companion.ABOUT
import com.promecarus.githubusercompose.ui.helper.Screen.Companion.FAVORITE
import com.promecarus.githubusercompose.ui.helper.Screen.Companion.SETTING
import com.promecarus.githubusercompose.ui.helper.TestTag.BACK
import com.promecarus.githubusercompose.ui.helper.TestTag.FAB_CLEAR_FAVORITE
import com.promecarus.githubusercompose.ui.helper.TestTag.FAB_FAVORITE
import com.promecarus.githubusercompose.ui.helper.TestTag.FAB_SETTING
import com.promecarus.githubusercompose.ui.helper.TestTag.LOADING
import com.promecarus.githubusercompose.ui.helper.TestTag.SEARCH_BAR
import com.promecarus.githubusercompose.ui.theme.GitHubUserComposeTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.compose.KoinContext

@OptIn(ExperimentalTestApi::class)
class GitHubUserComposeAppTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navHostController: TestNavHostController

    @Before
    fun setup() {
        rule.setContent {
            navHostController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            KoinContext { GitHubUserComposeTheme { GitHubUserComposeApp(navHostController = navHostController) } }
        }
    }

    @Test
    fun test_ListScreenDisplayedAsStartDestination_Success() {
        navHostController.assertCurrentRoute(Screen.List.route)
    }

    @Test
    fun test_ListScreenDisplayedAsStartDestination_Failure() {
        navHostController.apply {
            assertCurrentRoute(Screen.About.route, equal = false)
            assertCurrentRoute(Screen.Favorite.route, equal = false)
            assertCurrentRoute(Screen.Setting.route, equal = false)
            assertCurrentRoute(Screen.Detail.route, equal = false)
        }
    }

    @Test
    fun test_SearchDisplayed_Success() {
        rule.apply {
            search("promecarus")
            onNodeWithTag("promecarus item user").assertExists()
        }
    }

    @Test
    fun test_SearchDisplayed_Failure() {
        rule.apply {
            search("")
            onNodeWithTag(activity.getString(R.string.no_user_found)).assertExists()
        }
    }

    @Test
    fun test_DetailDisplayed_Success() {
        rule.apply {
            search("eyegnieeslla")
            onNodeWithTag("eyegnieeslla item user").performClick()
            waitUntilDoesNotExist(hasTestTag(LOADING))
            onNodeWithText("1 public repository · 1 public gist").assertExists()
        }
    }

    @Test
    fun test_DetailDisplayed_Failure() {
        rule.apply {
            search("promecarus")
            onNodeWithTag("promecarus item user").performClick()
            waitUntilDoesNotExist(hasTestTag(LOADING))
            onNodeWithText("1 public repository · 1 public gist").assertDoesNotExist()
        }
    }

    @Test
    fun test_SettingDisplayed_Success() {
        rule.apply {
            onNodeWithTag(FAB_SETTING).performClick()
            onNodeWithText(SETTING).assertExists()
        }
    }

    @Test
    fun test_SettingDisplayed_Failure() {
        rule.onNodeWithText(SETTING).assertDoesNotExist()
    }

    @Test
    fun test_FavoriteDisplayed_Success() {
        rule.apply {
            search("eyegnieeslla")
            onNodeWithTag("eyegnieeslla item user").performClick()
            waitUntilDoesNotExist(hasTestTag(LOADING))
            onNodeWithTag(FAB_FAVORITE).performClick()
            onNodeWithTag(BACK).performClick()
            onNodeWithTag(FAVORITE).performClick()
            onNodeWithTag(activity.getString(R.string.no_user_found)).assertDoesNotExist()
            onNodeWithTag("eyegnieeslla item user").assertExists()
            onNodeWithTag(FAB_CLEAR_FAVORITE).performClick()
        }
    }

    @Test
    fun test_FavoriteDisplayed_Failure() {
        rule.apply {
            search("eyegnieeslla")
            onNodeWithTag("eyegnieeslla item user").performClick()
            waitUntilDoesNotExist(hasTestTag(LOADING))
            onNodeWithTag(FAB_FAVORITE).performClick().performClick()
            onNodeWithTag(BACK).performClick()
            onNodeWithTag(FAVORITE).performClick()
            onNodeWithTag(activity.getString(R.string.no_user_found)).assertExists()
            onNodeWithTag("eyegnieeslla item user").assertDoesNotExist()
        }
    }

    @Test
    fun test_AboutDisplayed_Success() {
        rule.apply {
            onNodeWithTag(ABOUT).performClick()
            onNodeWithText("Muhammad Haikal Al Rasyid").assertExists()
            onNodeWithText("promecarus · haikalslipi@gmail.com").assertExists()
        }
    }

    @Test
    fun test_AboutDisplayed_Failure() {
        rule.apply {
            onNodeWithTag(ABOUT).performClick()
            onNodeWithContentDescription("promecarus's user avatar")
            onNodeWithText("Muhammad Haikal Al Rasyid").assertExists()
            onNodeWithText("promecarus · haikalslipi@gmail.com").assertExists()
        }
    }

    companion object {
        @Suppress("SameParameterValue")
        private fun NavController.assertCurrentRoute(expected: String, equal: Boolean = true) {
            currentBackStackEntry?.destination?.route.let {
                if (equal) assertEquals(expected, it) else assertNotEquals(expected, it)
            }
        }

        private fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>.search(
            input: String,
        ) {
            onNodeWithContentDescription(SEARCH_BAR).apply {
                performTextReplacement(input)
                performImeAction()
            }
            waitUntilDoesNotExist(hasTestTag(LOADING))
        }
    }
}
