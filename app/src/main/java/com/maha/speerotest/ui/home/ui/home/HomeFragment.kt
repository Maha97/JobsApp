package com.maha.speerotest.ui.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maha.speerotest.R
import com.maha.speerotest.data.local.JobsDao
import com.maha.speerotest.data.model.Job
import com.maha.speerotest.data.remote.RetrofitBuilder
import com.maha.speerotest.data.repository.JobsRepository
import com.maha.speerotest.ui.home.JobAdapter
import com.maha.speerotest.utils.NetworkConnectivity
import com.maha.speerotest.utils.Status
import com.maha.speerotest.utils.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var jobsViewModel: HomeViewModel
    private lateinit var jobAdapter: JobAdapter

    lateinit  var recycler_view_jobs : RecyclerView
    lateinit  var progress_bar_jobs : ProgressBar

    @Inject
    lateinit var jobsDao: JobsDao
    @Inject
    lateinit var networkConnectivity: NetworkConnectivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // Set Up Recyclerview
        recycler_view_jobs=root.findViewById<RecyclerView>(R.id.rv_job_list)
        progress_bar_jobs=root.findViewById<ProgressBar>(R.id.progress_bar_jobs)
        setUpUi()
        setupViewModel()
        setUpObserver()

        return root
    }




    private fun setupViewModel() {
        jobsViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                JobsRepository(RetrofitBuilder.apiService, jobsDao,networkConnectivity)
            )
        ).get(jobsViewModel::class.java)
    }

    private fun setUpObserver() {
        jobsViewModel.getJobsList().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progress_bar_jobs.visibility = View.GONE
                    it.data?.let { jobs -> renderList(jobs)
                    }
                    //   progress_bar_jobs.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progress_bar_jobs.visibility = View.VISIBLE
                    //progress_bar_jobs.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progress_bar_jobs.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    fun setUpUi(){
        recycler_view_jobs.layoutManager = GridLayoutManager(activity,2)
        jobAdapter =
            JobAdapter(
                arrayListOf()
            )
        recycler_view_jobs.addItemDecoration(
            DividerItemDecoration(
                recycler_view_jobs.context,
                (recycler_view_jobs.layoutManager as GridLayoutManager).orientation
            )
        )
        recycler_view_jobs.adapter = jobAdapter
    }
    private fun renderList(jobs: List<Job>) {
        jobAdapter.addData(jobs)
        jobAdapter.notifyDataSetChanged()
    }
}