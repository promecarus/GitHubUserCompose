package com.promecarus.githubusercompose.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.promecarus.githubusercompose.R
import com.promecarus.githubusercompose.data.model.Detail
import com.promecarus.githubusercompose.data.model.User
import com.promecarus.githubusercompose.ui.helper.TestTag.FAB_FAVORITE

@Composable
fun DetailContent(
    navigateToDetail: (String) -> Unit,
    detail: Detail,
    followers: List<User>,
    following: List<User>,
    isFavorite: Int,
    addToFavorite: (User) -> Unit,
    removeFromFavorite: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileUser(detail = detail)

            RowTabFollow(
                followers = followers,
                following = following,
                navigateToDetail = navigateToDetail,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        ExtendedFloatingActionButton(text = {
            Text(
                text = stringResource(
                    R.string.action_favorite,
                    if (isFavorite == 0) stringResource(R.string.add_to) else stringResource(R.string.remove_from)
                )
            )
        }, icon = {
            Icon(
                imageVector = if (isFavorite == 0) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                contentDescription = null
            )
        }, onClick = {
            if (isFavorite == 0) addToFavorite(
                User(
                    id = detail.id,
                    username = detail.username,
                    avatarUrl = detail.avatarUrl,
                    type = detail.type
                )
            ) else removeFromFavorite(detail.username)
        }, modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(end = 16.dp, bottom = 16.dp)
            .testTag(FAB_FAVORITE)
        )
    }
}
