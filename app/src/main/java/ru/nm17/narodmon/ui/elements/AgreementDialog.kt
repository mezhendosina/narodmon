@file:OptIn(ExperimentalMaterial3Api::class)

package ru.nm17.narodmon.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalUriHandler
import ru.nm17.narodmon.R
import kotlin.system.exitProcess

@Composable
fun AgreementDialog(onClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current

    AlertDialog(
        onDismissRequest = { /* Ничего не делаем пока пользователь не примет соглашение. */ },
        title = { Text(text = stringResource(id = R.string.agreement_dialog_title)) },
        text = {
            Column {
                Text(text = stringResource(id = R.string.agreement_dialog_text))
                Divider(Modifier.padding(vertical = 8.dp))
                ListItem(
                    headlineContent = {
                        Text(
                            text = stringResource(id = R.string.privacy_policy),
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    trailingContent = {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = ""
                        )
                    },
                    modifier = Modifier.clickable { uriHandler.openUri("https://narodmon.ru/#privacy") }
                )

                ListItem(
                    headlineContent = {
                        Text(
                            text = stringResource(id = R.string.user_agreement),
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    trailingContent = {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = ""
                        )
                    },
                    modifier = Modifier.clickable { Modifier.clickable { uriHandler.openUri("https://narodmon.ru/#privacy") } }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = onClick) {
                Text(text = stringResource(id = R.string.accept_agreements))
            }
        },
        dismissButton = {
            TextButton(
                onClick = { exitProcess(0) }) {
                Text(text = stringResource(id = R.string.exit))
            }
        }

    )
}