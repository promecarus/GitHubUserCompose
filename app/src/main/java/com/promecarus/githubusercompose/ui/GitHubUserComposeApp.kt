package com.promecarus.githubusercompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.promecarus.githubusercompose.ui.theme.GitHubUserComposeTheme

@Composable
fun GitHubUserComposeApp(modifier: Modifier = Modifier) {
    GitHubUserComposeTheme {
        Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Text(text = "Hello!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GitHubUserComposeAppPreview() {
    GitHubUserComposeApp()
}
