package ru.nm17.narodmon.ui.bottomSheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nm17.narodmon.R
import ru.nm17.narodmon.ui.entities.SensorFilterUiEntity
import ru.nm17.narodmon.ui.pages.FilterCheckbox
import ru.nm17.narodmon.ui.theme.NarodMonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSensorsBottomSheet(
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

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest.invoke() },
        sheetState = SheetState(true)
    ) {
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
        Box(contentAlignment = Alignment.BottomCenter) {
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
            Button(
                onClick = { onApply.invoke(filterItems) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 64.dp)
            ) {
                Text(text = stringResource(id = R.string.apply))
            }
        }

    }
}

@Preview
@Composable
fun PreviewSensorFilterBottomSheet() {
    NarodMonTheme {
        FilterSensorsBottomSheet({}) {

        }
    }
}