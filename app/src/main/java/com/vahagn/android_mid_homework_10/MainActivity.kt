package com.vahagn.android_mid_homework_10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {
    private lateinit var toolbar: Toolbar
    private val exMap = mapOf(R.id.menuEx1 to Ex1::class.java,
            R.id.menuEx2 to Ex2::class.java,
            R.id.menuEx3 to Ex3::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        item?.let {
            startActivity(Intent(this, exMap[it.itemId]))
        }
        return true
    }
}