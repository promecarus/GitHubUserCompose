package com.promecarus.githubusercompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.promecarus.githubusercompose.R
import com.promecarus.githubusercompose.data.model.User

@Composable
fun ColumnUser(
    users: List<User>,
    modifier: Modifier = Modifier,
    largeVerticalPadding: Boolean = false,
    navigateToDetail: (String) -> Unit = {},
) {
    if (users.isEmpty()) Text(
        text = stringResource(R.string.no_user_found),
        modifier = modifier.testTag(stringResource(R.string.no_user_found))
    )
    else LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(
            top = if (largeVerticalPadding) 88.dp else 16.dp, bottom = 88.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = users, key = { "${it.id}key" }) {
            ItemUser(item = it,
                modifier = Modifier
                    .clickable { navigateToDetail(it.username) }
                    .testTag("${it.username} item user"))
        }
    }
}
