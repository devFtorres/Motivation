package com.ftorres.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ftorres.motivation.infra.MotivationConstants
import com.ftorres.motivation.R
import com.ftorres.motivation.infra.SecurityPreferences
import com.ftorres.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View Binding
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set onClick's
        binding.buttonSave.setOnClickListener(this)

        //Hide Action Bar
        supportActionBar?.hide()
    }


    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            finish()
        } else {
            Toast.makeText(this, R.string.name_mandatory, Toast.LENGTH_SHORT).show()
        }
    }
}