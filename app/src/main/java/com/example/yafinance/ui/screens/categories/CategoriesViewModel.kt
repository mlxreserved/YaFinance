package com.example.yafinance.ui.screens.categories

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.domain.usecase.inter.GetCategoriesUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.BaseViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(private val getCategoriesUseCase: GetCategoriesUseCase) :
    BaseViewModel<List<Category>>() {

    init {
        loadCategories()
    }

    private fun loadCategories(isRetried: Boolean = false) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)

            when(val response = getCategoriesUseCase.getCategories()) {
                is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                is Result.Success<List<Category>> -> updateStateBasedOnListContent(response.result)
            }
        }
    }

    fun onRetryClicked() {
        loadCategories(isRetried = true)
    }

}