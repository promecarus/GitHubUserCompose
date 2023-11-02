package com.promecarus.githubusercompose.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.promecarus.githubusercompose.data.model.Detail
import com.promecarus.githubusercompose.data.model.User
import com.promecarus.githubusercompose.ui.helper.State
import com.promecarus.githubusercompose.ui.helper.TestTag.LOADING
import org.junit.Rule
import org.junit.Test

class DetailScreenStateTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test_DetailScreen_StateLoading() {
        rule.apply {
            setContentDetailScreen(State.Loading, State.Loading, State.Loading)
            onNodeWithTag(LOADING).assertExists()
        }
    }

    @Test
    fun test_DetailScreen_StateError() {
        rule.apply {
            setContentDetailScreen(
                State.Error("Error"),
                State.Error("Error"),
                State.Error("Error")
            )
            onNodeWithTag(LOADING).assertDoesNotExist()
            onNodeWithText("Error").assertExists()
        }
    }

    @Test
    fun test_DetailScreen_StateSuccess() {
        rule.apply {
            setContentDetailScreen(
                State.Success(
                    Detail(
                        id = 34930290,
                        username = "promecarus",
                        avatarUrl = "https://avatars.githubusercontent.com/u/34930290?v=4",
                        name = "Muhammad Haikal Al Rasyid",
                        email = "haikalslipi@gmail.com"
                    )
                ),
                State.Success(emptyList()),
                State.Success(emptyList())
            )
            onNodeWithTag(LOADING).assertDoesNotExist()
            onNodeWithText("Error").assertDoesNotExist()
            onNodeWithText("Muhammad Haikal Al Rasyid").assertExists()
            onNodeWithText("promecarus · haikalslipi@gmail.com").assertExists()
        }
    }

    companion object {
        private fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>.setContentDetailScreen(
            stateDetail: State<Detail>,
            stateFollowing: State<List<User>>,
            stateFollowers: State<List<User>>,
        ) {
            setContent {
                DetailScreen(
                    stateDetail = stateDetail,
                    stateFollowing = stateFollowing,
                    stateFollowers = stateFollowers,
                )
            }
        }
    }
}
