package com.udara.simplecrud

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot

class HomeFragment : Fragment() {
    
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var jobAdapter: JobAdapter
    private lateinit var searchEditText: EditText
    private val jobList = mutableListOf<Job>()
    private val filteredJobList = mutableListOf<Job>() // For search results

    private lateinit var database : DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance().reference.child("jobs")

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupRecyclerView()
        loadJobsFromFirebase()
        setupSearch() // Add search setup
    }

    private fun initViews(view: View) {
        jobRecyclerView = view.findViewById(R.id.jobRecyclerView)
        searchEditText = view.findViewById(R.id.searchEditText)
    }

    private fun setupRecyclerView() {
        jobAdapter = JobAdapter(filteredJobList)
        jobRecyclerView.layoutManager = LinearLayoutManager(context)
        jobRecyclerView.adapter = jobAdapter
    }

    private fun loadSampleJobs() {
        jobList.addAll(listOf(
            Job("Project Manager", "Fulltime", "20k-30k", "Colombo"),
            Job("Software Engineer", "Part time", "18k-35k", "Colombo"),
            Job("Quality Assurance Engineer", "Part time", "8k-12k", "Galle"),
            Job("Business Analyst", "Fulltime", "10k-12k", "Matara"),
            Job("Business Analyst", "Fulltime", "8k-13k", "Kurunegala"),
            Job("Business Analyst", "Fulltime", "8k-13k", "Kurunegala"),
            Job("Business Analyst", "Fulltime", "8k-13k", "Kurunegala"),
            Job("Business Analyst", "Fulltime", "8k-13k", "Kurunegala"),
            Job("Business Analyst", "Fulltime", "8k-13k", "Kurunegala"),
            Job("Business Analyst", "Fulltime", "8k-13k", "Kurunegala"),
        ))
        filteredJobList.clear()
        filteredJobList.addAll(jobList)
        jobAdapter.notifyDataSetChanged()
    }

    private fun setupSearch() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterJobs(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterJobs(query: String) {
        val lowerQuery = query.lowercase()
        filteredJobList.clear()
        if (lowerQuery.isEmpty()) {
            filteredJobList.addAll(jobList)
        } else {
            filteredJobList.addAll(
                jobList.filter {
                    it.title.lowercase().contains(lowerQuery) ||
                    it.type.lowercase().contains(lowerQuery) ||
                    it.salary.lowercase().contains(lowerQuery) ||
                    it.location.lowercase().contains(lowerQuery)
                }
            )
        }
        jobAdapter.notifyDataSetChanged()
    }

    private fun loadJobsFromFirebase() {
        jobList.clear()
        filteredJobList.clear()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                jobList.clear()

                for (jobSnapshot in snapshot.children) {
                    val job = jobSnapshot.getValue(Job::class.java)
                    job?.let {
                        // Set the Firebase key as the job ID
                        jobList.add(it)
                    }
                }

                // Update filtered list and refresh adapter
                filteredJobList.clear()
                filteredJobList.addAll(jobList)
                jobAdapter.notifyDataSetChanged()

                // Hide progress indicator
                // progressBar.visibility = View.GONE
            }

            override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                // Handle error - log or show message to user
                android.util.Log.e("HomeFragment", "Error loading jobs: ${error.message}")

                // Hide progress indicator
                // progressBar.visibility = View.GONE
            }
        })
    }
}