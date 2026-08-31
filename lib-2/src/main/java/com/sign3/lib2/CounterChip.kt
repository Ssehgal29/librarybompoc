package com.sign3.lib2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Lib-2 widget: a self-contained counter chip with +/- buttons.
 */
@Composable
fun CounterChip(
    modifier: Modifier = Modifier,
    initialCount: Int = 0,
) {
    var count by rememberSaveable { mutableIntStateOf(initialCount) }

    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = Color(0xFFE8F5E9),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedButton(onClick = { count-- }) { Text("−") }
            Text(
                text = "lib-2 count: $count",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1B5E20),
            )
            Button(
                onClick = { count++ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
            ) { Text("+") }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CounterChipPreview() {
    CounterChip()
}
