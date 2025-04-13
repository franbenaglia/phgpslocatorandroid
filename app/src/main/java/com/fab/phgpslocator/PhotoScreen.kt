package com.fab.phgpslocator

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.lifecycle.LifecycleOwner
import com.fab.phgpslocator.ui.permission.WithPermission
import java.io.File

@Composable
fun CameraAppScreen() {
    WithPermission(
        permission = Manifest.permission.CAMERA
    ) {
        CameraApp()
    }
}

@Composable
fun CameraApp() {
    var lensFacing by remember { mutableIntStateOf(CameraSelector.LENS_FACING_FRONT) }
    var zoomLevel by remember { mutableFloatStateOf(0.0f) }
    val imageCaptureUseCase = remember { ImageCapture.Builder().build() }

    val localContext = LocalContext.current

    Box {
        CameraPreview(
            lensFacing = lensFacing,
            zoomLevel = zoomLevel,
            imageCaptureUseCase = imageCaptureUseCase
        )

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Row {
                Button(onClick = { lensFacing = CameraSelector.LENS_FACING_FRONT }) {
                    Text("Front camera")
                }
                Button(onClick = { lensFacing = CameraSelector.LENS_FACING_BACK }) {
                    Text("Back camera")
                }
            }

            Row {
                Button(onClick = { zoomLevel = 0.0f }) {
                    Text("Zoom 0.0")
                }
                Button(onClick = { zoomLevel = 0.5f }) {
                    Text("Zoom 0.5")
                }
                Button(onClick = { zoomLevel = 1.0f }) {
                    Text("Zoom 1.0")
                }
            }

            Button(onClick = {
                val outputFileOptions = ImageCapture.OutputFileOptions.Builder(
                    File(
                        localContext.externalCacheDir,
                        "image.jpg"
                    )
                )
                    .build()
                val callback = object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        outputFileResults.savedUri?.shareAsImage(localContext)
                    }

                    override fun onError(exception: ImageCaptureException) {
                    }
                }
                imageCaptureUseCase.takePicture(
                    outputFileOptions,
                    ContextCompat.getMainExecutor(localContext),
                    callback
                )
            }) {
                Text("Take Photo")
            }
        }
    }
}

fun Uri.shareAsImage(context: Context) {
    val contentUri =
        FileProvider.getUriForFile(context, "com.fab.phgpslocator.fileprovider", toFile())
    val shareIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, contentUri)
        type = "image/jpeg"
    }
    context.startActivity(Intent.createChooser(shareIntent, null))
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    lensFacing: Int,
    zoomLevel: Float,
    imageCaptureUseCase: ImageCapture,
) {
    val previewUseCase = remember { androidx.camera.core.Preview.Builder().build() }

    var cameraProvider by remember { mutableStateOf<ProcessCameraProvider?>(null) }
    var cameraControl by remember { mutableStateOf<CameraControl?>(null) }

    val localContext = LocalContext.current

    fun rebindCameraProvider() {
        cameraProvider?.let { cameraProvider ->
            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build()
            cameraProvider.unbindAll()
            val camera = cameraProvider.bindToLifecycle(
                localContext as LifecycleOwner,
                cameraSelector,
                previewUseCase, imageCaptureUseCase
            )
            cameraControl = camera.cameraControl
        }
    }

    LaunchedEffect(Unit) {
        cameraProvider = ProcessCameraProvider.awaitInstance(localContext)
        rebindCameraProvider()
    }

    LaunchedEffect(lensFacing) {
        rebindCameraProvider()
    }

    LaunchedEffect(zoomLevel) {
        cameraControl?.setLinearZoom(zoomLevel)
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            PreviewView(context).also {
                previewUseCase.surfaceProvider = it.surfaceProvider
                rebindCameraProvider()
            }
        }
    )
}

