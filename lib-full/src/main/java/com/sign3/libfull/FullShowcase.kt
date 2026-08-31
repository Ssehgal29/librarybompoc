package com.sign3.libfull

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sign3.lib1.GreetingCard
import com.sign3.lib2.CounterChip
import com.sign3.lib3.PulseLoader
import com.sign3.lib4.RatingStars

/**
 * Lib-full widget: showcases every widget from lib-1 through lib-4 in one column.
 */
@Composable
fun FullShowcase(
    name: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "lib-full showcase",
            style = MaterialTheme.typography.headlineSmall,
        )
        GreetingCard(name = name)
        CounterChip()
        PulseLoader()
        RatingStars()
    }
}

@Preview(showBackground = true)
@Composable
private fun FullShowcasePreview() {
    FullShowcase(name = "JitPack")
}
