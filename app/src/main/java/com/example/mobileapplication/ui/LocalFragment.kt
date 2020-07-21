package com.example.mobileapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileapplication.R
import com.example.mobileapplication.data.local.posts.PostEntity
import com.example.mobileapplication.helper.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.local_fragment.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.nextDown
import kotlin.math.nextTowards

@AndroidEntryPoint
class LocalFragment  : Fragment() , PostsAdapter.AdapterCallback {



    lateinit var adapter:PostsAdapter
    var listItems: ArrayList<PostEntity> = ArrayList()

    private  val viewModel1: LocalViewModel by viewModels()

    companion object {
        fun newInstance() = LocalFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.local_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        viewModel1.getPosts()
        observePosts()
        insertPost()

    }

    private fun insertPost() {
        btnPlus.setOnClickListener {
            val postEntity = PostEntity(Random().nextInt(1000),"testing string","testing message");
            viewModel1.insertPost(postEntity)
        }
    }

    private fun initRecyclerView() {
        adapter = PostsAdapter(listItems,this)
        localRecyclerView.setHasFixedSize(true)
        localRecyclerView.layoutManager = LinearLayoutManager(context)
        localRecyclerView.adapter = adapter

    }

    private fun observePosts() {
        viewModel1.postResponse.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    progressbarLocal.visibility = View.VISIBLE

                }
                Status.ERROR -> {
                    progressbarLocal.visibility = View.GONE

                }
                Status.SUCCESS -> {
                    progressbarLocal.visibility = View.GONE
                    adapter.setItems(it.data as ArrayList<PostEntity>)
                }
            }
        })
    }

    override fun onItemClicked(menuPosition: Int?) {
    }


}