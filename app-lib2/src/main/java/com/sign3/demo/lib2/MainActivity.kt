package com.sign3.demo.lib2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sign3.lib2.CounterChip

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lib2Screen()
        }
    }
}

@Composable
fun Lib2Screen(modifier: Modifier = Modifier) {
    MaterialTheme {
        Surface(modifier = modifier.fillMaxSize()) {
            CounterChip(modifier = Modifier.wrapContentSize())
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Lib2ScreenPreview() {
    Lib2Screen()
}
