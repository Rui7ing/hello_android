package com.thoughtworks.androidtrain.view

import android.Manifest.permission.READ_CONTACTS
import android.content.Intent
import android.content.res.ColorStateList
import android.database.Cursor
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.provider.ContactsContract
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.thoughtworks.androidtrain.R
import com.thoughtworks.androidtrain.util.PermissionUtil
import kotlin.reflect.KClass

const val PICK_CONTACT_REQUEST = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        layoutButton()
    }

    private fun layoutButton() {
        val layout = findViewById<LinearLayout>(R.id.linearLayoutButtons)

        val drawable = GradientDrawable()
        drawable.cornerRadius = resources.getDimension(R.dimen.login_button)
        drawable.color = ColorStateList.valueOf(Color.GRAY)

        for (i in 1..10) {
            val button = Button(this).also {
                val resourceId = resources.getIdentifier("button_$i", "string", packageName)
                it.text = resources.getText(resourceId)
                val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                layoutParams.width = resources.getDimension(R.dimen.button_width).toInt()
                layoutParams.topMargin = resources.getDimension(R.dimen.button_top_margin).toInt()
                it.layoutParams = layoutParams
                it.background = drawable
            }
            when (i) {
                1 -> button.setOnClickListener { jumpToView(ConstraintActivity::class) }
                2 -> button.setOnClickListener { jumpToView(LoginActivity::class) }
                3 -> button.setOnClickListener { implicitJump() }
                4 -> button.setOnClickListener { jumpToView(LanguageSelectionActivity::class) }
                5 -> button.setOnClickListener { jumpToView(TweetsActivity::class) }
                6 -> button.setOnClickListener { jumpToView(ThreadActivity::class) }
                7 -> button.setOnClickListener { jumpToView(DataStoreActivity::class) }
                8 -> button.setOnClickListener { jumpToView(TweetsComposeActivity::class) }
                else -> {}
            }
            layout.addView(button)
        }
    }

    private fun implicitJump() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        PermissionUtil().checkPermission(this, READ_CONTACTS, PICK_CONTACT_REQUEST)
        startActivityForResult(intent, PICK_CONTACT_REQUEST)
    }

    private fun <T : AppCompatActivity> jumpToView(activityClazz: KClass<T>) {
        val intent = Intent(this, activityClazz.java)
        startActivity(intent)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {
            val projection = arrayOf(
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
            )

            val cursor = contentResolver.query(
                data?.data!!,
                projection,
                null,
                null,
                null
            )
            cursor.use {
                cursor!!.moveToNext()
                val displayNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val contactName = cursor.getString(displayNameIndex)
                val contactIdIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
                val phoneNum = getPhoneNumber(cursor, contactIdIndex)
                Toast.makeText(this, "$contactName $phoneNum", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getPhoneNumber(cursor: Cursor, contactIdIndex: Int): String? {

        val contactId = cursor.getString(contactIdIndex)
        val phoneCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
            null,
            null
        )
        phoneCursor.use {
            phoneCursor!!.moveToNext()
            val phoneIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            return phoneCursor.getString(phoneIndex)
        }
    }
}