package com.example.opencvjetpackcompose

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.opencvjetpackcompose.CameraxFile.CameraView
import com.example.opencvjetpackcompose.cropify.CropBitmap
import com.example.opencvjetpackcompose.cropify.Cropify
import com.example.opencvjetpackcompose.cropify.CropifyOption
import com.example.opencvjetpackcompose.cropify.rememberCropifyState
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@OptIn(ExperimentalLayoutApi::class)
class MainActivity : ComponentActivity() {
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var photoUri: Uri
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)
    private val cropperOffsetWhenCornersNotFound = 100.0
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("kilo", "Permission granted")
            shouldShowCamera.value = true
        } else {
            Log.i("kilo", "Permission denied")
        }
    }
    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    @SuppressLint("SuspiciousIndentation", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val cropifyState = rememberCropifyState()
            var cropifyOption = remember { mutableStateOf(CropifyOption()) }
//            OpenCVJetpackComposeTheme {
//
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Scaffold(
//                        content = {innerPadding ->
//                            ImagePicker(modifier = Modifier.consumeWindowInsets(innerPadding))
//                        }
//                    )
//                }
//            }

            if (shouldShowCamera.value) {
                CameraView(
                    outputDirectory = outputDirectory,
                    executor = cameraExecutor,
                    onImageCaptured = ::handleImageCapture,
                    onError = { Log.e("kilo", "View error:", it) }
                )
            }


            if (shouldShowPhoto.value) {
                val bitmap = findDocumentCornersAndCrop(uriToBitmap(applicationContext,photoUri)!!)
                var imageBitmap= bitmap.asImageBitmap()
//                val rect = findDocumentCornersAndDrawRectangle(bitmap!!).rect
//                var rect1 = findDocumentCornersAndDrawRectangle(bitmap).bitmap.asImageBitmap()
//                Image(bitmap=rect1.asImageBitmap(), contentDescription =null )
//                Toast.makeText(applicationContext, rect.toString(), Toast.LENGTH_SHORT).show()
//                if (imageBitmap != null) {
////                    Image(bitmap=imageBitmap, contentDescription =null )
//                }
                if (bitmap != null) {
                    // Flip the bitmap
                    val matrix = Matrix().apply { postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f) }
                    val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)

                    // Cropify composable with stretched bitmap
                    Cropify(
                        bitmap = flippedBitmap.asImageBitmap(),
                        state = cropifyState,
                        onImageCropped = { imageBitmap = it },
                        modifier = Modifier.fillMaxSize().aspectRatio(flippedBitmap.width.toFloat() / flippedBitmap.height)
                    )
                }

//                if (imageBitmap != null) {
//                    if (rect != null) {
//                        CropBitmap(bitmap =rect1 , state =cropifyState , onImageCropped = {rect1=it},
//                            modifier = Modifier
//                                .fillMaxSize(),cropifyOption.value,rect)
//                    }
//                }
            }
            }
        requestCameraPermission()
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }


    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("kilo", "Permission previously granted")
                shouldShowCamera.value = true
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            ) -> Log.i("kilo", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    private fun handleImageCapture(uri: Uri) {
        Log.i("kilo", "Image captured: $uri")
        shouldShowCamera.value = false
        photoUri = uri
        shouldShowPhoto.value = true
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, "").apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}