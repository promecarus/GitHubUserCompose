package com.promecarus.githubusercompose.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.promecarus.githubusercompose.R
import com.promecarus.githubusercompose.data.model.Detail

@Composable
fun ProfileUser(
    detail: Detail,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(detail.avatarUrl)
                .crossfade(true).build(),
            contentDescription = "${detail.username}'s user avatar",
            modifier = Modifier
                .padding(top = 16.dp)
                .size(120.dp)
                .clip(CircleShape),
            placeholder = rememberVectorPainter(Icons.Default.AccountCircle),
            error = rememberVectorPainter(Icons.Default.ErrorOutline)
        )

        if (detail.name != null) {
            Text(
                text = detail.name,
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = detail.username + if (detail.email != null) " · ${detail.email}" else "",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        } else Text(
            text = detail.username,
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )

        if (detail.followers != null && detail.following != null) Text(
            text = pluralStringResource(
                id = R.plurals.follow, count = detail.followers, detail.followers, detail.following
            ), modifier = Modifier.padding(top = 16.dp), style = MaterialTheme.typography.titleSmall
        )

        if (detail.publicRepos != null && detail.publicGists != null) Text(
            text = pluralStringResource(
                id = R.plurals.repo, count = detail.publicRepos, detail.publicRepos
            ) + " · " + pluralStringResource(
                id = R.plurals.gist, count = detail.publicGists, detail.publicGists
            ), modifier = Modifier.padding(top = 16.dp), style = MaterialTheme.typography.titleSmall
        )
    }
}
