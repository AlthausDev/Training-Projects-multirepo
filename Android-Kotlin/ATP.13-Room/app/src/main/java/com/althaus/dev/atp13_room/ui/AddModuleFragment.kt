package com.althaus.dev.atp13_room.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.althaus.dev.atp13_room.ui.viewmodel.ModulosViewModel
import com.althaus.dev.atp13_room.ui.viewmodel.ModulosViewModelFactory
import com.althaus.dev.atp13_room.databinding.FragmentAddModuleBinding

class AddModuleFragment : Fragment() {
    private var _binding: FragmentAddModuleBinding? = null
    private val binding get() = _binding!!

    private val modulosViewModel: ModulosViewModel by activityViewModels{
        ModulosViewModelFactory((requireActivity().application as ModuleApp).appRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddModuleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddModule.setOnClickListener {
            modulosViewModel.insert(binding.editModuleName.text.toString(), binding.editModuleCredits.text.toString().toByte())
        }

        binding.btnClearList.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.alertClearTitle)
                .setMessage(R.string.alertClearMessage)
                .setNegativeButton(R.string.cancel){dialog,_ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.confirm){_,_ ->
                    modulosViewModel.clearAll()
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
