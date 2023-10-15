package com.ajs.virtusademoapp.presentation.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajs.virtusademoapp.databinding.ActivityMainBinding
import com.ajs.virtusademoapp.presentation.adapters.UserDetailAdapter
import com.ajs.virtusademoapp.presentation.State
import com.virtusademoapp.presentation.splash.UserDataViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: UserDataViewModel by viewModels()
    private lateinit var userDetailAdapter: UserDetailAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
        //observer for user data changes
        viewModel.usersList.observe(this, { data ->
            userDetailAdapter.updateData(data)
        })
        //first time fetching data
        lifecycleScope.launch {
            viewModel.getUserDataResponse()
                .collect {
                    if (it is State.DataState) {
                        viewModel.updateUsersList(it.data.data)
                    }
                }
        }
    }
//Initializing the recyclerview
    private fun initRecyclerview() {
        userDetailAdapter = UserDetailAdapter(ArrayList())
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userDetailAdapter
        }
    }
}