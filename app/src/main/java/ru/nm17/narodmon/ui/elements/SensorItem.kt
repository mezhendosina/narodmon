package ru.nm17.narodmon.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nm17.narodmon.ui.entities.SensorEntity
import ru.nm17.narodmon.ui.iosevkaFamily

@ExperimentalMaterial3Api
@Composable
fun SensorItem(sensorEntity: SensorEntity) {
    ListItem(
        overlineContent = { Text(text = "${sensorEntity.deviceName} от ${sensorEntity.deviceOwner}") },
        headlineContent = { Text(text = sensorEntity.type.name) },
        supportingContent = { Text(text = sensorEntity.name) },
        trailingContent = {
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(text = "${sensorEntity.distance} km")
                Spacer(modifier = Modifier.size(2.dp))
                ElevatedCard(
                    shape = RectangleShape,
                ) {
                    Text(
                        text = "${sensorEntity.value} ${sensorEntity.unit}",
                        fontFamily = iosevkaFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 3.dp, vertical = 1.dp)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewSensorItem() {
//    SensorItem(SensorEntity(0, Se))
}