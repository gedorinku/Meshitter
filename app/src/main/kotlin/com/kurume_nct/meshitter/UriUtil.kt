package com.kurume_nct.meshitter

import android.content.Context
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore

/**
 * Created by gedorinku on 2017/07/26.
 */
fun Uri.toMediaPath(context: Context): String {
    if (scheme == "file") {
        return path
    }

    val id = DocumentsContract.getDocumentId(this)
    val selection = "_id=?"
    val selectionArgs = arrayOf(id.split(":")[1])

    return context.contentResolver
            .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, arrayOf(MediaStore.MediaColumns.DATA), selection, selectionArgs, null)
            ?.use {
                it.moveToFirst()
                it.getString(0)
            } ?: throw IllegalStateException()
}