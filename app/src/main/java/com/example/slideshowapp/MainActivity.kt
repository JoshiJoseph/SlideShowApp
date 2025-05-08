package com.example.slideshowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.slideshowapp.ui.theme.SlideShowAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideShowAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SlideshowScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SlideshowScreen(modifier: Modifier = Modifier) {
    val images = listOf(
        R.drawable.marcus1,
        R.drawable.marcus2,
        R.drawable.marcus3,
        R.drawable.marcus4,
        R.drawable.marcus5
    )
    val captions = listOf(
        "Quote 1 from Marcus Aurelius",
        "Quote 2 from Marcus Aurelius",
        "Quote 3 from Marcus Aurelius",
        "Quote 4 from Marcus Aurelius",
        "Quote 5 from Marcus Aurelius"
    )
    var index by remember { mutableStateOf(0) }
    var input by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = captions[index])
        Spacer(modifier = Modifier.height(12.dp))
        Image(
            painter = painterResource(images[index]),
            contentDescription = "Slide ${index + 1}",
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Button(onClick = {
                index = if (index == 0) images.lastIndex else index - 1
            }) {
                Text("Back")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                index = if (index == images.lastIndex) 0 else index + 1
            }) {
                Text("Next")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Image #") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val num = input.toIntOrNull()
            if (num != null && num in 1..images.size) {
                index = num - 1
            }
        }) {
            Text("Go")
        }
    }
}
