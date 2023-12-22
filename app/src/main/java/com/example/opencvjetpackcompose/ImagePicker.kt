package com.example.opencvjetpackcompose

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.MatOfPoint
import org.opencv.core.MatOfPoint2f
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc
import org.opencv.utils.Converters
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.Arrays
import kotlin.math.roundToInt


//fun findDocumentCornersAndDrawRectangle(image: Bitmap): ProcessedResult {
//  OpenCVLoader.initDebug()
//
//  // Convert bitmap to OpenCV matrix
//  val matOriginal = Mat()
//  Utils.bitmapToMat(image, matOriginal)
//
//  // Create a copy for processing
//  val matProcessed = matOriginal.clone()
//
//  // Calculate the scale factor for resizing
//  val scaleFactor = 500.0 / image.height
//  val resizedWidth = (image.width * scaleFactor).roundToInt().toInt()
//
//  // Resize the processed image for corner detection
//  Imgproc.resize(matProcessed, matProcessed, Size(resizedWidth.toDouble(), 500.0))
//
//  // Convert the processed image to LUV color space
//  Imgproc.cvtColor(matProcessed, matProcessed, Imgproc.COLOR_BGR2Luv)
//
//  // Split and process channels to find corners
//  val imageSplitByColorChannel: List<Mat> = mutableListOf()
//  Core.split(matProcessed, imageSplitByColorChannel)
//  val documentCorners = imageSplitByColorChannel
//    .mapNotNull { channel -> findCorners(channel) }
//    .maxByOrNull { contour -> Imgproc.contourArea(contour) }
//    ?.toList()
//    ?.map { point ->
//      Point(point.x / scaleFactor, point.y / scaleFactor) // Rescale points to original size
//    }
//
//  var detectedComposeRect: androidx.compose.ui.geometry.Rect? = null
//
//  // Draw contours and rectangle on the original image
//  documentCorners?.let { corners ->
//    // Draw contour
//    val contour = MatOfPoint(*corners.toTypedArray())
//    Imgproc.drawContours(matOriginal, listOf(contour), -1, Scalar(255.0, 0.0, 0.0), 2) // Red contour
//
//    // Draw bounding rectangle
//    val boundingRect = Imgproc.boundingRect(contour)
//    Imgproc.rectangle(matOriginal, boundingRect.tl(), boundingRect.br(), Scalar(0.0, 255.0, 0.0), 2) // Green rectangle
//
//    // Convert OpenCV Rect to Compose Rect
//    detectedComposeRect = androidx.compose.ui.geometry.Rect(
//      left = boundingRect.x.toFloat(),
//      top = boundingRect.y.toFloat(),
//      right = (boundingRect.x + boundingRect.width).toFloat(),
//      bottom = (boundingRect.y + boundingRect.height).toFloat()
//    )
//  }
//
//  // Convert the processed Mat back to Bitmap
//  val processedImage = Bitmap.createBitmap(matOriginal.cols(), matOriginal.rows(), Bitmap.Config.ARGB_8888)
//  Utils.matToBitmap(matOriginal, processedImage)
//  val rect=detectedComposeRect?.let { calculateImagePositionOpenCV(processedImage.asImageBitmap(), it.size) }
//  // Release resources
//  matOriginal.release()
//  matProcessed.release()
//
//  // Return the processed Bitmap and detected Rectangle in Compose format
//  return ProcessedResult(processedImage, rect)
//}




// DRAW CONTOUR ON DETECTED DOCUMENT
//fun findDocumentCornersAndDrawContour(image: Bitmap): Bitmap {
//  OpenCVLoader.initDebug()
//
//  // Convert bitmap to OpenCV matrix
//  val matOriginal = Mat()
//  Utils.bitmapToMat(image, matOriginal)
//
//  // Create a copy for processing
//  val matProcessed = matOriginal.clone()
//
//  // Calculate the scale factor for resizing
//  val scaleFactor = 500.0 / image.height
//  val resizedWidth = (image.width * scaleFactor).roundToInt().toInt()
//
//  // Resize the processed image for corner detection
//  Imgproc.resize(matProcessed, matProcessed,Size(resizedWidth.toDouble(), 500.0))
//
//  // Convert the processed image to LUV color space
//  Imgproc.cvtColor(matProcessed, matProcessed, Imgproc.COLOR_BGR2Luv)
//
//  // Split and process channels to find corners
//  val imageSplitByColorChannel: List<Mat> = mutableListOf()
//  Core.split(matProcessed, imageSplitByColorChannel)
//  val documentCorners = imageSplitByColorChannel
//    .mapNotNull { channel -> findCorners(channel) }
//    .maxByOrNull { contour -> Imgproc.contourArea(contour) }
//    ?.toList()
//    ?.map { point ->
//      Point(point.x / scaleFactor, point.y / scaleFactor) // Rescale points to original size
//    }
//
//  // Draw a contour around the detected corners on the original image
//  documentCorners?.let {
//    val contour = MatOfPoint(*it.toTypedArray())
//    Imgproc.drawContours(matOriginal, listOf(contour), -1, Scalar(255.0, 0.0, 0.0), 2) // Red contour
//  }
//
//  // Convert the processed Mat back to Bitmap
//  val processedImage = Bitmap.createBitmap(matOriginal.cols(), matOriginal.rows(), Bitmap.Config.ARGB_8888)
//  Utils.matToBitmap(matOriginal, processedImage)
//
//  // Release the processed Mat to free memory
//  matProcessed.release()
//
//  // Return the processed Bitmap
//  return processedImage
//}




