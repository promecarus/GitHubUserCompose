package com.promecarus.githubusercompose.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.promecarus.githubusercompose.R
import com.promecarus.githubusercompose.ui.helper.Screen.Companion.LIST
import com.promecarus.githubusercompose.ui.helper.Screen.Companion.SETTING
import com.promecarus.githubusercompose.ui.helper.Screen.Companion.USERNAME
import com.promecarus.githubusercompose.ui.helper.TestTag.BACK

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarTop(
    title: String,
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(title = { Text(text = if (title == "$LIST/{$USERNAME}") stringResource(R.string.detail_user) else title) },
        modifier = modifier,
        navigationIcon = {
            if (title == "$LIST/{$USERNAME}" || title == SETTING) {
                IconButton(onClick = { onNavigationClick() }, modifier = Modifier.testTag(BACK)) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = BACK)
                }
            }
        })
}
