package ru.nm17.narodmon.ui.elements

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nm17.narodmon.R

/**
 * Кнопка, которая нужна для настроек.
 * @param titleId Id заголовка кнопки
 * @param leadingItem Заполнить, когда нужно вставить Composable перед заголовком(например [Icon], [FilterCheckbox] или [Switch]
 */
@Composable
fun SettingsItem(
    @StringRes titleId: Int,
    leadingItem: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    Row(modifier = Modifier.padding(16.dp)) {
        if (leadingItem != null) {
            leadingItem.invoke()
            Spacer(modifier = Modifier.size(16.dp))
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() }) {
            Text(text = stringResource(id = titleId))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsItem() {
    SettingsItem(R.string.about_app) {}
}