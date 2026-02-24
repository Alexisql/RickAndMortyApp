package co.com.alexis.rickandmortyapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import co.com.alexis.rickandmortyapp.ui.component.ErrorHandlerContext
import co.com.alexis.rickandmortyapp.ui.home.HomeScreen
import co.com.alexis.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyAppTheme {
                ErrorHandlerContext {
                    HomeScreen()
                }
            }
        }
    }
}