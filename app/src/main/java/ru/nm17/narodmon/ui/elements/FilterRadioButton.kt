package ru.nm17.narodmon.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@ExperimentalMaterial3Api
@Composable
fun FilterRadioButton(selected: Boolean, onClick: () -> Unit, stringRes: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() }
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
        )
        Text(text = stringResource(id = stringRes))
    }
}