package com.promecarus.githubusercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.promecarus.githubusercompose.ui.GitHubUserComposeApp
import com.promecarus.githubusercompose.ui.theme.GitHubUserComposeTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            KoinAndroidContext {
                GitHubUserComposeTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) { GitHubUserComposeApp() }
                }
            }
        }
    }
}
