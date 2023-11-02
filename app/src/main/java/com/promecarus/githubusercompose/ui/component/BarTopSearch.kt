package com.promecarus.githubusercompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NorthWest
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.promecarus.githubusercompose.ui.helper.TestTag.SEARCH_BAR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarTopSearch(
    query: String,
    onQueryChange: (String) -> Unit,
    history: List<String>,
    setHistory: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var active by rememberSaveable { mutableStateOf(false) }

    DockedSearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {
            active = false
            if (it.trim() !in history && it.isNotEmpty()) setHistory(it)
        },
        active = active,
        onActiveChange = { active = it },
        modifier = modifier,
        placeholder = { Text("Search GitHub User") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = SEARCH_BAR) },
    ) {
        LazyColumn {
            items(items = history) {
                if (it.isNotEmpty()) ListItem(
                    headlineContent = { Text(text = it) },
                    modifier = Modifier
                        .clickable {
                            onQueryChange(it)
                            active = false
                        }
                        .fillMaxWidth(),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Default.NorthWest,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onQueryChange(it)
                            })
                    },
                )
            }
        }
    }
}
