package com.porfirio.practica2.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.porfirio.practica2.R
import com.porfirio.practica2.application.TenistasRFApp
import com.porfirio.practica2.data.TenistaRepository
import com.porfirio.practica2.data.remote.model.TenistaDetailDto
import com.porfirio.practica2.databinding.FragmentTenistaDetailBinding
import com.porfirio.practica2.util.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TENISTA_ID = "tenista_id"

class TenistaDetailFragment : Fragment() {

    private var tenistaId: String? = null

    private var _binding: FragmentTenistaDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: TenistaRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            tenistaId = args.getString(TENISTA_ID)

            Log.d(Constants.LOGTAG, "Id recibido: $tenistaId")

            repository = (requireActivity().application as TenistasRFApp).repository

            lifecycleScope.launch {

                tenistaId?.let { id ->
                    val call: Call<TenistaDetailDto> = repository.getTenistaDetailApiary(id)

                    call.enqueue(object: Callback<TenistaDetailDto> {
                        override fun onResponse(
                            call: Call<TenistaDetailDto>,
                            response: Response<TenistaDetailDto>
                        ) {


                            binding.apply {
                                pbLoading.visibility = View.GONE

                                tvPais.text = response.body()?.pais
                                tvEstatura.text = response.body()?.estatura.toString()
                                tvRanking.text = response.body()?.ranking
                                tvPuntos.text = response.body()?.puntos.toString()
                                tvTitulos.text = response.body()?.titulos.toString()
                                tvIngresos.text = response.body()?.ingresos.toString()



                                Glide.with(requireContext())
                                    .load(response.body()?.imagen)
                                    .into(ivImage)
                            }

                        }

                        override fun onFailure(call: Call<TenistaDetailDto>, t: Throwable) {
                            binding.pbLoading.visibility = View.GONE

                            Toast.makeText(requireActivity(), "No hay conexión", Toast.LENGTH_SHORT).show()
                        }

                    })
                }

            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTenistaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(tenistaId: String) =
            TenistaDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(TENISTA_ID, tenistaId)
                }
            }
    }
}