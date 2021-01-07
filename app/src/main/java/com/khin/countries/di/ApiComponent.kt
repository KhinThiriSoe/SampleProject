package com.khin.countries.di

import com.khin.countries.model.CountriesService
import com.khin.countries.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)
}