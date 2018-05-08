package com.bdjobs.bdjobsrnd

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_search__new.*
import java.util.ArrayList
import android.text.Editable




class Search_Activity_new : AppCompatActivity() {

    var data: String? = null
    var from: String? = null
    private val sampleData = ArrayList<String>()
    var searchTextWatcher:TextWatcher?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.enterTransition = null
        setContentView(R.layout.activity_search__new)
        //setSupportActionBar(toolbar2)
        //supportActionBar?.title = ""
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        for(i in 1..100) {
            sampleData.add("item $i")
        }

        val adapter = FilterRVAdapter(sampleData,this)
        filterRV.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        filterRV.adapter = adapter





        searchTextWatcher = object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                adapter.filter.filter(s)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        }

        editText.addTextChangedListener(searchTextWatcher!!)


        from = intent.getStringExtra("from")
        data = intent.getStringExtra("data")

        when (from) {
            "jobTitle" -> editText.hint="Job title, Keyword"
            "location" -> editText.hint="Location"
        }

        editText.setText(data)
        editText.setSelection(editText.text.length)
        /*val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)*/
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            // If the event is a key-down event on the "enter" button
            if (event.getAction() === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Perform action on key press
                data = editText.text.toString()
                imageView6.visibility = View.GONE
                onBackPressed()
                return@OnKeyListener true
            }
            false
        })

        imageView6.setOnClickListener {

            imageView6.visibility = View.GONE
            onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {

        val intent = Intent()
        intent.putExtra("typedData", data)
        intent.putExtra("from", from)
        setResult(Activity.RESULT_OK, intent)
        window.exitTransition = null
        super.onBackPressed()
    }
}
