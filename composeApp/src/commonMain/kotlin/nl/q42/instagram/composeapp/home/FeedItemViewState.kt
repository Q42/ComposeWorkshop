package nl.q42.instagram.composeapp.home

data class FeedItemViewState(
    val authorName: String,
    val authorDescription: String,
    val authorImage: FeedImageKey,
    val postDescription: String,
    val postImage: FeedImageKey,
    val numberOfLikes: Int,
    val canFollow: Boolean = true,
    val canLike: Boolean = true,
)

