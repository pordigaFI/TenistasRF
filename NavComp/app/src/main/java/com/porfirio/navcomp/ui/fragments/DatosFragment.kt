package com.porfirio.navcomp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.porfirio.navcomp.R
import com.porfirio.navcomp.databinding.FragmentDatosBinding



class DatosFragment : Fragment() {

    private var _binding: FragmentDatosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDatosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //el objeto arguments ya se crea y ahí viene el bundle
        //val name = arguments?.getString("name", "")
        //val mobile = arguments?.getString("mobile", "")

        val args: DatosFragmentArgs by navArgs()

        val name = args.name
        val mobile = args.mobile
        val user = args.user

        binding.tvName.text = user?.name
        binding.tvMobile.text = user?.mobile



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}