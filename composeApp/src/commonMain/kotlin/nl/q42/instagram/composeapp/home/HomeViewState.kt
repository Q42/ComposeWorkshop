package nl.q42.instagram.composeapp.home

data class HomeViewState(
    val feedItems: List<FeedItemViewState>,
    val statusMessage: String? = null,
)

val emptyHomeViewState = HomeViewState(feedItems = emptyList())

fun HomeViewState.reduceInteraction(event: HomeInteractionEvent): HomeViewState = when (event) {
    is HomeInteractionEvent.LikeClicked -> copy(
        feedItems = feedItems.map { item ->
            if (item == event.item && item.canLike) {
                item.copy(
                    numberOfLikes = item.numberOfLikes + 1,
                    canLike = false,
                )
            } else {
                item
            }
        }
    )

    is HomeInteractionEvent.FollowClicked -> copy(
        feedItems = feedItems.map { item ->
            if (item == event.item && item.canFollow) {
                item.copy(canFollow = false)
            } else {
                item
            }
        }
    )
}

fun HomeViewState.applyActionResult(result: HomeActionResult): HomeViewState = copy(
    statusMessage = when (result) {
        is HomeActionResult.LikeHandled -> "Liked ${result.authorName} (${result.eventCount})"
        is HomeActionResult.FollowHandled -> "Followed ${result.authorName} (${result.eventCount})"
    }
)

