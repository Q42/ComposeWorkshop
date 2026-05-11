package nl.q42.instagram.composeapp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

sealed interface HomeInteractionEvent {
    data class LikeClicked(
        val item: FeedItemViewState,
    ) : HomeInteractionEvent

    data class FollowClicked(
        val item: FeedItemViewState,
    ) : HomeInteractionEvent
}

sealed interface HomeActionResult {
    data class LikeHandled(
        val authorName: String,
        val eventCount: Int,
    ) : HomeActionResult

    data class FollowHandled(
        val authorName: String,
        val eventCount: Int,
    ) : HomeActionResult
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    viewState: HomeViewState,
    onInteraction: (HomeInteractionEvent) -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewState.statusMessage) {
        viewState.statusMessage?.let { snackbarHostState.showSnackbar(it) }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Instagram") },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        if (viewState.feedItems.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center,
            ) {
                Text("No posts yet.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(viewState.feedItems) { item ->
                    FeedMetadataItem(
                        item = item,
                        onInteraction = onInteraction,
                    )
                }
            }
        }
    }
}

@Composable
private fun FeedMetadataItem(
    item: FeedItemViewState,
    onInteraction: (HomeInteractionEvent) -> Unit,
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            // Author row
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(item.authorImage.drawableResource),
                    contentDescription = item.authorName,
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .size(36.dp)
                            .clip(CircleShape),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(item.authorName, style = MaterialTheme.typography.labelLarge)
                    Text(item.authorDescription, style = MaterialTheme.typography.bodySmall)
                }
            }

            HorizontalDivider()

            // Post image
            Image(
                painter = painterResource(item.postImage.drawableResource),
                contentDescription = item.postDescription,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
            )

            // Post description and likes
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                Text(item.postDescription, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${item.numberOfLikes} likes",
                    style = MaterialTheme.typography.labelMedium,
                )
            }

            HorizontalDivider()

            // Actions row
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Button(
                    onClick = { onInteraction(HomeInteractionEvent.LikeClicked(item)) },
                    enabled = item.canLike,
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Like")
                }
                OutlinedButton(
                    onClick = { onInteraction(HomeInteractionEvent.FollowClicked(item)) },
                    enabled = item.canFollow,
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Follow")
                }
            }
        }
    }
}
