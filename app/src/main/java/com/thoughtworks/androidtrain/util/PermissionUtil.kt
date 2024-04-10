package com.thoughtworks.androidtrain.util

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.math.BigInteger
import java.security.MessageDigest

class PermissionUtil {
    fun checkPermission(act: Activity, permission: String, requestCode: Int): Boolean {

        val check = ContextCompat.checkSelfPermission(act, permission)
        if (check != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(act, arrayOf(permission), requestCode)
            return false
        }
        return true
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}