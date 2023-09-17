package com.porfirio.navcomp.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.porfirio.navcomp.R
import com.porfirio.navcomp.data.model.User
import com.porfirio.navcomp.databinding.FragmentFormularioBinding


class FormularioFragment : Fragment() {

    private var _binding: FragmentFormularioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormularioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tietName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnVerifyDetails.isEnabled = validateFields()
            }
        })

        binding.tietMobile.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnVerifyDetails.isEnabled = validateFields()
            }
        })

        binding.btnVerifyDetails.setOnClickListener {

            val name = binding.tietName.text.toString()
            val mobile = binding.tietMobile.text.toString()

            /*val bundle = bundleOf(
                "name" to name,
                "mobile" to mobile
            )*/

            //findNavController().navigate(R.id.action_formularioFragment_to_datosFragment, bundle)

            val user = User(name, mobile)

            findNavController().navigate(FormularioFragmentDirections.actionFormularioFragmentToDatosFragment(
                name,
                mobile,
                user
            ))


        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun validateFields(): Boolean = (binding.tietName.text.toString().isNotEmpty() &&
            binding.tietMobile.text.toString().isNotEmpty() &&
            binding.tietMobile.text.toString().length == 10)


}