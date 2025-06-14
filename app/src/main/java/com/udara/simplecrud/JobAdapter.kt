package com.udara.simplecrud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView

class JobAdapter(private val jobList: List<Job>) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jobIcon: ImageView = itemView.findViewById(R.id.jobIcon)
        val jobTitle: TextView = itemView.findViewById(R.id.jobTitle)
        val jobType: TextView = itemView.findViewById(R.id.jobType)
        val jobSalary: TextView = itemView.findViewById(R.id.jobSalary)
        val jobLocation: TextView = itemView.findViewById(R.id.jobLocation)
        val bookmarkIcon: ImageView = itemView.findViewById(R.id.bookmarkIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobList[position]
        
        holder.jobTitle.text = job.title
        holder.jobType.text = job.type
        holder.jobSalary.text = job.salary
        holder.jobLocation.text = job.location
        
        holder.bookmarkIcon.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Job bookmarked: ${job.title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = jobList.size
}
