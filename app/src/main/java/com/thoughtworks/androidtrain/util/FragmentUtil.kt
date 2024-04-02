package com.thoughtworks.androidtrain.util

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class FragmentUtil {


    @SuppressLint("CommitTransaction")
    fun replaceToFragment(
        activity: FragmentActivity,
        containerViewId: Int,
        fragment: Fragment
        ) {

        val fm = activity.supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(containerViewId, fragment)
        ft.commit()
    }
}