//Working not able to correctly crop images

//fun findDocumentCornersAndCrop(image: Bitmap): Bitmap {
//  OpenCVLoader.initDebug()
//
//  // Convert bitmap to OpenCV matrix
//  val matOriginal = Mat()
//  Utils.bitmapToMat(image, matOriginal)
//
//  // Create a copy for processing
//  val matProcessed = matOriginal.clone()
//
//  // Calculate the scale factor for resizing
//  val scaleFactor = 500.0 / image.height
//  val resizedWidth = (image.width * scaleFactor).roundToInt().toInt()
//
//  // Resize the processed image for corner detection
//  Imgproc.resize(matProcessed, matProcessed, Size(resizedWidth.toDouble(), 500.0))
//
//  // Convert the processed image to LUV color space
//  Imgproc.cvtColor(matProcessed, matProcessed, Imgproc.COLOR_BGR2Luv)
//
//  // Split and process channels to find corners
//  val imageSplitByColorChannel: List<Mat> = mutableListOf()
//  Core.split(matProcessed, imageSplitByColorChannel)
//  val documentCorners = imageSplitByColorChannel
//    .mapNotNull { channel -> findCorners(channel) }
//    .maxByOrNull { contour -> Imgproc.contourArea(contour) }
//    ?.toList()
//    ?.map { point ->
//      Point(point.x / scaleFactor, point.y / scaleFactor) // Rescale points to original size
//    }
//
//  // Crop the original image using the detected corners
//  var croppedImage: Bitmap? = null
//  documentCorners?.let {
//    val rect = Imgproc.boundingRect(MatOfPoint(*it.toTypedArray()))
//    val croppedMat = Mat(matOriginal, rect)
//
//    // Convert the cropped Mat back to Bitmap
//    croppedImage = Bitmap.createBitmap(croppedMat.cols(), croppedMat.rows(), Bitmap.Config.ARGB_8888)
//    Utils.matToBitmap(croppedMat, croppedImage)
//  }
//
//  // Release the processed Mat to free memory
//  matProcessed.release()
//
//  // Return the cropped Bitmap or the original image if no corners were found
//  return croppedImage ?: image
//}


//WORKING BUT PUTTING LEFT CONTENT ON RIGHT

