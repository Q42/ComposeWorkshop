package nl.q42.instagram.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import nl.q42.instagram.ui.data.dummyViewState
import nl.q42.instagram.ui.home.Home
import nl.q42.instagram.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Scaffold { contentPadding ->
                        Box(Modifier
                            .fillMaxSize()
                            .padding(contentPadding.calculateTopPadding())) {
                            Home(dummyViewState)
                        }
                    }
                }
            }
        }
    }
}
