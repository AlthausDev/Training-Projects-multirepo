package com.althaus.dev.cinemaNexus.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BaseLayout(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    showAppBar: Boolean = true,
    appBarTitle: String = "",
    appBarActions: @Composable RowScope.() -> Unit = {},
    appBarNavigationIcon: @Composable (() -> Unit)? = null,
    // Agregamos estos dos:
    appBarBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    appBarContentColor: Color = MaterialTheme.colorScheme.primary,

    showAppImage: Boolean = false,
    appImage: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            if (showAppBar) {
                SharedTopAppBar(
                    title = appBarTitle,
                    actions = appBarActions,
                    navigationIcon = appBarNavigationIcon,
                    // Utilizamos los nuevos parámetros
                    backgroundColor = appBarBackgroundColor,
                    contentColor = appBarContentColor
                )
            }
            if (showAppImage && appImage != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    appImage()
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                content()
            }
        }
    }
}

@Composable
fun ColumnScope.SpacerForLogo(
    topWeight: Float = 0.3f,
    bottomWeight: Float = 0.1f
) {
    Spacer(modifier = Modifier.weight(topWeight)) // Espacio flexible superior
    Spacer(modifier = Modifier.weight(bottomWeight)) // Espacio flexible inferior
}