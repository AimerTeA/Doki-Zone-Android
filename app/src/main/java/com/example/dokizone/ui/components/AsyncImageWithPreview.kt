package com.example.dokizone.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter.State
import com.example.dokizone.ui.utils.testImage

@Composable
fun AsyncImageWithPreview(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    error: Painter? = null,
    fallback: Painter? = error,
    onLoading: ((State.Loading) -> Unit)? = null,
    onSuccess: ((State.Success) -> Unit)? = null,
    onError: ((State.Error) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DefaultFilterQuality,
    clipToBounds: Boolean = true,
    imagePreviewPainter: Painter? = testImage()
) {
    if (LocalInspectionMode.current && imagePreviewPainter != null) {
        Image(
            painter = imagePreviewPainter,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    } else {
        AsyncImage(
            model = model,
            contentDescription = contentDescription,
            modifier = modifier,
            placeholder = placeholder,
            error = error,
            fallback = fallback,
            onLoading = onLoading,
            onSuccess = onSuccess,
            onError = onError,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
            filterQuality = filterQuality,
            clipToBounds = clipToBounds
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun AsyncImageWithPreviewPreview() {
    AsyncImageWithPreview(
        model = "https://example.com/image.jpg",
        contentDescription = "Example Image",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}