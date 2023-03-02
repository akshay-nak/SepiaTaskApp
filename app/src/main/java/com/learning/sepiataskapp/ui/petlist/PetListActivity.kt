package com.learning.sepiataskapp.ui.petlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.sepiataskapp.R
import com.learning.sepiataskapp.ui.petlist.adapter.PetListAdapter
import kotlinx.android.synthetic.main.activity_pet_list.*

class PetListActivity : AppCompatActivity() {
    private val viewModel : PetListViewModel by viewModels()
    private val petsAdapter = PetListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_list)

        initView()
        initObservers()
        viewModel.getPetList()
    }

    private fun initView() {
        rv_pets.apply {
            layoutManager = LinearLayoutManager(this@PetListActivity, RecyclerView.VERTICAL,false)
            adapter = petsAdapter
        }
    }

    private fun initObservers() {
        viewModel.petListLiveData.observe(this){petList->
            petsAdapter.setData(petList)
        }
    }
}