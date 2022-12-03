package com.safaorhan.jokes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: JokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val uiState = viewModel.state.collectAsState()
                    JokeScreen(uiState.value, viewModel::onRefreshButtonClick)
                }
            }
        }
    }
}

@Composable
fun JokeScreen(uiState: JokeState, onRefreshJokeClick: () -> Unit) {
    Column {
        TopAppBar(title = { Text(text = "Jokes") })

        Column(modifier = Modifier.padding(all = 16.dp)) {
            Text(text = uiState.joke.orEmpty(), fontSize = 24.sp)

            if (uiState.error != null) {
                Text(
                    text = uiState.error,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp),
                    fontSize = 18.sp
                )
            }

            Button(
                onClick = onRefreshJokeClick,
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Refresh")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            JokeScreen(
                uiState = JokeState(
                    error = "Check your internet connectivity.",
                    joke = "The glass is neither half-full nor half-empty, the glass is twice as big as it needs to be."
                ),
                onRefreshJokeClick = {}
            )
        }
    }
}
