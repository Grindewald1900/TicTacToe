package com.example.tictactoe

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.suke.widget.SwitchButton

class SettingsActivity : AppCompatActivity() {
    lateinit var tvAutoReset: TextView
    lateinit var btnReset: SwitchButton
    lateinit var switchListener: SwitchButton.OnCheckedChangeListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        tvAutoReset = findViewById(R.id.tv_setting_reset)
        btnReset = findViewById(R.id.switch_button_reset)

        val share: SharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        btnReset.setChecked(share.getBoolean("AutoReset",false))
        btnReset.setOnCheckedChangeListener(object:SwitchButton.OnCheckedChangeListener{
            override fun onCheckedChanged(view:SwitchButton, isChecked:Boolean ){
                share.edit().putBoolean("AutoReset", isChecked).apply()
                if (isChecked)
                    Toast.makeText(applicationContext,resources.getString(R.string.toast_reset_on),Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(applicationContext,resources.getString(R.string.toast_reset_off),Toast.LENGTH_SHORT).show()
            }
        })
    }

}
