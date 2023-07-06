package com.example.compose_app
// https://developer.android.com/jetpack/compose/tutorial
import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose_app.ui.theme.Compose_AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GmailScreen() {
    val scope = rememberCoroutineScope()
    val snackBarstate = remember {
        SnackbarHostState()
    }
    val bottomBarTabs = listOf<String>("Home", "Meet")
    val bottomBarIcons = listOf(Icons.Default.Home, Icons.Default.Person)
    // selected index variable
    val selectedIndex = remember {
        mutableStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Compose") },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Compose email button"
                    )
                },
                onClick = {
                    scope.launch { snackBarstate.showSnackbar("Clicked FAB") }
                })

        },
        bottomBar = {
            NavigationBar() {
                bottomBarTabs.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex.value == index,
                        icon = {
                            Icon(
                                imageVector = bottomBarIcons[index],
                                contentDescription = item.toString()
                            )
                        },
                        label = { Text(text = item) },
                        onClick = { selectedIndex.value = index })

                }
            }
        }
    ) {
        val myMessage = Message("John", "Did you get those items you saught?")
        Compose_AppTheme() {
            Surface(modifier = Modifier.fillMaxSize()) {
                //EmailCard(msg = myMessage)
                Conversation(messages = SampleData.conversationSample)
            }
        }
    }
}

// class object for a message
data class Message(val author: String, val body: String)

@Composable
fun EmailCard(msg: Message) {
    // by using "by" property we get the actual variable type
    var isExpanded by remember {
        mutableStateOf(false)
    }
    // get smooth color change
    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surface
    )
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.pic),
            contentDescription = "Image Avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(
                    width = 1.5.dp,
                    color = MaterialTheme.colorScheme.primary
                )
        )
        Spacer(
            modifier = Modifier
                .width(8.dp)
        )
        Column(
            modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    // smooth container size re-shape
                    // like AnimatedContainer flutter
                    .animateContentSize()
                    .padding(1.dp)

            ) {
                Text(
                    text = msg.body,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages.size) {
            EmailCard(msg = messages[it])
        }
    }
}