package com.learning.sepiataskapp.ui.petlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.sepiataskapp.R
import com.learning.sepiataskapp.data.model.Pet
import com.learning.sepiataskapp.ui.petdetails.PetDetailsActivity
import com.learning.sepiataskapp.ui.petlist.adapter.PetListAdapter
import kotlinx.android.synthetic.main.activity_pet_list.*

class PetListActivity : AppCompatActivity(), PetListAdapter.PetListItemClickListener {
    private val viewModel: PetListViewModel by viewModels()
    private val petsAdapter = PetListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_list)

        initView()
        initObservers()
        viewModel.getPetList()
    }

    private fun initView() {
        petsAdapter.setPetListItemClickListener(this)
        rv_pets.apply {
            layoutManager = LinearLayoutManager(this@PetListActivity, RecyclerView.VERTICAL, false)
            adapter = petsAdapter
        }
    }

    private fun initObservers() {
        viewModel.petListLiveData.observe(this) { petList ->
            petsAdapter.setData(petList)
        }
    }

    override fun onPetItemClicked(pet: Pet) {
        val intent = Intent(this, PetDetailsActivity::class.java)
        intent.putExtra("pet_content_url", pet.contentURL)
        startActivity(intent)
    }
}