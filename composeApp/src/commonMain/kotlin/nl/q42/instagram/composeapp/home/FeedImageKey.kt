package nl.q42.instagram.composeapp.home

import nl.q42.instagram.composeapp.generated.resources.Res
import nl.q42.instagram.composeapp.generated.resources.author_nature
import nl.q42.instagram.composeapp.generated.resources.cat_profile
import nl.q42.instagram.composeapp.generated.resources.dog_profile
import nl.q42.instagram.composeapp.generated.resources.eli
import nl.q42.instagram.composeapp.generated.resources.husky
import nl.q42.instagram.composeapp.generated.resources.post_nature
import org.jetbrains.compose.resources.DrawableResource

enum class FeedImageKey(val drawableResource: DrawableResource) {
    AuthorNature(Res.drawable.author_nature),
    AuthorCat(Res.drawable.cat_profile),
    AuthorDog(Res.drawable.dog_profile),
    PostNature(Res.drawable.post_nature),
    PostCat(Res.drawable.eli),
    PostDog(Res.drawable.husky),
}
