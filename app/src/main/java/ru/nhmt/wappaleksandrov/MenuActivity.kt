package ru.nhmt.wappaleksandrov

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import ru.nhmt.wappaleksandrov.databinding.ActivityMenuBinding

class MenuActivity : Activity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMap.setOnClickListener { startActivity(Intent(this, MapActivity::class.java)) }
    }
}