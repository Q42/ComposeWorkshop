package nl.q42.instagram.ui.data

import nl.q42.instagram.R
import nl.q42.instagram.ui.homeContent.FeedItemViewState
import nl.q42.instagram.ui.homeContent.HomeViewState

val dummyBunnyItemViewState = FeedItemViewState(
    authorName = "nature._.images",
    authorDescription = "Cute Animals",
    authorImage = R.drawable.author_nature,
    postDescription = "What is your favourite image? \uD83D\uDE0D 1,2,3,4 or ?", // TODO search on emoticons: all fonts/android versions?
    postImageId = R.drawable.post_nature,
    numberOfLikes = 2_374_585,
)

val dummyCatItemViewState = FeedItemViewState(
    authorName = "cats_online",
    authorDescription = "Cats!",
    authorImage = R.drawable.cat_profile,
    postDescription = "Awww, look at these kitties",
    postImageId = R.drawable.eli,
    numberOfLikes = 133_742,
)

val dummyDogItemViewState = FeedItemViewState(
    authorName = "puppers",
    authorDescription = "Awooo!",
    authorImage = R.drawable.dog_profile,
    postDescription = "Woof!",
    postImageId = R.drawable.husky,
    numberOfLikes = 42_424_242,
)

val dummyViewState = HomeViewState(
    feedItems = listOf(dummyBunnyItemViewState, dummyCatItemViewState, dummyDogItemViewState)
)