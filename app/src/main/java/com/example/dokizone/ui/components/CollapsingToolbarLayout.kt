package com.example.dokizone.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.dokizone.R
import com.example.dokizone.ui.theme.DokiZoneTheme
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CollapsingLayout(
    modifier: Modifier = Modifier,
    toolbarTitle: String? = null,
    navigationIcon: Painter = painterResource(R.drawable.ic_back),
    onNavigationIconClicked: () -> Unit = {},
    collapsingTop: @Composable () -> Unit,
    bodyContent: @Composable () -> Unit
) {
    var collapsingTopHeight by remember { mutableStateOf(0f) }

    var offset by remember { mutableStateOf(Float.MIN_VALUE) }

    var toolbarHeight by remember { mutableIntStateOf(0) }
    val isToolbarVisible = (offset <= -(collapsingTopHeight - toolbarHeight)) && offset != Float.MIN_VALUE
    val isCollapsingContentVisible = true

    fun calculateOffset(delta: Float): Offset {
        val oldOffset = offset
        val newOffset = (oldOffset + delta).coerceIn(-collapsingTopHeight, 0f)
        offset = newOffset
        return Offset(0f, newOffset - oldOffset)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset =
                when {
                    available.y >= 0 -> Offset.Zero
                    offset == -collapsingTopHeight -> Offset.Zero
                    else -> calculateOffset(available.y)
                }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource,
            ): Offset =
                when {
                    available.y <= 0 -> Offset.Zero
                    offset == 0f -> Offset.Zero
                    else -> calculateOffset(available.y)
                }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
    ) {
        Box(
            modifier = Modifier
                .onSizeChanged { size ->
                    collapsingTopHeight = size.height.toFloat()
                }
                .offset {
                    IntOffset(
                        x = 0,
                        y = offset.roundToInt()
                    )
                },
            content = {
                LazyColumn {
                    item {
                        AnimatedVisibility(
                            visible = isCollapsingContentVisible
                        ) {
                            collapsingTop()
                        }
                    }
                }
            },
        )
        Box(
            modifier = Modifier.offset {
                IntOffset(
                    x = 0,
                    y = (collapsingTopHeight + offset).roundToInt()
                )
            },
            content = {
                Column {
                   AnimatedVisibility(
                       visible = isToolbarVisible
                   ) {
                       TopAppBar(
                           modifier = Modifier
                               .onGloballyPositioned {
                                   toolbarHeight = it.size.height
                               },
                           title = {
                               Text(
                                   text = toolbarTitle.orEmpty(),
                                   style = DokiZoneTheme.typography.toolbar,
                                   color = DokiZoneTheme.colorScheme.textColor,
                                   maxLines = 2,
                                   overflow = TextOverflow.Ellipsis
                               )
                           },
                           navigationIcon = {
                               Icon(
                                   modifier = Modifier.size(32.dp)
                                       .clickable {
                                           onNavigationIconClicked()
                                       },
                                   tint = DokiZoneTheme.colorScheme.textColor,
                                   painter = navigationIcon,
                                   contentDescription = null
                               )
                           },
                           colors = TopAppBarDefaults.topAppBarColors(
                               containerColor = DokiZoneTheme.colorScheme.backgroundColor
                           )
                       )
                   }
                    bodyContent()
                }
            },
        )
    }
}