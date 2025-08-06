package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL


@Composable
fun PdfViewer(pdfUrl: String) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(Uri.parse(pdfUrl), "application/pdf")
                flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            }
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                context,
                "No PDF viewer application found",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}



/*
@Composable
fun PdfViewer(pdfUrl: String) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }

    rememberVerticalVueReaderState(
        resource = VueResourceType.Remote(
            "https://drive.google.com/uc?export=download&id=1DSA7cmFzqCtTsHhlB0xdYJ6UweuC8IOz",
            fileType = VueFileType.PDF
        )
    )


    Box(modifier = Modifier.fillMaxSize()) {

        AndroidView(
            factory = {
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.setSupportZoom(true)
                    settings.builtInZoomControls = true
                    settings.displayZoomControls = false


                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                            isLoading = true
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            isLoading = false
                        }
                    }

                    loadUrl("https://docs.google.com/gview?embedded=true&url=$pdfUrl")
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        )

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}

 */