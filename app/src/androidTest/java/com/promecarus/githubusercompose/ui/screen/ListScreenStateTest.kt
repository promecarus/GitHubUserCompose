package com.promecarus.githubusercompose.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.promecarus.githubusercompose.data.model.User
import com.promecarus.githubusercompose.ui.helper.State
import com.promecarus.githubusercompose.ui.helper.TestTag.LOADING
import org.junit.Rule
import org.junit.Test

class ListScreenStateTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test_ListScreen_StateLoading() {
        rule.apply {
            setContentListScreen(State.Loading)
            onNodeWithTag(LOADING).assertExists()
        }
    }

    @Test
    fun test_ListScreen_StateError() {
        rule.apply {
            setContentListScreen(State.Error("Error"))
            onNodeWithTag(LOADING).assertDoesNotExist()
            onNodeWithText("Error").assertExists()
        }
    }

    @Test
    fun test_ListScreen_StateSuccess() {
        val user = User(
            id = 34930290,
            username = "promecarus",
            avatarUrl = "https://avatars.githubusercontent.com/u/34930290?v=4",
            type = "User"
        )

        rule.apply {
            setContentListScreen(State.Success(listOf(user)))
            onNodeWithTag(LOADING).assertDoesNotExist()
            onNodeWithText("Error").assertDoesNotExist()
            onNodeWithContentDescription("${user.username}'s user avatar").assertExists()
            onNodeWithText(user.username).assertExists()
            onNodeWithText("ID: 34930290 | Type: User").assertExists()
        }
    }

    companion object {
        private fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>.setContentListScreen(
            stateDetail: State<List<User>>,
        ) {
            setContent { ListScreen(state = stateDetail) }
        }
    }
}
