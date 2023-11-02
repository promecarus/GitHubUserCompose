package com.promecarus.githubusercompose.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.promecarus.githubusercompose.data.model.User

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowTabFollow(
    followers: List<User>,
    following: List<User>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val titles = listOf("Followers", "Following")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val statePager = rememberPagerState { titles.size }

    LaunchedEffect(selectedTabIndex) {
        statePager.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(statePager.currentPage, statePager.isScrollInProgress) {
        if (!statePager.isScrollInProgress) selectedTabIndex = statePager.currentPage
    }
    Column(modifier = modifier) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            titles.forEachIndexed { index, title ->
                Tab(selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) })
            }
        }
        HorizontalPager(
            state = statePager,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalAlignment = Alignment.Top
        ) {
            Box(Modifier.fillMaxSize()) {
                ColumnUser(
                    users = if (it == 0) followers else following,
                    modifier = Modifier.align(Alignment.Center),
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}
