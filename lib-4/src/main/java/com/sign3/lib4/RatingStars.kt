package com.sign3.lib4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp

/**
 * Lib-4 widget: a tappable 5-star rating bar.
 */
@Composable
fun RatingStars(
    modifier: Modifier = Modifier,
    maxStars: Int = 5,
    initialRating: Int = 3,
) {
    var rating by rememberSaveable { mutableIntStateOf(initialRating) }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(maxStars) { index ->
                Text(
                    text = if (index < rating) "★" else "☆",
                    fontSize = 28.sp,
                    color = if (index < rating) Color(0xFF7B1FA2) else Color(0xFFB39DDB),
                    modifier = Modifier.clickable { rating = index + 1 },
                )
            }
        }
        Text(
            text = "lib-4 rating: $rating/$maxStars",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF7B1FA2),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingStarsPreview() {
    RatingStars()
}
