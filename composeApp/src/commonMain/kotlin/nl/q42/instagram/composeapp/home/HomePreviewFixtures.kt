package nl.q42.instagram.composeapp.home

private val previewFeedItems = listOf(
    FeedItemViewState(
        authorName = "nature._.images",
        authorDescription = "Cute Animals",
        authorImage = FeedImageKey.AuthorNature,
        postDescription = "What is your favourite image?",
        postImage = FeedImageKey.PostNature,
        numberOfLikes = 2_374_585,
    ),
    FeedItemViewState(
        authorName = "cats_online",
        authorDescription = "Cats!",
        authorImage = FeedImageKey.AuthorCat,
        postDescription = "Awww, look at these kitties",
        postImage = FeedImageKey.PostCat,
        numberOfLikes = 133_742,
    ),
)

private val previewSingleFeedItem = FeedItemViewState(
    authorName = "puppers",
    authorDescription = "Awooo!",
    authorImage = FeedImageKey.AuthorDog,
    postDescription = "Woof!",
    postImage = FeedImageKey.PostDog,
    numberOfLikes = 42_424_242,
)

private val previewMixedPermissionsItem = FeedItemViewState(
    authorName = "follow_disabled",
    authorDescription = "Permission sample",
    authorImage = FeedImageKey.AuthorCat,
    postDescription = "Interaction visibility sample",
    postImage = FeedImageKey.PostNature,
    numberOfLikes = 7,
    canFollow = false,
    canLike = false,
)

internal val previewHomeWithContent = HomeViewState(feedItems = previewFeedItems)
internal val previewHomeEmpty = HomeViewState(feedItems = emptyList())
internal val previewHomeSingleItem = HomeViewState(feedItems = listOf(previewSingleFeedItem))
internal val previewHomeWithMixedPermissions = HomeViewState(
    feedItems = listOf(previewFeedItems.first(), previewMixedPermissionsItem)
)
