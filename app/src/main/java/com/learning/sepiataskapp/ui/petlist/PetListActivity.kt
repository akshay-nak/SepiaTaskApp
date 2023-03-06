package com.learning.sepiataskapp.ui.petlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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
    }

    private fun initView() {
        petsAdapter.setPetListItemClickListener(this)
        rv_pets.apply {
            layoutManager = LinearLayoutManager(this@PetListActivity, RecyclerView.VERTICAL, false)
            adapter = petsAdapter
        }
        viewModel.getConfig()
    }

    private fun initObservers() {
        viewModel.petListLiveData.observe(this) { petList ->
            petsAdapter.setData(petList)
        }

        viewModel.configLiveData.observe(this) { allowAppUsage ->
            if (allowAppUsage) {
                viewModel.getPetList()
            } else {
                showAppBlockedAlert()
            }
        }
    }

    override fun onPetItemClicked(pet: Pet) {
        val intent = Intent(this, PetDetailsActivity::class.java)
        intent.putExtra("pet_content_url", pet.contentURL)
        startActivity(intent)
    }

    private fun showAppBlockedAlert() {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.alert_title))
            setMessage(R.string.alert_message)
            setCancelable(false)
            setPositiveButton(R.string.button_ok) { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            show()
        }
    }
}