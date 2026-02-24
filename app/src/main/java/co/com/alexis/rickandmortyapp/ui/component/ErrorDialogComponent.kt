package co.com.alexis.rickandmortyapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.com.alexis.rickandmortyapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorDialogComponent(
    error: ErrorDialog,
    onDismiss: () -> Unit
) {
    BasicAlertDialog(onDismissRequest = onDismiss) {
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                error.message?.let {
                    Text(
                        text = it,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                Button(onClick = {
                    error.onRetry?.invoke()
                    onDismiss()
                }) {
                    Text(text = stringResource(R.string.label_retry))
                }
            }

        }

    }
}

@Composable
@Preview(showBackground = true)
private fun ErrorDialogComponentPreview() {
    ErrorDialogComponent(
        error = ErrorDialog(
            message = "description"
        ),
        onDismiss = {}
    )
}