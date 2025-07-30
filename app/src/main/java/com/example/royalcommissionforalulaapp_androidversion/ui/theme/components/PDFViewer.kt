package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun PdfViewer(pdfUrl: String) {
    val context = LocalContext.current
    val webView = remember { WebView(context) }

    AndroidView(
        factory = {
            webView.apply {
                settings.javaScriptEnabled = true
                settings.setSupportZoom(true)
                settings.builtInZoomControls = true
                settings.displayZoomControls = false
                loadUrl("https://docs.google.com/gview?embedded=true&url=$pdfUrl")
            }
        },
        update = { view ->
            view.loadUrl("https://docs.google.com/gview?embedded=true&url=$pdfUrl")
        },
        modifier = Modifier.fillMaxSize()
    )
}