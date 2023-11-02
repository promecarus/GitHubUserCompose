package com.promecarus.githubusercompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.promecarus.githubusercompose.R
import com.promecarus.githubusercompose.data.model.Setting
import com.promecarus.githubusercompose.ui.component.SliderInput

@Composable
fun SettingScreen(
    setting: Setting,
    modifier: Modifier = Modifier,
    onValueChange: (Setting) -> Unit = {},
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var result by remember { mutableFloatStateOf(setting.result.toFloat()) }
        var followers by remember { mutableFloatStateOf(setting.followers.toFloat()) }
        var following by remember { mutableFloatStateOf(setting.following.toFloat()) }

        val settingCast = Setting(
            result = result.toInt(), followers = followers.toInt(), following = following.toInt()
        )

        SliderInput(text = stringResource(R.string.search_result_limit, result.toInt()),
            value = result,
            onValueChange = { result = it },
            onValueChangeFinished = { onValueChange(settingCast) })

        SliderInput(text = stringResource(R.string.followers_limit, followers.toInt()),
            value = followers,
            onValueChange = { followers = it },
            onValueChangeFinished = { onValueChange(settingCast) })

        SliderInput(text = stringResource(R.string.following_limit, following.toInt()),
            value = following,
            onValueChange = { following = it },
            onValueChangeFinished = { onValueChange(settingCast) })
    }
}

@Preview(showSystemUi = true)
@Composable
fun SettingScreenPreview() {
    SettingScreen(setting = Setting(result = 50, followers = 25, following = 10))
}
