package com.promecarus.githubusercompose.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.promecarus.githubusercompose.R

@Composable
fun ErrorHandler(error: String, refresh: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        when (error) {
            "HTTP 422 " -> ColumnUser(users = emptyList())

            else -> Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = when (error) {
                        "Unable to resolve host \"api.github.com\": No address associated with hostname" -> stringResource(
                            R.string.no_internet_connection
                        )

                        else -> error
                    }, textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(onClick = refresh) { Text(text = stringResource(R.string.retry)) }
            }
        }
    }
}
