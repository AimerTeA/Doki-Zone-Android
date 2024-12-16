package com.example.dokizone.ui.components.anime

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dokizone.domain.model.AnimeCard
import com.example.dokizone.ui.components.AsyncImageWithPreview
import com.example.dokizone.ui.theme.DokiZoneTheme

@Composable
fun AnimeCardView(
    modifier: Modifier = Modifier,
    animeCard: AnimeCard
) {
    AsyncImageWithPreview(
        modifier = modifier.height(150.dp)
            .aspectRatio(0.7f)
            .clip(shape = DokiZoneTheme.shapes.imageShape),
        model = animeCard.imageUrl,
        contentDescription = animeCard.title,
        contentScale = ContentScale.Crop
    )
}

@Preview(showSystemUi = true)
@Composable
fun AnimeCardViewPreview() {
    DokiZoneTheme {
        AnimeCardView(
            animeCard = AnimeCard(
                id = 1,
                imageUrl = "",
                title = "One Piece",
                synopsis = ""
            )
        )
    }
}