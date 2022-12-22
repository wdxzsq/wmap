package ru.nhmt.wappaleksandrov

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import ru.nhmt.wappaleksandrov.databinding.ActivityMainBinding

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }

    }
}