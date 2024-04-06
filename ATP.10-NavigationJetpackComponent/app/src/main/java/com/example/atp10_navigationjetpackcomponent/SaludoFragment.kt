package com.example.atp10_navigationjetpackcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.atp10_navigationjetpackcomponent.databinding.FragmentSaludoBinding

class SaludoFragment : Fragment() {

    private var _binding : FragmentSaludoBinding? = null
    private val binding get() = _binding!!
    private val args: SaludoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSaludoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewNombre.text = "${getString(R.string.saludo, args.name, args.apellidos)}"

    }
}