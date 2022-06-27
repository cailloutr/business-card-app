package com.example.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscard.App
import com.example.businesscard.databinding.ActivityMainBinding
import com.example.businesscard.util.Image

//TODO: 1 - Delete button
//TODO: 2 - Set an onLongClickListener to update an card

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    
    private val adapter by lazy { BusinessCardAdapter() }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBusinessCard()
        insertListeners()
    }

    private fun insertListeners() {
        // Add new card
        binding.btnAddNewCard.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }

        // Hide the FloatActionButton when scrolling to the bottom of the view
        binding.rvCards.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerView.canScrollVertically(Integer.MAX_VALUE)) {
                    binding.btnAddNewCard.show()
                } else {
                    binding.btnAddNewCard.hide()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(Integer.MAX_VALUE)) {
                    binding.btnAddNewCard.hide()
                }
            }
        })

        // Share when clicking on the card
        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
    }

    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this) { businessCards ->
            adapter.submitList(businessCards)
        }
    }

}