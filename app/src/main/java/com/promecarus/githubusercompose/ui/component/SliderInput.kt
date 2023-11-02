package com.promecarus.githubusercompose.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SliderInput(
    text: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 1f..100f,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = text, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp)
        )

        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            onValueChangeFinished = onValueChangeFinished
        )
    }
}
