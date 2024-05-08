package com.example.machinetest.utility

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import productdb.GetAllCartinfo
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date

fun getcurrentdateAndTime(): CurrentDateAndTime {
    val currentDateTime = Date()
    // Format date in "yyyy-MM-dd" format
    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
    val apidateformat= SimpleDateFormat("yyyy-MM-dd")
    val formattedDate = dateFormat.format(currentDateTime)
    val apiformattedDate=apidateformat.format(currentDateTime)
    // Format time in 24-hour "HH:mm:ss" format
    val timeFormat = SimpleDateFormat("HH:mm:ss")
    val formattedTime = timeFormat.format(currentDateTime)
    return CurrentDateAndTime(date = formattedDate,time = formattedTime)

}

fun getimagebyteArray(context: Context, uri: Uri?): ByteArray {
    val contentResolver = context.contentResolver

    val inputStream = contentResolver.openInputStream(uri!!)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    val selectedImage = bitmap.asImageBitmap()

    val outputStream = ByteArrayOutputStream()
    selectedImage.asAndroidBitmap().compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    return outputStream.toByteArray()
}

fun getNetTotal(pricedetails:List<GetAllCartinfo>):Double?{
    var totalPrice = 0.0
    for (price in pricedetails) {
        totalPrice += price.sub_total
    }
   return totalPrice
}