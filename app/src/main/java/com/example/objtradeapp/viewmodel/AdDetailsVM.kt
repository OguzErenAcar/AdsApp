package com.example.objtradeapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.objtradeapp.repository.AdsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdDetailsVM @Inject constructor(
   private val repository:AdsRepository
):ViewModel() {



}