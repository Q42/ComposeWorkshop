package nl.q42.instagram.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import nl.q42.instagram.R

@Composable
fun Love() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.love))
    LottieAnimation(composition, iterations = Int.MAX_VALUE)
}