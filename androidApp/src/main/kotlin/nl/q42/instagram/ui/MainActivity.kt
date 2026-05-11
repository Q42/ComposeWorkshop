package nl.q42.instagram.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nl.q42.instagram.ui.data.dummyViewState
import nl.q42.instagram.ui.home.Home
import nl.q42.instagram.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Home(dummyViewState)
            }
        }
    }
}
