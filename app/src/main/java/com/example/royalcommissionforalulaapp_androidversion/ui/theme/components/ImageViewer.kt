package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.example.royalcommissionforalulaapp_androidversion.utilities.Utilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL


@Composable
fun ImageViewer(imageUrl: String) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        try {
            val file = withContext(Dispatchers.IO) {
                Utilities.downloadImageToCache(context, imageUrl)
            }

            val uri = FileProvider.getUriForFile(
                context,
                "com.example.royalcommissionforalulaapp_androidversion.provider",
                file
            )

            Log.d("ImageV", "Opening local URI: $uri")

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "image/*")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            val chooser = Intent.createChooser(intent, "Open image with...")
            context.startActivity(chooser)

        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "No image viewer found", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}






/*
@Composable
fun ImageViewer(imageUrl: String) {
    var scale by remember { mutableFloatStateOf(1f) }
    val offset by remember { mutableStateOf(Offset.Zero) }
    var isLoading by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(1f, 5f)
                }
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            onState = { isLoading = it is AsyncImagePainter.State.Loading},

            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
                .fillMaxSize()

        )

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}

*/



