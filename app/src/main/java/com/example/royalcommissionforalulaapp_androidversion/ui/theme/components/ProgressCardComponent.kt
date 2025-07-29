package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R




@Composable
fun CircularProgressView(progress: Double) {
    val percentage = progress.coerceIn(0.0, 100.0)
    val mainColor = colorResource(R.color.main_color)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(80.dp)
            .padding(8.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 15f
            size.minDimension / 2
            val topLeft = Offset(strokeWidth / 2, strokeWidth / 2)
            val size = Size(size.width - strokeWidth, size.height - strokeWidth)

            drawArc(
                color = Color.Gray.copy(alpha = 0.2f),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = size,
                style = Stroke(width = strokeWidth),

            )

            drawArc(
                color = mainColor,
                startAngle = -90f,
                sweepAngle = (360f * (percentage / 100f)).toFloat(),
                useCenter = false,
                topLeft = topLeft,
                size = size,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        Text(
            text = "${percentage.toInt()}%",
            color = mainColor,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun LabeledCircularProgressView (
    modifier: Modifier = Modifier,
    progressDescription: String,
    title: String,
    progress: Double
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        ReusableTextComponent(
            text = title,
            fontFamily = FontFamily(Font(R.font.text_medium)),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        CircularProgressView(progress= progress)

        ReusableTextComponent(
            text = progressDescription,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
    }
}



@Composable
fun TotalProgressView(
    modifier: Modifier = Modifier,
    total: Int,
    available: Int,
    perc1: Double,
    perc2: Double
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
       // horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LabeledCircularProgressView(
            title = "Total Scope",
            progressDescription = "${perc1.toInt()}% completed out of $total total buildings",
            progress = perc1,
            modifier = Modifier
                .weight(1f)
        )

        LabeledCircularProgressView(
            title = "Available Buildings",
            progressDescription = "${perc2.toInt()}% completed out of $available available buildings",
            progress = perc2,
            modifier = Modifier
                .weight(1f)
        )
    }
}


@Composable
fun CardView(
    name: String,
    total: Int,
    available: Int,
    perc1: Double,
    perc2: Double
) {
    val cardWidth = LocalConfiguration.current.screenWidthDp.dp - 40.dp
    val backgroundColor = colorResource(R.color.secondary_color)

    Column(
        modifier = Modifier
            .width(cardWidth)
            .background(backgroundColor, shape = RoundedCornerShape(15.dp))
    ) {
        ReusableTextComponent(
            text = name,
            fontFamily = FontFamily(Font(R.font.text_bold)),
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            textAlign = TextAlign.Center
        )

        TotalProgressView(
            total = total,
            available = available,
            perc1 = perc1,
            perc2 = perc2
        )
    }
}



@Preview
@Composable
fun LabeledCircularProgressViewPreview(modifier: Modifier = Modifier) {
    LabeledCircularProgressView(
        progressDescription = "0% completed out of 93 of total builldings",
        title = "initial report ",
        progress = 90.0
    )

}

@Preview
@Composable
fun TotalProgressViewPreview(modifier: Modifier = Modifier) {
    TotalProgressView(
        total = 20,
        available = 100,
        perc1 = 20.0,
        perc2 = 50.0
    )

}

@Preview
@Composable
fun CircularProgressViewPreview (modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressView(progress = 0.0)
    }
}


@Preview
@Composable
fun CardViewPreview() {
    CardView(
        name = "Buildings",
        total = 100,
        available = 65,
        perc1 = 65.0,
        perc2 = 35.0
    )
}














