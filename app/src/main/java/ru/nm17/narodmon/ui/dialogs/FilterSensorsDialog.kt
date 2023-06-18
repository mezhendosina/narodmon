package ru.nm17.narodmon.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nm17.narodmon.R
import ru.nm17.narodmon.ui.elements.FilterCheckbox
import ru.nm17.narodmon.ui.entities.SensorFilterUiEntity
import ru.nm17.narodmon.ui.theme.NarodMonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSensorsDialog(
    onApply: (filters: List<SensorFilterUiEntity>) -> Unit,
    onDismissRequest: () -> Unit
) {
    val filterItems = remember {
        listOf(
            /* TODO:
             *  Заменить `code` на настоящее значение
             *  либо динамически его подгружать из ответа АПИ
             *  (см. /appInit, ключ в жсоне: types.type) */
            SensorFilterUiEntity(R.string.filter_temp, 0),
            SensorFilterUiEntity(R.string.filter_temp_water, 1),
            SensorFilterUiEntity(R.string.filter_temp_ground, 2),
            SensorFilterUiEntity(R.string.filter_temp_dew_point, 3),
            SensorFilterUiEntity(R.string.filter_humidity, 4),
            SensorFilterUiEntity(R.string.filter_pressure, 5),
            SensorFilterUiEntity(R.string.filter_lightness, 6),
            SensorFilterUiEntity(R.string.filter_uv, 7),
            SensorFilterUiEntity(R.string.filter_radiation, 8),
            SensorFilterUiEntity(R.string.filter_rainfall, 9),
            SensorFilterUiEntity(R.string.filter_dust, 10),
            SensorFilterUiEntity(R.string.filter_wind_speed, 11),
            SensorFilterUiEntity(R.string.filter_wind_direction, 12),
            SensorFilterUiEntity(R.string.filter_concentration, 13),
            SensorFilterUiEntity(R.string.filter_power, 14),
            SensorFilterUiEntity(R.string.filter_voltage, 15),
            SensorFilterUiEntity(R.string.filter_amperage, 16),
            SensorFilterUiEntity(R.string.filter_energy, 17),
            SensorFilterUiEntity(R.string.filter_battery, 18),
            SensorFilterUiEntity(R.string.filter_rxtx, 19),
            SensorFilterUiEntity(R.string.filter_signal, 20),
            SensorFilterUiEntity(R.string.filter_water_meter, 21),
            SensorFilterUiEntity(R.string.filter_time, 22),
        )
    }


    AlertDialog(
        onDismissRequest = {
            onDismissRequest.invoke()
        }) {
        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Text(
                    text = stringResource(id = R.string.sensors_filter_title),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(128.dp, 312.dp)
                        .padding(horizontal = 4.dp),
                ) {
                    items(filterItems) {
                        FilterCheckbox(
                            checked = it.enabled.value,
                            stringRes = it.stringRes,
                        ) { it.enabled.value = !it.enabled.value }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismissRequest.invoke() }) {
                        Text(text = "Отмена")
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                    Button(onClick = { onApply.invoke(filterItems) }) {
                        Text(text = stringResource(id = R.string.apply))
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewFilterSensorsDialog() {
    NarodMonTheme {
        FilterSensorsDialog({}) {}
    }
}