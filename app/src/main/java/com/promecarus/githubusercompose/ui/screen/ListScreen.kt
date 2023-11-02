package com.promecarus.githubusercompose.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
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
import com.promecarus.githubusercompose.ui.component.BarTopSearch
import com.promecarus.githubusercompose.ui.component.ColumnUser
import com.promecarus.githubusercompose.ui.component.ErrorHandler
import com.promecarus.githubusercompose.ui.helper.State
import com.promecarus.githubusercompose.ui.helper.TestTag.FAB_SETTING
import com.promecarus.githubusercompose.ui.helper.TestTag.LOADING

@Composable
fun ListScreen(
    state: State<List<User>>,
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit = {},
    refresh: () -> Unit = {},
    history: List<String> = emptyList(),
    setHistory: (String) -> Unit = {},
    navigateToDetail: (String) -> Unit = {},
    navigateToSetting: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        BarTopSearch(
            query = query,
            onQueryChange = onQueryChange,
            history = history,
            setHistory = setHistory,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.TopCenter),
        )

        when (state) {
            is State.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag(LOADING)
            )

            is State.Error -> ErrorHandler(
                error = state.message, refresh = refresh, Modifier.align(Alignment.Center)
            )

            is State.Success -> ColumnUser(
                users = state.data,
                modifier = Modifier.align(Alignment.Center),
                largeVerticalPadding = true,
                navigateToDetail = navigateToDetail
            )
        }

        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(R.string.setting)) },
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) },
            onClick = { navigateToSetting() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
                .testTag(FAB_SETTING)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ListScreenPreview() {
    ListScreen(state = State.Success(emptyList()))
}
