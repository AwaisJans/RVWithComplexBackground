package com.jans.recycler.view.with.background.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jans.recycler.view.with.background.R

class MainActivity : AppCompatActivity() {


    lateinit var btnOneImageRV: Button
    lateinit var btnTwoImagesRV: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTwoImagesRV = findViewById(R.id.btnTwoImagesWithRV)
        btnOneImageRV = findViewById(R.id.btnAnImageWithRV)




        btnTwoImagesRV.setOnClickListener{
            startActivity(Intent(this, TwoBGImagesRVScreen::class.java))
        }


        btnOneImageRV.setOnClickListener{
            startActivity(Intent(this, OneBGImageRVScreen::class.java))
        }


    }




}