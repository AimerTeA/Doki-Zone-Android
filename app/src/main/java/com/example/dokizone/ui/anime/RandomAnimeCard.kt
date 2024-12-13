package com.example.dokizone.ui.anime

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.palette.graphics.Palette
import coil3.BitmapImage
import coil3.compose.SubcomposeAsyncImage
import com.example.dokizone.R
import com.example.dokizone.domain.model.AnimeCard
import com.example.dokizone.ui.components.GradientWithBlurBox
import com.example.dokizone.ui.isPhoneInLandscape
import com.example.dokizone.ui.isTablet
import com.example.dokizone.ui.isWideScreenMode
import com.example.dokizone.ui.theme.DokiZoneTheme

@Composable
fun RandomAnimeCard(
    modifier: Modifier = Modifier,
    randomAnime: AnimeCard
) {
    var dominantColor by remember { mutableStateOf(Color.Black) }
    var vibrantColor by remember { mutableStateOf(Color.Black) }
    val gradient = if (isWideScreenMode()) {
        Brush.radialGradient(
            colorStops = arrayOf(
                0f to dominantColor,
                0.65f to vibrantColor,
                .8f to DokiZoneTheme.colorScheme.backgroundColor
            ),
            center = Offset(0f,0f),
            radius = 1000f
        )
    } else {
        Brush.verticalGradient(
            colorStops = arrayOf(
                0f to dominantColor,
                0.5f to vibrantColor,
                .95f to DokiZoneTheme.colorScheme.backgroundColor
            )
        )
    }
    val sizeModifier = when {
        isTablet() -> {
            modifier.height(310.dp)
        }
        isPhoneInLandscape() -> {
            modifier
        }
        else -> {
            modifier.fillMaxWidth()
                .aspectRatio(
                    ratio = 0.7f,
                    matchHeightConstraintsFirst = true
                )
        }
    }
    GradientWithBlurBox(
        modifier = sizeModifier,
        background = gradient
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = DokiZoneTheme.dimens.generalPadding)
                .padding(top = DokiZoneTheme.dimens.generalPadding),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            RandomAnimeImage(
                imageUrl = randomAnime.imageUrl,
                onColorsRetrieved = { dominant, vibrant ->
                    dominantColor = dominant
                    vibrantColor = vibrant
                }
            )
            if (isWideScreenMode()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = randomAnime.title,
                        style = DokiZoneTheme.typography.title,
                        color = DokiZoneTheme.colorScheme.textColor,
                        textAlign = TextAlign.Start
                    )
                    randomAnime.synopsis?.let {
                        Text(
                            text = it,
                            style = DokiZoneTheme.typography.synopsis,
                            color = DokiZoneTheme.colorScheme.textColor,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 7,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RandomAnimeImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    onColorsRetrieved: (Color, Color) -> Unit = { _, _ -> }
) {
    val contentScale = if (isWideScreenMode()) {
        ContentScale.FillHeight
    } else {
        ContentScale.FillWidth
    }
    val imageModifier = when {
        isTablet() -> {
            modifier.height(300.dp)
        }
        isPhoneInLandscape() -> {
            modifier.fillMaxHeight(.95f)
        }
        else -> {
            modifier.fillMaxWidth()
        }
    }.clip(shape = DokiZoneTheme.shapes.imageShape)

    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = imageModifier,
        clipToBounds = true,
        error = {
            if (LocalInspectionMode.current) {
                Image(
                    painter = painterResource(R.drawable.test_image),
                    contentDescription = null,
                    modifier = imageModifier,
                    contentScale = contentScale
                )
            }
        },
        onSuccess = { state ->
            val bitmap = (state.result.image as BitmapImage).bitmap.copy(Bitmap.Config.ARGB_8888, false)
            // Generate the color palette from the bitmap
            val palette = Palette.from(bitmap).generate()

            // Extract dominant and vibrant colors
            val dominantColorArgb = palette.getDominantColor(Color.Black.toArgb())
            val vibrantColorArgb = palette.getVibrantColor(Color.Black.toArgb())

            onColorsRetrieved(
                Color(
                    red = dominantColorArgb.red,
                    green = dominantColorArgb.green,
                    blue = dominantColorArgb.blue,
                    alpha = dominantColorArgb.alpha
                ),
                Color(
                    red = vibrantColorArgb.red,
                    green = vibrantColorArgb.green,
                    blue = vibrantColorArgb.blue,
                    alpha = vibrantColorArgb.alpha
                )
            )
        },
        contentScale = contentScale
    )
}

@Composable
@Preview(showSystemUi = true, device = "id:pixel_9_pro_fold")
fun MainAnimeCardPreviewTablet() {
    DokiZoneTheme {
        RandomAnimeCard(
            randomAnime = getRandomAnime()
        )
    }
}

@Composable
@Preview(showSystemUi = true, device = "id:pixel_8a")
fun MainAnimeCardPreview() {
    DokiZoneTheme {
        RandomAnimeCard(
            randomAnime = getRandomAnime()
        )
    }
}

private fun getRandomAnime(): AnimeCard {
    return AnimeCard(
        id = 1,
        imageUrl = "",
        title = LoremIpsum(20).values.first(),
        synopsis = LoremIpsum(200).values.first()
    )
}