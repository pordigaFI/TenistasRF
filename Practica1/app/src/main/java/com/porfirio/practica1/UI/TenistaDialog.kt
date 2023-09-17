package com.porfirio.practica1.UI

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.porfirio.practica1.Model.TenistaEntity
import com.porfirio.practica1.application.TenistasDBApp
import com.porfirio.practica1.data.TenistaRepository
import com.porfirio.practica1.databinding.TenistaDialogBinding
import kotlinx.coroutines.launch
import java.io.IOException

class TenistaDialog(
    private val newTenista: Boolean = true,
    private var tenista: TenistaEntity = TenistaEntity(
        nombre = "",
        ranking = "",
        puntos = ""
    ),
    private val updateUI: () -> Unit,
    private val message: (String) -> Unit
): DialogFragment(){
        private var _binding: TenistaDialogBinding? = null
        private val binding get() = _binding!!

        private lateinit var builder: AlertDialog.Builder
        private lateinit var dialog: Dialog

        private var saveButton: Button? = null
        private lateinit var repository: TenistaRepository
        //Se configura el dialogo inicial

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{

            _binding = TenistaDialogBinding.inflate(requireActivity().layoutInflater)
            repository = (requireContext().applicationContext as TenistasDBApp).repository
            builder = AlertDialog.Builder(requireContext())

            binding.apply{
                tietNombre.setText(tenista.nombre)
                tietRanking.setText(tenista.ranking)
                tietPuntos.setText(tenista.puntos)
            }

            dialog = if (newTenista) {
                buildDialog("Guardar", "Cancelar", {
                    //Create (Guardar)
                    tenista.nombre = binding.tietNombre.text.toString()
                    tenista.ranking = binding.tietRanking.text.toString()
                    tenista.puntos = binding.tietPuntos.text.toString()

                    try {
                        lifecycleScope.launch {
                            repository.insertTenista(tenista)
                        }

                        message("Datos guardados")

                        //Actualizar la UI
                        updateUI()
                    } catch (e: IOException) {
                        e.printStackTrace()
                        message("Error al guardar")
                    }
                }, {
                    //Cancelar
                })
            }else{
                buildDialog("Actualizar", "Borrar", {
                    //Create (Guardar)
                    tenista.nombre = binding.tietNombre.text.toString()
                    tenista.ranking = binding.tietRanking.text.toString()
                    tenista.puntos = binding.tietPuntos.text.toString()

                    try {
                        lifecycleScope.launch {
                            repository.updateTenista(tenista)
                        }

                        message("Actualización exitosa")

                        //Actualizar la UI
                        updateUI()
                    } catch (e: IOException) {
                        e.printStackTrace()
                        message("Error al actualizar")
                    }
                }, {
                    //Delete

                    AlertDialog.Builder(requireContext())
                        .setTitle("Confirmación")
                        .setMessage("¿Realmente deseas eliminar el elemento ${tenista.nombre}?")
                        .setPositiveButton("Aceptar"){ _,_ ->
                            try{
                                lifecycleScope.launch{
                                    repository.deleteTenista(tenista)
                                }

                                message("Tenista eliminado exitosamente")

                                //Actualizar la UI
                                updateUI()
                            }catch (e: IOException){
                                e.printStackTrace()
                                message("Error al eliminar al tenista")
                            }
                        }
                        .setNegativeButton("Cancelar"){ dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                })
            }
            return dialog
        }
        override fun onDestroy(){
            super.onDestroy()
            _binding = null

        }

        override fun onStart(){
            super.onStart()

            val alertDialog =
                dialog as AlertDialog   //Lo usamos para emplear el método getButton
            saveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            saveButton?.isEnabled = false

            binding.tietNombre.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    TODO("Not yet implemented")
                }

                override fun afterTextChanged(s: Editable?) {
                    saveButton?.isEnabled = validateFields()
                }
            })

            binding.tietRanking.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    TODO("Not yet implemented")
                }

                override fun afterTextChanged(s: Editable?) {
                    saveButton?.isEnabled = validateFields()
                }
            })

            binding.tietPuntos.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    TODO("Not yet implemented")
                }

                override fun afterTextChanged(s: Editable?) {
                    saveButton?.isEnabled = validateFields()
                }
            } )
        }

        private fun validateFields() =
            (binding.tietNombre.text.toString().isNotEmpty() && binding.tietRanking.text.toString().isNotEmpty() && binding.tietPuntos.text.toString().isNotEmpty())

        private fun  buildDialog(
            btn1Text: String,
            btn2Text: String,
            positiveButton: () -> Unit,
            negativeButton: () -> Unit
        ): Dialog =
            builder.setView(binding.root)
                .setTitle("Tenista")
                .setPositiveButton(btn1Text, DialogInterface.OnClickListener { dialog, which ->
                    positiveButton()
                })
                .setNegativeButton(btn2Text) { _, _ ->
                    //Acción para el botón negativo
                    negativeButton()
                }
                .create()
    }

