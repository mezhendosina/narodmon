package ru.nm17.narodmon.ui.webCamsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.nm17.narodmon.ui.iosevkaFamily

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WebCamItem(webCamEntity: WebCamUiEntity) {
    Box(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        GlideImage(
            model = webCamEntity.imageUrl,
            contentDescription = webCamEntity.name,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .height(240.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                )
                .background(Color(0f, 0f, 0f, 0.55f))
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = webCamEntity.time,
                    color = Color.White,
                    fontFamily = iosevkaFamily
                )
                Text(
                    text = webCamEntity.name,
                    color = Color.White,
                    maxLines = 1,
                    fontFamily = iosevkaFamily
                )

            }
            Text(
                text = "${webCamEntity.distance} км",
                color = Color.White,
                fontFamily = iosevkaFamily,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
    }
}