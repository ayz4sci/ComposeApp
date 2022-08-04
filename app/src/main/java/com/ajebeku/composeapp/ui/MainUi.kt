package com.ajebeku.composeapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ajebeku.composeapp.models.Concert
import com.ajebeku.composeapp.models.Event
import com.ajebeku.composeapp.repository.ConcertRepositoryApi
import com.ajebeku.composeapp.ui.theme.ComposeAppTheme
import com.ajebeku.composeapp.ui.viewModel.EventViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MainUiApp(viewModel: EventViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(color = MaterialTheme.colors.background) {
        Column {
            SearchBox(
                uiState.searchByCity,
                uiState.searchByPrice,
                viewModel::onCityTextChanged,
                viewModel::onPriceTextChanged,
                viewModel::fetchEventsBy
            )
            ListItems(uiState.events)
        }
    }
}

@Composable
fun SearchBox(
    city: String,
    price: String,
    onCityChanged: (String) -> Unit,
    onPriceChanged: (String) -> Unit,
    onSearchClicked: () -> Unit
) {
    Surface(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 4.dp)
        ) {
            TextField(
                value = city,
                onValueChange = onCityChanged,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(1f),
                label = { Text("City:") }
            )
            TextField(
                value = price,
                onValueChange = onPriceChanged,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(1f),
                label = { Text("Price:") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(
                onClick = onSearchClicked,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Search")
            }
        }
    }
}

@Composable
fun ListItems(events: List<Event>) {
    LazyColumn {
        items(items = events) {
            ListItem(it)
        }
    }
}

@Composable
fun ListItem(event: Event) {
    Card(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(1f)
        ) {
            Text(text = "Artiste: ${event.name}")
            Text(text = "City: ${event.city}")
            Text(text = "Price: ${event.price}")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 720)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
        MainUiApp(EventViewModel(object : ConcertRepositoryApi {
            override fun fetchConcerts(): Concert {
                return Concert(1, "Sample",
                    events = (1..10).map {
                        Event(it, name = "Name$it", city = "City$it", price = it)
                    })
            }
        }))
    }
}