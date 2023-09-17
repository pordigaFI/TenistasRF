package com.porfirio.practica2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.porfirio.practica2.R
import com.porfirio.practica2.databinding.ActivityMainBinding
import com.porfirio.practica2.ui.fragments.TenistasListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /*private lateinit var repository: GameRepository
    private lateinit var retrofit: Retrofit*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(R.style.Theme_VideoGamesRF)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TenistasListFragment())
                .commit()
        }

    }
}