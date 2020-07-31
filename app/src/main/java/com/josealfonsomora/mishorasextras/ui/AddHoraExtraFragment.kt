package com.josealfonsomora.mishorasextras.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.josealfonsomora.mishorasextras.R
import com.josealfonsomora.mishorasextras.databinding.FragmentAddHoraExtraBinding
import com.josealfonsomora.mishorasextras.db.HoraExtra
import com.josealfonsomora.mishorasextras.db.HoraExtraDao
import com.josealfonsomora.mishorasextras.extensions.print
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class AddHoraExtraFragment : Fragment() {

    private lateinit var binding: FragmentAddHoraExtraBinding

    @Inject
    lateinit var horaExtraDao: HoraExtraDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(false)

        binding = FragmentAddHoraExtraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val timeNow = ZonedDateTime.now()
            dateTextView.text = timeNow.print("dd/MM/yyyy")
            dateTextView.tag = timeNow.toEpochSecond()

            add.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    horaExtraDao.add(
                        HoraExtra(
                            date = (dateTextView.tag as Long),
                            hours = hoursEditText.text.toString().toInt(),
                            price = BigDecimal(priceEditText.text.toString())
                        )
                    )
                }
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }

            dateTextView.setOnClickListener {
                val today = LocalDate.now()
                // date picker dialog
                // date picker dialog
                val picker = DatePickerDialog(
                    requireContext(),
                    OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        dateTextView.setText(
                            dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                        )
                        dateTextView.setTag(
                            LocalDate.of(year, monthOfYear, dayOfMonth).atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
                        )
                    }, today.year, today.monthValue, today.dayOfMonth
                )
                picker.show()
            }
        }
    }
}
