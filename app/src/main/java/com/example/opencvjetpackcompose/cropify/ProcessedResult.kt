package com.example.opencvjetpackcompose.cropify

import android.graphics.Bitmap
import androidx.compose.ui.geometry.Rect


data class ProcessedResult(val bitmap: Bitmap, val rect: Rect?)