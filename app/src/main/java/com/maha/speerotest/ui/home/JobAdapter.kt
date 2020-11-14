package com.maha.speerotest.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maha.speerotest.R
import com.maha.speerotest.data.model.Job
import kotlinx.android.synthetic.main.item_job.view.*

class JobAdapter(private val jobList:ArrayList<Job>) :RecyclerView.Adapter<JobAdapter.JobViewHolder>(){


    class JobViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(job: Job) {
            itemView.tv_job_title.text = job.title
          itemView.tv_job_describtion.text=job.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder =
        JobViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_job, parent,
                false
            )
        )

    override fun getItemCount(): Int = jobList.size

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) = holder.bind(jobList[position])


    fun addData(list: List<Job>) {
        jobList.addAll(list)
    }

}