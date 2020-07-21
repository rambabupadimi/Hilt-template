package com.example.mobileapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileapplication.Item
import com.example.mobileapplication.R
import com.example.mobileapplication.helper.Resource
import com.example.mobileapplication.helper.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.remote_fragment.*

@AndroidEntryPoint
class RemoteFragment : Fragment(), CommentsAdapter.AdapterCallback {

    private val PAGE = 1;
    private val APIKEY = "2247b343"
    private val SEARCHKEY = "parasite"

    lateinit var adapter:CommentsAdapter
    var listItems: ArrayList<Item> = ArrayList()

    companion object {
        fun newInstance() = RemoteFragment()
    }

    private  val remoteViewModel: RemoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.remote_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        loadComments()
    }

    private fun initRecyclerView() {
        adapter = CommentsAdapter(listItems,this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

    }
    private fun loadComments() {

        remoteViewModel.getComments(PAGE,APIKEY,SEARCHKEY)
        remoteViewModel.commentsResponse.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE

                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE

                }
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    adapter.setItems(it.data?.Search as ArrayList<Item>)
                }
            }
        });
    }

    override fun onItemClicked(menuPosition: Int?) {
    }


}