package com.example.compose_app.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_app.Features

@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()
    val featureMap = listOf<Features>(
        Features(title = "Sleep Meditation"),
        Features(title = "Tips for Sleeping"),
        Features(title = "More Interesting stuff"),
        Features(title = "Essence of sleep")
    )
    LazyColumn(userScrollEnabled = true) {
        item {
            Column(
                modifier = Modifier

                    .background(MaterialTheme.colorScheme.background)
            ) {
                greetSection()
                tabSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
                sectionCard()
                //GridView()
                //featuresSection(features = featureMap)

            }
        }
    }
}

@Composable
fun GridView() {
    val items = listOf(
        "Poem Title",
        "A Sweet Poem",
        "Death be not proud",
        "The journey"
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(3.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items.size) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .aspectRatio(0.8f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                ) {
                    Column() {
                        Text(
                            text = items[it],
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight(400),

                                fontSize = 20.sp
                            ),
                            modifier = Modifier.padding(5.dp),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Lorem ipsum is a name for a common type of placeholder text. Also known as filler or dummy text, this is text copy that serves to fill a space without saying anything meaningful. It's essentially nonsense text that still gives an idea of what real words will look like in the final product",
                            style = MaterialTheme.typography.bodyMedium.copy(),
                            maxLines = 8,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun sectionCard(
    color: Color = Color(0xffef9a9a)
) {
    Card(
        onClick = {},
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier.padding(12.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(15.dp)
                .clip(RoundedCornerShape(8.dp))
//                .background(color)
                .padding(horizontal = 10.dp, vertical = 12.dp)
                .fillMaxWidth(),
        ) {
            Column() {
                Text(
                    text = "Daily Thought",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = "Meditation • 3-10 min",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(
                        CircleShape
                    )
                    .background(color),
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Start",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

@Composable
fun tabSection(
    chips: List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Card(

                modifier = Modifier
                    .padding(
                        8.dp
                    )
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
//                    .background(
//                        if (selectedChipIndex == it) MaterialTheme.colorScheme.primaryContainer
//                        else MaterialTheme.colorScheme.secondary
//                    )

            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = chips[it]
                )
            }
        }
    }

}

@Composable
fun greetSection(
    name: String = "John"
) {
    Row(

        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Good morning $name",
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                text = "Hope you have a great day mate.",
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search contents online",

            modifier = Modifier.size(35.dp)
        )
    }
}




