package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun ImageViewerComponent(
    modifier: Modifier = Modifier,
    image: Painter,

    shape: RoundedCornerShape? = null

) {
    Image(
        painter = image,
        contentDescription = "image",
        modifier = modifier
            .then(if (shape != null) Modifier.clip(shape) else Modifier),

        )
}