package nl.q42.instagram.ui.homeContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.q42.instagram.ui.HomeScaffold
import nl.q42.instagram.ui.data.dummyViewState
import nl.q42.instagram.ui.theme.AppTheme

data class HomeViewState(
    val feedItems: List<FeedItemViewState>
)

/**
 * Composable for the entire content of the Home Screen.
 * A list of InstAnimal posts will be displayed here.
 */
@Composable
fun HomeContent(viewState: HomeViewState) {
    val firstItem = viewState.feedItems.first()
    AnimalFeedItem(feedItem = firstItem)
}


/**
 * Composable for an individual InstAnimal feedItem.
 * One Individual  Post. You can create your onw card here!
 *
 *
 * It should probably show the following:
 * The image!
 * The post description
 *
 * It can also show the following (if you want):
 * The author of the post, including a profile picture
 * The amount of likes
 * More?
 */
@Composable
fun AnimalFeedItem(feedItem: FeedItemViewState) {
    Column() {
        Text(
            text = "This is a InstAnimal post!",
            style = MaterialTheme.typography.titleLarge,
        )
        Text("(It has ${feedItem.numberOfLikes} likes)")
    }
}


//Preview for the individual feed item
@Composable
@Preview(showBackground = true)
fun AnimalFeedItemPreview() {
    val firstItem = dummyViewState.feedItems.first()
    AppTheme {
        AnimalFeedItem(feedItem = firstItem)
    }
}


//Preview for the entire home screen!
@Composable
@Preview(showBackground = true, widthDp = 300, heightDp = 500)
private fun HomeContentPreview() {
    AppTheme {
        HomeScaffold()
    }
}
