package ru.nm17.narodmon.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nm17.narodmon.R
import ru.nm17.narodmon.ui.elements.FilterRadioButton
import ru.nm17.narodmon.ui.entities.SensorSortingUiEntity
import ru.nm17.narodmon.ui.entities.SortingTypes
import ru.nm17.narodmon.ui.theme.NarodMonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortSensorsDialog(
    selected: SensorSortingUiEntity,
    sortingTypes: List<SensorSortingUiEntity>? = null,
    onApply: (sortingType: SensorSortingUiEntity) -> Unit,
    onDismissRequest: () -> Unit
) {
    var selectedType by remember {
        mutableStateOf(selected)
    }
    val sensorsSortingTypes = listOf(
        SensorSortingUiEntity(R.string.sort_by_name, SortingTypes.NAME),
        SensorSortingUiEntity(R.string.sort_by_name_desc, SortingTypes.NAME_DESC),
        SensorSortingUiEntity(R.string.sort_by_distance, SortingTypes.DISTANCE),
        SensorSortingUiEntity(R.string.sort_by_distance_desc, SortingTypes.DISTANCE_DESC),
        SensorSortingUiEntity(R.string.sort_by_type, SortingTypes.TYPE),
        SensorSortingUiEntity(R.string.sort_by_type_desc, SortingTypes.TYPE_DESC),
        SensorSortingUiEntity(R.string.sort_update_time, SortingTypes.UPD_TIME),
    )




    AlertDialog(
        onDismissRequest = {
            onDismissRequest.invoke()
        }) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Text(
                    text = stringResource(id = R.string.sensors_sort_title),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                ) {
                    items(sortingTypes ?: sensorsSortingTypes) {
                        FilterRadioButton(
                            selected = (selectedType == it),
                            onClick = { selectedType = it },
                            stringRes = it.stringRes,
                        )
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
                    Button(onClick = { onApply.invoke(selectedType) }) {
                        Text(text = stringResource(id = R.string.apply))
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun SortSensorsPreview() {
    NarodMonTheme {
        SortSensorsDialog(
            SensorSortingUiEntity(R.string.sort_by_distance, SortingTypes.DISTANCE),
            onApply = {}) {}
    }
}