package me.jorgecasariego.capturephoto

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.common.RotationOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private var mCurrentPhotoPath: String = ""
    private val TAKE_PHOTO_REQUEST = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Check for permissions
        capture.setOnClickListener { checkPermissions() }
    }

    /**
     * 2. checkPermissions() method takes care of verify and request the write permissions that the app
     * requires. If the permission is granted it will launch the camera app via an Intent:
     */

    private fun checkPermissions() {
        Dexter.withActivity(this)
                .withPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(object: PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        launchCamera()
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                        AlertDialog.Builder(this@MainActivity)
                                .setTitle("Storage permission required")
                                .setMessage("In order to take a picture, you must grant this permission")
                                .setNegativeButton("Cancel") { dialogInterface, i ->
                                    dialogInterface.dismiss()
                                    token?.cancelPermissionRequest()
                                }
                                .setPositiveButton("OK!"){ dialogInterface, i ->
                                    dialogInterface.dismiss()
                                    token?.continuePermissionRequest()
                                }
                                .setOnDismissListener { token?.cancelPermissionRequest() }
                                .show()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        Snackbar.make(main_container,
                                "Storage permission is required in order to take a photo with the camera",
                                Snackbar.LENGTH_SHORT).show()
                    }

                }).check()
    }

    private fun launchCamera() {
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        val fileUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if(intent.resolveActivity(packageManager) != null) {
            mCurrentPhotoPath = fileUri.toString()
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(intent, TAKE_PHOTO_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("TEST", "volviendo a mostrar foto:  ");
        if (resultCode == Activity.RESULT_OK && requestCode == TAKE_PHOTO_REQUEST) {
            processToCapturePhoto()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    private fun processToCapturePhoto() {
        val cursor = contentResolver.query(Uri.parse(mCurrentPhotoPath), Array(1){
            android.provider.MediaStore.Images.ImageColumns.DATA
        }, null, null, null)

        cursor.moveToFirst()
        val photoPath = cursor.getString(0)
        cursor.close()
        val file = File(photoPath)
        val uri = Uri.fromFile(file)

        val height = resources.getDimensionPixelSize(R.dimen.photo_height)
        val width = resources.getDimensionPixelSize(R.dimen.photo_width)

        val request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(ResizeOptions(width, height))
                .setRotationOptions(RotationOptions.forceRotation(RotationOptions.ROTATE_90))
                .build()

        val controller = Fresco.newDraweeControllerBuilder()
                .setOldController(photo?.controller)
                .setImageRequest(request)
                .build()

        photo?.controller = controller
    }
}
