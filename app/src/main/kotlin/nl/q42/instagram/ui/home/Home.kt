package nl.q42.instagram.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.q42.instagram.ui.data.dummyViewState
import nl.q42.instagram.ui.theme.AppTheme
import java.text.NumberFormat

data class HomeViewState(
    val feedItems: List<FeedItemViewState>
)

@Composable
fun Home(viewState: HomeViewState) {
    Column {
        Header()
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp)
        ) {
            viewState.feedItems.forEach { item ->
                FeedItem(item)
            }
        }
    }
}

@Composable
fun FeedItem(item: FeedItemViewState) {
    val favoriteState = remember { mutableStateOf<Boolean>(false) }
    AuthorDetails(item)
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = painterResource(item.postImageId),
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
    IconButton(
        onClick = { favoriteState.value = !favoriteState.value }
    ) {
        Icon(
            imageVector = if (favoriteState.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            tint = if (favoriteState.value) Color.Red else Color.Black,
            contentDescription = ""
        )
    }
    Text(
        text = NumberFormat.getIntegerInstance().format(
            item.numberOfLikes
        )
    )
    Text(text = item.postDescription)
    HorizontalDivider()
}

@Composable
fun AuthorDetails(item: FeedItemViewState) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp, brush =
                        Brush.linearGradient(
                            colors = listOf(
                                Color.Blue,
                                Color.Red
                            )
                        ), shape = CircleShape
                ),
            painter = painterResource(item.authorImage),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(0.7f)
        ) {
            Text(text = item.authorName)
            Text(text = item.authorDescription)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HomePreview() {
    AppTheme {
        Home(viewState = dummyViewState)
    }
}