fun findDocumentCornersAndCrop(image: Bitmap): Bitmap {
  // Initialize OpenCV
  OpenCVLoader.initDebug()

  // Convert bitmap to OpenCV matrix
  val matOriginal = Mat()
  Utils.bitmapToMat(image, matOriginal)

  // Create a copy for processing
  val matProcessed = matOriginal.clone()

  // Calculate the scale factor for resizing
  val scaleFactor = 500.0 / image.height
  val resizedWidth = (image.width * scaleFactor).roundToInt().toInt()

  // Resize the processed image for corner detection
  Imgproc.resize(matProcessed, matProcessed, Size(resizedWidth.toDouble(), 500.0))

  // Convert the processed image to LUV color space
  Imgproc.cvtColor(matProcessed, matProcessed, Imgproc.COLOR_BGR2Luv)

  // Split and process channels to find corners
  val imageSplitByColorChannel: List<Mat> = mutableListOf()
  Core.split(matProcessed, imageSplitByColorChannel)
  val documentCorners = imageSplitByColorChannel
    .mapNotNull { channel -> findCorners(channel) }
    .maxByOrNull { contour -> Imgproc.contourArea(contour) }
    ?.toList()
    ?.map { point ->
      Point(point.x / scaleFactor, point.y / scaleFactor) // Rescale points to original size
    }

  // Release the processed Mat to free memory
  matProcessed.release()

  // Check if corners are detected
  if (documentCorners != null && documentCorners.size == 4) {
    // Define the destination points for perspective transformation
    val destPoints = arrayOf(
      Point(0.0, 0.0), // Top-left corner
      Point(image.width.toDouble(), 0.0), // Top-right corner
      Point(image.width.toDouble(), image.height.toDouble()), // Bottom-right corner
      Point(0.0, image.height.toDouble()) // Bottom-left corner
    )

    // Calculate the Perspective Transform matrix
    val perspectiveTransform = Imgproc.getPerspectiveTransform(
      Converters.vector_Point2f_to_Mat(documentCorners),
      Converters.vector_Point2f_to_Mat(destPoints.toList())
    )

    // Apply the Perspective Transform
    val warpedImage = Mat()
    Imgproc.warpPerspective(
      matOriginal,
      warpedImage,
      perspectiveTransform,
      Size(image.width.toDouble(), image.height.toDouble())
    )

    // Convert the warped Mat back to Bitmap
    val straightenedImage = Bitmap.createBitmap(warpedImage.cols(), warpedImage.rows(), Bitmap.Config.ARGB_8888)
    Utils.matToBitmap(warpedImage, straightenedImage)

    warpedImage.release()
    return straightenedImage
  } else {
    // Return the original image if no corners were found
    return image
  }
}

private fun findCorners(image: Mat): MatOfPoint? {
  val outputImage = Mat()

  // blur image to help remove noise
  Imgproc.GaussianBlur(image, outputImage, Size(5.0, 5.0), 0.0)

  // convert all pixels to either black or white (document should be black after this), but
  // there might be other parts of the photo that turn black
  Imgproc.threshold(
    outputImage,
    outputImage,
    0.0,
    255.0,
    Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU
  )

  // detect the document's border using the Canny edge detection algorithm
  Imgproc.Canny(outputImage, outputImage, 50.0, 200.0)

  // the detect edges might have gaps, so try to close those
  Imgproc.morphologyEx(
    outputImage,
    outputImage,
    Imgproc.MORPH_CLOSE,
    Mat.ones(Size(5.0, 5.0), CvType.CV_8U)
  )

  // get outline of document edges, and outlines of other shapes in photo
  val contours: MutableList<MatOfPoint> = mutableListOf()
  Imgproc.findContours(
    outputImage,
    contours,
    Mat(),
    Imgproc.RETR_LIST,
    Imgproc.CHAIN_APPROX_SIMPLE
  )

  // approximate outlines using polygons
  var approxContours =
    contours.map {
      val approxContour = MatOfPoint2f()
      val contour2f = MatOfPoint2f(*it.toArray())
      Imgproc.approxPolyDP(
        contour2f,
        approxContour,
        0.02 * Imgproc.arcLength(contour2f, true),
        true
      )
      MatOfPoint(*approxContour.toArray())
    }

  // We now have many polygons, so remove polygons that don't have 4 sides since we
  // know the document has 4 sides. Calculate areas for all remaining polygons, and
  // remove polygons with small areas. We assume that the document takes up a large portion
  // of the photo. Remove polygons that aren't convex since a document can't be convex.
  approxContours =
    approxContours.filter {
      it.height() == 4 && Imgproc.contourArea(it) > 1000 && Imgproc.isContourConvex(it)
    }

  // Once we have all large, convex, 4-sided polygons find and return the 1 with the
  // largest area
  return approxContours.maxByOrNull { Imgproc.contourArea(it) }
}



