package ru.nm17.narodmon.ui.bottomSheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nm17.narodmon.R
import ru.nm17.narodmon.ui.entities.SensorSortingUiEntity
import ru.nm17.narodmon.ui.entities.SortingTypes
import ru.nm17.narodmon.ui.pages.FilterRadioButton
import ru.nm17.narodmon.ui.theme.NarodMonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortSensorsBottomSheet(
    onApply: (sortingType: SortingTypes) -> Unit,
    onDismissRequest: () -> Unit
) {
    var sortingType by remember { mutableStateOf(SortingTypes.DISTANCE) }

    val sortingTypes = remember {
        listOf(
            SensorSortingUiEntity(R.string.sort_by_name, SortingTypes.NAME),
            SensorSortingUiEntity(R.string.sort_by_name_desc, SortingTypes.NAME_DESC),
            SensorSortingUiEntity(R.string.sort_by_distance, SortingTypes.DISTANCE),
            SensorSortingUiEntity(R.string.sort_by_distance_desc, SortingTypes.DISTANCE_DESC),
            SensorSortingUiEntity(R.string.sort_by_type, SortingTypes.TYPE),
            SensorSortingUiEntity(R.string.sort_by_type_desc, SortingTypes.TYPE_DESC),
            SensorSortingUiEntity(R.string.sort_update_time, SortingTypes.UPD_TIME),
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
                text = stringResource(R.string.sensors_sort_title),
                fontSize = 24.sp,
                fontWeight = FontWeight(500),
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxWidth(),
        ) {
            items(sortingTypes) {
                FilterRadioButton(
                    selected = (sortingType == it.sortingType),
                    onClick = { sortingType = it.sortingType },
                    stringRes = it.stringRes,
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp, vertical = 8.dp),
            onClick = {
                onApply.invoke(sortingType)
            }) {
            Text(text = stringResource(R.string.apply))
        }
    }
}

@Preview
@Composable
fun PreviewSortBottomSheet() {
    NarodMonTheme {
        SortSensorsBottomSheet(onApply = {}) {}
    }
}