package com.promecarus.githubusercompose.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.promecarus.githubusercompose.R
import com.promecarus.githubusercompose.data.model.User
import com.promecarus.githubusercompose.ui.theme.GitHubUserComposeTheme

@Composable
fun ItemUser(item: User, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth(), shape = MaterialTheme.shapes.extraLarge) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(item.avatarUrl)
                    .crossfade(true).build(),
                contentDescription = "${item.username}'s user avatar",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(50.dp)
                    .clip(CircleShape),
                placeholder = rememberVectorPainter(Icons.Default.AccountCircle),
                error = rememberVectorPainter(Icons.Default.ErrorOutline)
            )
            Column {
                Text(text = item.username, style = MaterialTheme.typography.titleLarge)
                Text(
                    text = stringResource(R.string.id_type, item.id, item.type),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemUserPreview() {
    GitHubUserComposeTheme {
        ItemUser(
            User(
                id = 34930290,
                username = "promecarus",
                avatarUrl = "https://avatars.githubusercontent.com/u/34930290?v=4",
                type = "User"
            )
        )
    }
}