//@Composable
//@OptIn(ExperimentalPermissionsApi::class)
//fun ImagePicker(
//  modifier: Modifier = Modifier,
//) {
//  var currentUri by remember { mutableStateOf(value = Uri.EMPTY) }
//  var tempUri by remember { mutableStateOf(value = Uri.EMPTY) }
//
//  val context = LocalContext.current
//
//  val imagePicker = rememberLauncherForActivityResult(
//    contract = ActivityResultContracts.GetContent(),
//    onResult = { uri ->
//      currentUri = uri ?: tempUri
////      hasImage = uri != null
////      imageUri = uri
//    }
//  )
//
//  val cameraLauncher = rememberLauncherForActivityResult(
//    contract = ActivityResultContracts.TakePicture(),
//    onResult = { success ->
//      if (success) currentUri = tempUri
//    }
//  )
//
//  val cameraPermissionState = rememberPermissionState(
//    permission = android.Manifest.permission.CAMERA,
//    onPermissionResult = { granted ->
//      if (granted) {
//        tempUri = createTempPictureUri(context)
//        cameraLauncher.launch(tempUri)
//      } else print("camera permission is denied")
//    }
//  )
//
//  Column(
//    modifier = modifier.padding(16.dp),
//  ) {
//    Box(
//      modifier = Modifier
//        .fillMaxWidth()
//        .weight(1f),
//    ) {
//      if (currentUri.toString().isNotEmpty()) {
////        val result = processImage(context, currentUri!!)
//        val bitmap= uriToBitmap(context = context,currentUri)
//        val result= bitmap?.let { findDocumentCornersAndDraw(it) }
//
//        if (result != null) {
//          Image(
//            bitmap = result.asImageBitmap(),
//            modifier = Modifier
//              .fillMaxWidth()
//              .fillMaxHeight(),
//            contentScale = ContentScale.Fit,
//            contentDescription = "Selected image",
//            )
//        }
//      }
//    }
//    Spacer(Modifier.height(32.dp))
//    Button(
//      modifier = Modifier
//        .fillMaxWidth()
//        .height(50.dp),
//      onClick = {
//        imagePicker.launch("image/*")
//      }
//    ) {
//      Text(text = "Select image")
//    }
//    Spacer(Modifier.height(16.dp))
//    Button(
//      modifier = Modifier
//        .fillMaxWidth()
//        .height(50.dp),
//        onClick = cameraPermissionState::launchPermissionRequest
////      onClick = {
////        cameraPermissionState::launchPermissionRequest
////        val uri = ComposeFileProvider.getImageUri(context)
////        imageUri = uri
////        cameraLauncher.launch(uri)
////      }
//    ) {
//      Text(text = "Take a photo")
//    }
//  }
//}

fun uriToBitmap(context: Context,uri: Uri): Bitmap? {
  try {
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
    return BitmapFactory.decodeStream(inputStream)
  } catch (e: FileNotFoundException) {
    e.printStackTrace()
    return null
  }
}





//@Composable
//fun CropLayoutWithDraggableCorners() {
//  // State to hold corner positions
//  var topLeft = remember { mutableStateOf(Offset(100f, 100f)) }
//  var topRight = remember { mutableStateOf(Offset(300f, 100f)) }
//  var bottomLeft = remember { mutableStateOf(Offset(100f, 300f)) }
//  var bottomRight = remember { mutableStateOf(Offset(300f, 300f)) }
//
//  BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
//    val constraints = this.constraints
//
//    // Drawing lines on canvas
//    Canvas(modifier = Modifier.matchParentSize()) {
//      drawLine(Color.Black, topLeft.value, topRight.value, strokeWidth = 4.dp.toPx())
//      drawLine(Color.Black, topRight.value, bottomRight.value, strokeWidth = 4.dp.toPx())
//      drawLine(Color.Black, bottomRight.value, bottomLeft.value, strokeWidth = 4.dp.toPx())
//      drawLine(Color.Black, bottomLeft.value, topLeft.value, strokeWidth = 4.dp.toPx())
//    }
//
//    // Draggable corners
//    DraggableCorner(position = topLeft.value, onPositionChange = { topLeft.value = it }, bounds = constraints)
//    DraggableCorner(position = topRight.value, onPositionChange = { topRight.value = it }, bounds = constraints)
//    DraggableCorner(position = bottomLeft.value, onPositionChange = { bottomLeft.value = it }, bounds = constraints)
//    DraggableCorner(position = bottomRight.value, onPositionChange = { bottomRight.value = it }, bounds = constraints)
//  }
//}
//
//@Composable
//fun DraggableCorner(position: Offset, onPositionChange: (Offset) -> Unit, bounds: Constraints) {
//  Box(
//    modifier = Modifier
//      .offset { IntOffset(position.x.roundToInt().coerceIn(0, bounds.maxWidth), position.y.roundToInt().coerceIn(0, bounds.maxHeight)) }
//      .size(20.dp)
//      .background(Color.Blue)
//      .pointerInput(Unit) {
//        detectDragGestures { change, dragAmount ->
//          val newX = (position.x + dragAmount.x).coerceIn(0f, bounds.maxWidth.toFloat())
//          val newY = (position.y + dragAmount.y).coerceIn(0f, bounds.maxHeight.toFloat())
//          val newPosition = Offset(newX, newY)
//          onPositionChange(newPosition)
//        }
//      }
//  )
//}


