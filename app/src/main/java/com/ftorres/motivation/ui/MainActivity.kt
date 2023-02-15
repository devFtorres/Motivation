package com.ftorres.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.ftorres.motivation.infra.MotivationConstants
import com.ftorres.motivation.R
import com.ftorres.motivation.data.Mock
import com.ftorres.motivation.infra.SecurityPreferences
import com.ftorres.motivation.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryID = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide Toolbar
        supportActionBar?.hide()
        //Set filters
        handleFilter(R.id.image_all)
        //Set Phrase
        handleNextPhrase()

        //Set onClick's
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.textUserName.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        handleUserName()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        }else if (view.id == R.id.text_user_name) {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }



    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryID = MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryID = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryID = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Hi, $name!"
    }

    private fun handleNextPhrase() {
        binding.textPhrase.text = Mock().getPhrase(categoryID, Locale.getDefault().language)
    }


}