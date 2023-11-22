package com.example.atp10_navigationjetpackcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.atp10_navigationjetpackcomponent.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var nameTxt = binding.txtNombre.text.toString()
        var apellidoTxt = binding.txtApellidos.text.toString()
        var edadTxt = binding.txtEdad.text.toString().toInt()

        binding.btnEnviar.setOnClickListener(){
            if (nameTxt.isEmpty() || apellidoTxt.isEmpty()){
                Toast.makeText(this.context, "Introduce algo", Toast.LENGTH_SHORT).show()
            } else if (edadTxt == null){
                Toast.makeText(this.context, "Introduzca su edad", Toast.LENGTH_SHORT).show()
            }
            else {
                var action = ProfileFragmentDirections
                                .actionProfileFragmentToSaludoFragment(nameTxt, apellidoTxt)
                it.findNavController().navigate(action)

                action = ProfileFragmentDirections
                    .actionProfileFragmentToSaludoFragment(edadTxt)
                it.findNavController().navigate(action)
            }

        }
    }
}