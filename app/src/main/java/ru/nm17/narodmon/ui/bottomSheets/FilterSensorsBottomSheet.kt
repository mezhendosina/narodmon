package ru.nm17.narodmon.ui.bottomSheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nm17.narodmon.R
import ru.nm17.narodmon.ui.entities.SensorFilterUiEntity
import ru.nm17.narodmon.ui.pages.FilterCheckbox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSensorsBottomSheet(
    filterItems: List<SensorFilterUiEntity>,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = { onDismissRequest.invoke() }) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.sensors_filter_title),
                fontSize = 24.sp,
                fontWeight = FontWeight(500),
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxWidth(),
        ) {
            items(filterItems) {
                FilterCheckbox(
                    checked = it.enabled.value,
                    stringRes = it.stringRes,
                ) { it.enabled.value = !it.enabled.value }
            }
        }
    }
}