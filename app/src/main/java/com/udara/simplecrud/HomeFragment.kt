package com.udara.simplecrud

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText

class HomeFragment : Fragment() {
    
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var jobAdapter: JobAdapter
    private lateinit var searchEditText: EditText
    private val jobList = mutableListOf<Job>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupRecyclerView()
        loadSampleJobs()
    }

    private fun initViews(view: View) {
        jobRecyclerView = view.findViewById(R.id.jobRecyclerView)
        searchEditText = view.findViewById(R.id.searchEditText)
    }

    private fun setupRecyclerView() {
        jobAdapter = JobAdapter(jobList)
        jobRecyclerView.layoutManager = LinearLayoutManager(context)
        jobRecyclerView.adapter = jobAdapter
    }

    private fun loadSampleJobs() {
        jobList.addAll(listOf(
            Job("Project Manager", "Fulltime", "20k-30k", "Colombo", R.drawable.card_profile),
            Job("Software Engineer", "Part time", "18k-35k", "Colombo", R.drawable.card_profile),
            Job("Quality Assurance Engineer", "Part time", "8k-12k", "Galle", R.drawable.card_profile),
            Job("Business Analyst", "Fulltime", "10k-12k", "Matara", R.drawable.card_profile),
            Job("Business Analyst", "Fulltime", "8k-13k", "Kurunegala", R.drawable.card_profile)
        ))
        jobAdapter.notifyDataSetChanged()
    }
}
