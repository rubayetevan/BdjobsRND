package com.bdjobs.bdjobsrnd

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.enterTransition = null
        setContentView(R.layout.activity_main)


        profileIMGV.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        jobtitleET.setOnClickListener {
            val intent = Intent(this, Search_Activity_new::class.java)
            intent.putExtra("from", "jobTitle")
            intent.putExtra("data", jobtitleET.text.toString())
            val options = ActivityOptions.makeSceneTransitionAnimation(this, jobtitleET, "robot")
            window.exitTransition = null
            startActivityForResult(intent, 1, options.toBundle())
        }
        loacationET.setOnClickListener {
            val intent = Intent(this, Search_Activity_new::class.java)
            intent.putExtra("from", "location")
            intent.putExtra("data", loacationET.text.toString())
            val options = ActivityOptions.makeSceneTransitionAnimation(this, loacationET, "robot")
            window.exitTransition = null
            startActivityForResult(intent, 1, options.toBundle())
        }
        /* categoryET.setOnClickListener {
             val intent = Intent(this, Search_Activity_new::class.java)
             val options = ActivityOptions.makeSceneTransitionAnimation(this, categoryET, "robot")
             window.exitTransition = null
             startActivityForResult(intent, 1, options.toBundle())
         }*/
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val myStr = data?.getStringExtra("typedData")
                val from = data?.getStringExtra("from")
                when (from) {
                    "jobTitle" -> jobtitleET.setText(myStr)
                    "location" -> loacationET.setText(myStr)
                }
            }
        }
    }
}
