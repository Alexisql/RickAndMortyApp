package co.com.alexis.rickandmortyapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.com.alexis.rickandmortyapp.R
import coil3.compose.AsyncImage

@Composable
fun LoadImageAsyncComponent(modifier: Modifier = Modifier, url: String) {
    Box(
        modifier = modifier.clip(CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            placeholder = painterResource(R.drawable.ic_placeholder),
            error = painterResource(R.drawable.ic_not_found),
            contentScale = ContentScale.Fit
        )
    }

}