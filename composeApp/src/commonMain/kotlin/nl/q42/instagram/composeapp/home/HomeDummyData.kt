package nl.q42.instagram.composeapp.home

val dummyBunnyItemViewState = FeedItemViewState(
    authorName = "nature._.images",
    authorDescription = "Cute Animals",
    authorImage = FeedImageKey.AuthorNature,
    postDescription = "What is your favourite image? \uD83D\uDE0D 1,2,3,4 or ?",
    postImage = FeedImageKey.PostNature,
    numberOfLikes = 2_374_585,
)

val dummyCatItemViewState = FeedItemViewState(
    authorName = "cats_online",
    authorDescription = "Cats!",
    authorImage = FeedImageKey.AuthorCat,
    postDescription = "Awww, look at these kitties",
    postImage = FeedImageKey.PostCat,
    numberOfLikes = 133_742,
)

val dummyDogItemViewState = FeedItemViewState(
    authorName = "puppers",
    authorDescription = "Awooo!",
    authorImage = FeedImageKey.AuthorDog,
    postDescription = "Woof!",
    postImage = FeedImageKey.PostDog,
    numberOfLikes = 42_424_242,
)

val dummyHomeViewState = HomeViewState(
    feedItems = listOf(dummyBunnyItemViewState, dummyCatItemViewState, dummyDogItemViewState),
)

