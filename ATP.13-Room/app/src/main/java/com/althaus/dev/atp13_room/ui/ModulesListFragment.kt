package com.althaus.dev.atp13_room.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.fragment.app.activityViewModels
import androidx.compose.foundation.lazy.LazyColumn
import com.althaus.dev.atp13_room.core.ModuleApp
import com.althaus.dev.atp13_room.ui.viewmodel.ModulosViewModel
import com.althaus.dev.atp13_room.ui.viewmodel.ModulosViewModelFactory
import com.althaus.dev.atp13_room.databinding.FragmentItemListBinding
import com.althaus.dev.atp13_room.data.database.Module

/**
 * A fragment representing a list of Items.
 */
class ModulesListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ModulosViewModel by activityViewModels {
        ModulosViewModelFactory(
            (requireActivity().application as ModuleApp).appRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.setContent {
            val listaModulos = viewModel.allModules.observeAsState(emptyList())
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(listaModulos.value.size) { index ->
                    RegistroModulo(listaModulos.value[index])
                }
            }
        }
    }

    @Composable
    private fun RegistroModulo(module: Module) {
        Row {
            Text(text = module.id.toString(), Modifier.weight(1.0f))
            Text(text = module.name, Modifier.weight(3.0f))
            Text(text = module.credits.toString(), Modifier.weight(1.0f))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
