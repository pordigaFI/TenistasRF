package com.porfirio.practica2.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.porfirio.practica2.R
import com.porfirio.practica2.application.TenistasRFApp
import com.porfirio.practica2.data.TenistaRepository
import com.porfirio.practica2.data.remote.model.TenistaDto
import com.porfirio.practica2.databinding.FragmentTenistasListBinding
import com.porfirio.practica2.ui.adapters.TenistasAdapter
import com.porfirio.practica2.util.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TenistasListFragment : Fragment() {

    private var _binding: FragmentTenistasListBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: TenistaRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTenistasListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = (requireActivity().application as TenistasRFApp).repository

        lifecycleScope.launch {
            //val call: Call<List<GameDto>> = repository.getGames("cm/games/games_list.php")
            val call: Call<List<TenistaDto>> = repository.getTenistasApiary()  //("tenistas/tenistas_list")

            call.enqueue(object: Callback<List<TenistaDto>> {
                override fun onResponse(
                    call: Call<List<TenistaDto>>,
                    response: Response<List<TenistaDto>>
                ) {

                    binding.pbLoading.visibility = View.GONE

                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.body()}")

                    response.body()?.let{ tenistas ->
                        binding.rvTenistas.apply {
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = TenistasAdapter(tenistas){ tenista ->
                                tenista.id?.let { id ->
                                    //Aquí va el código para la operación para ver los detalles
                                    requireActivity().supportFragmentManager.beginTransaction()
                                        .replace(R.id.fragment_container, TenistaDetailFragment.newInstance(id))
                                        .addToBackStack(null)
                                        .commit()
                                }
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<List<TenistaDto>>, t: Throwable) {
                    Log.d(Constants.LOGTAG, "Error: ${t.message}")

                    Toast.makeText(requireActivity(), "No hay conexión", Toast.LENGTH_SHORT).show()

                    binding.pbLoading.visibility = View.GONE

                }

            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}