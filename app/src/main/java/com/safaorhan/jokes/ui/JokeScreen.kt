package com.safaorhan.jokes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.safaorhan.jokes.JokeState

@Composable
fun JokeScreen(uiState: JokeState?, onRefreshJokeClick: () -> Unit) = uiState?.let {
    Column {
        TopAppBar(title = { Text(text = "Jokes") })

        Column(modifier = Modifier.padding(all = 16.dp)) {
            Text(text = it.joke.orEmpty(), fontSize = 24.sp)

            if (it.error != null) {
                Text(
                    text = it.error,
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

            if (it.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(CenterHorizontally)
                )
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
