package com.josealfonsomora.mishorasextras.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.josealfonsomora.mishorasextras.R
import com.josealfonsomora.mishorasextras.databinding.FragmentHorasExtrasBinding
import com.josealfonsomora.mishorasextras.extensions.print
import com.josealfonsomora.mishorasextras.extensions.toZonedDateTime
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class HorasExtrasFragment : Fragment() {

    private lateinit var binding: FragmentHorasExtrasBinding

    private val horaExtraAdapter by lazy { HoraExtraAdapter() }

    private val viewModel: HorasExtrasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(false)

        binding = FragmentHorasExtrasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }

        binding.timeList.adapter = horaExtraAdapter
        binding.timeList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.getHorasExtras().observe(viewLifecycleOwner, Observer {
            horaExtraAdapter.updateHorasExtras(it.map { horaExtra ->
                HoraExtraItem(
                    date = horaExtra.date.toZonedDateTime().print("dd/MM/yyyy"),
                    hours = horaExtra.hours,
                    price = horaExtra.price
                )
            })
        })
    }
}
