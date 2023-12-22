package com.example.opencvjetpackcompose

import androidx.compose.ui.geometry.Offset

data class CropPoints(val topLeft: Offset, val topRight: Offset,
                      val bottomLeft: Offset, val bottomRight: Offset)
