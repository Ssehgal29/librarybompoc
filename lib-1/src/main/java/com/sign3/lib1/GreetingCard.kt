package com.sign3.lib1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Lib-1 widget: a simple greeting card with a colored container.
 */
@Composable
fun GreetingCard(
    name: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Hello, $name! 👋",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF0D47A1),
            )
            Text(
                text = "Served from lib-1",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF1565C0),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingCardPreview() {
    GreetingCard(name = "JitPack")
}
