package com.khin.countries.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.khin.countries.R
import com.khin.countries.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var countriesList: RecyclerView
    lateinit var listError: TextView
    lateinit var progressLoading: ProgressBar
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    lateinit var viewModel: ListViewModel
    private val countryListAdapter: CountryListAdapter by lazy { CountryListAdapter(arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countriesList = findViewById(R.id.countriesList)
        listError = findViewById(R.id.list_error)
        progressLoading = findViewById(R.id.progress_circular)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryListAdapter
        }

        observeViewModel()
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this) { countries ->
            countries?.let {
                countriesList.visibility = View.VISIBLE
                countryListAdapter.updateCountries(it)
            }
        }

        viewModel.countryLoadError.observe(this) { isError ->
            isError?.let {
                listError.visibility = if (isError) View.VISIBLE else View.GONE
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                progressLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
                if (isLoading) {
                    listError.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }
            }
        }
    }
}