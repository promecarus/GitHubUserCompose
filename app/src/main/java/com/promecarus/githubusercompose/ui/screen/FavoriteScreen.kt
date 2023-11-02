package com.promecarus.githubusercompose.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.promecarus.githubusercompose.R
import com.promecarus.githubusercompose.data.model.User
import com.promecarus.githubusercompose.ui.component.ColumnUser
import com.promecarus.githubusercompose.ui.helper.TestTag.FAB_CLEAR_FAVORITE

@Composable
fun FavoriteScreen(
    users: List<User>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit = {},
    removeAllFavorite: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        ColumnUser(
            users = users,
            modifier = Modifier.align(Alignment.Center),
            navigateToDetail = navigateToDetail
        )

        if (users.isNotEmpty()) ExtendedFloatingActionButton(
            text = { Text(text = stringResource(R.string.delete_all_favorite)) },
            icon = { Icon(imageVector = Icons.Default.Delete, contentDescription = null) },
            onClick = { removeAllFavorite() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
                .testTag(FAB_CLEAR_FAVORITE)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen(users = emptyList())
}
