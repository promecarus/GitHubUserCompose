package com.promecarus.githubusercompose.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.promecarus.githubusercompose.data.model.Detail
import com.promecarus.githubusercompose.data.model.User
import com.promecarus.githubusercompose.ui.component.DetailContent
import com.promecarus.githubusercompose.ui.component.ErrorHandler
import com.promecarus.githubusercompose.ui.helper.State
import com.promecarus.githubusercompose.ui.helper.TestTag.LOADING

@Composable
fun DetailScreen(
    stateDetail: State<Detail>,
    stateFollowing: State<List<User>>,
    stateFollowers: State<List<User>>,
    modifier: Modifier = Modifier,
    refresh: () -> Unit = {},
    navigateToDetail: (String) -> Unit = {},
    username: String = "",
    getDetail: (String) -> Unit = {},
    getFollowers: (String) -> Unit = {},
    getFollowing: (String) -> Unit = {},
    isFavorite: Int = 0,
    addToFavorite: (User) -> Unit = {},
    removeFromFavorite: (String) -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            stateDetail is State.Loading || stateFollowing is State.Loading || stateFollowers is State.Loading -> {
                CircularProgressIndicator(modifier = Modifier.testTag(LOADING))
                getDetail(username)
                getFollowers(username)
                getFollowing(username)
            }

            stateDetail is State.Error -> ErrorHandler(
                error = stateDetail.message, refresh = refresh
            )

            stateFollowers is State.Error -> ErrorHandler(
                error = stateFollowers.message, refresh = refresh
            )

            stateFollowing is State.Error -> ErrorHandler(
                error = stateFollowing.message, refresh = refresh
            )

            stateDetail is State.Success && stateFollowing is State.Success && stateFollowers is State.Success -> DetailContent(
                navigateToDetail = navigateToDetail,
                detail = stateDetail.data,
                followers = stateFollowers.data,
                following = stateFollowing.data,
                isFavorite = isFavorite,
                addToFavorite = addToFavorite,
                removeFromFavorite = removeFromFavorite
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        stateDetail = State.Success(
            Detail(
                id = 34930290,
                username = "promecarus",
                avatarUrl = "https://avatars.githubusercontent.com/u/34930290?v=4",
                name = "Muhammad Haikal Al Rasyid",
                email = "haikalslipi@gmail.com"
            )
        ), stateFollowing = State.Success(emptyList()), stateFollowers = State.Success(emptyList())
    )
}
