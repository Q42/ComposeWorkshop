package nl.q42.instagram.composeapp.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun HomeScreen(
    viewState: HomeViewState,
    onInteraction: (HomeInteractionEvent) -> Unit = {},
    onActionResult: (HomeActionResult) -> Unit = {},
) {
    var handledEventCount by remember { mutableIntStateOf(0) }

    HomeContent(
        viewState = viewState,
        onInteraction = { event ->
            handledEventCount += 1
            onInteraction(event)

            val result =
                when (event) {
                    is HomeInteractionEvent.LikeClicked -> {
                        HomeActionResult.LikeHandled(
                            authorName = event.item.authorName,
                            eventCount = handledEventCount,
                        )
                    }

                    is HomeInteractionEvent.FollowClicked -> {
                        HomeActionResult.FollowHandled(
                            authorName = event.item.authorName,
                            eventCount = handledEventCount,
                        )
                    }
                }

            onActionResult(result)
        },
    )
}
