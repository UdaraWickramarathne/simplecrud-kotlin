package com.udara.simplecrud

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.udara.simplecrud.databinding.FragmentAddJobBinding

class AddJobFragment : Fragment() {
    
    private var _binding: FragmentAddJobBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddJobBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupSpinner()
        setupClickListeners()
    }
    
    private fun setupSpinner() {
        val employmentTypes = arrayOf("Fulltime", "Part-time", "Contract", "Internship")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            employmentTypes
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerEmploymentType.adapter = adapter
    }
    
    private fun setupClickListeners() {
        binding.btnAddJob.setOnClickListener {
            // Handle add job button click
            addJob()
        }
    }
    
    private fun addJob() {
        val jobTitle = binding.etJobTitle.text.toString().trim()
        val employmentType = binding.spinnerEmploymentType.selectedItem.toString()
        val salary = binding.etSalary.text.toString().trim()
        val location = binding.etLocation.text.toString().trim()
        
        // Validate input fields
        when {
            jobTitle.isEmpty() -> {
                binding.etJobTitle.error = "Job title is required"
                binding.etJobTitle.requestFocus()
                return
            }
            salary.isEmpty() -> {
                binding.etSalary.error = "Salary is required"
                binding.etSalary.requestFocus()
                return
            }
            location.isEmpty() -> {
                binding.etLocation.error = "Location is required"
                binding.etLocation.requestFocus()
                return
            }
        }
        
        // Clear any existing errors
        binding.etJobTitle.error = null
        binding.etSalary.error = null
        binding.etLocation.error = null
        
        // Process the job data (you can add database logic here)
        Toast.makeText(requireContext(), "Job added successfully!", Toast.LENGTH_SHORT).show()
        
        // Clear form fields
        clearForm()
    }
    
    private fun clearForm() {
        binding.etJobTitle.text?.clear()
        binding.etSalary.text?.clear()
        binding.etLocation.text?.clear()
        binding.spinnerEmploymentType.setSelection(0)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
