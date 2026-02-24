package co.com.alexis.rickandmortyapp.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.com.alexis.rickandmortyapp.R
import co.com.alexis.rickandmortyapp.domain.model.Character
import co.com.alexis.rickandmortyapp.ui.component.SpacerComponent
import co.com.alexis.rickandmortyapp.ui.theme.Typography

@Composable
fun ItemCharacter(
    modifier: Modifier = Modifier,
    character: Character
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Image(
                    modifier = Modifier.size(90.dp),
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null
                )
                SpacerComponent(10)
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SpacerComponent(10)
                        Text(
                            text = character.name,
                            style = Typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    SpacerComponent(10)
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SpacerComponent(10)
                        Text(
                            text = character.status,
                            style = Typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemCharacterPreview() {
    ItemCharacter(
        character = Character(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            image = ""
        )
    )
}