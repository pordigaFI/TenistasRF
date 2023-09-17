package com.porfirio.practica1.UI

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.porfirio.practica1.Model.TenistaEntity
import com.porfirio.practica1.application.TenistasDBApp
import com.porfirio.practica1.data.TenistaRepository
import com.porfirio.practica1.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var tenistas: List<TenistaEntity> = emptyList()
    private lateinit var repository: TenistaRepository
    private lateinit var tenistaAdapter: TenistaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = (application as TenistasDBApp).repository

        tenistaAdapter= TenistaAdapter(){tenista ->
            tenistaClicked(tenista)
        }

        binding.rvTenistas.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = tenistaAdapter
        }

        updateUI()

    }

    private fun updateUI(){
        lifecycleScope.launch {
            tenistas =repository.getAllTenistas()

            if(tenistas.isNotEmpty()){
                //hay por lo menos un regitro
                binding.tvSinRegistros.visibility= View.INVISIBLE
            } else {
                //No hay registros
                binding.tvSinRegistros.visibility = View.VISIBLE
            }
            tenistaAdapter.updateList(tenistas)
        }
    }

    fun click(view: View){
        val dialog = TenistaDialog(updateUI= {
            updateUI()
        }, message = { text ->
            message(text)
        })
        dialog.show(supportFragmentManager, "dialog")
    }

    private fun tenistaClicked(tenista: TenistaEntity){
        val dialog = TenistaDialog(newTenista = false, tenista= tenista, updateUI= {
            updateUI()
        }, message = { text ->
            message(text)
        })
        dialog.show(supportFragmentManager, "dialog")
    }

    private fun message(text: String){
        Snackbar.make(binding.cl, text, Snackbar.LENGTH_SHORT)
            .setTextColor(Color.parseColor("#FFFFFFFF"))
            .setBackgroundTint(Color.parseColor("#FF9E1734"))
            .show()
    }
}