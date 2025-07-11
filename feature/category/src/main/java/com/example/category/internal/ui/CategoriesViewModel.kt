package com.example.category.internal.ui

import androidx.lifecycle.viewModelScope
import com.example.domain.model.category.Category
import com.example.domain.usecase.category.inter.GetCategoriesUseCase
import com.example.ui.baseViewModel.BaseViewModel
import com.example.ui.data.state.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.model.result.Result

class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel<List<Category>>() {

    private var _originalCategories: List<Category> = emptyList()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories(isRetried: Boolean = false) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)

            when (val response = getCategoriesUseCase.getCategories()) {
                is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                is Result.Success<List<Category>> -> {
                    _originalCategories = response.result
                    updateStateBasedOnListContent(response.result)
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun findCategories() {
        val currentState = screenState.value
        if (currentState is ScreenState.Success) {
            updateState(
                ScreenState.Success(
                    filterCategories(
                        _originalCategories,
                        _searchQuery.value
                    )
                )
            )
        }
    }

    private fun filterCategories(categories: List<Category>, query: String): List<Category> {
        return if (query.isBlank()) categories
        else categories.filter { it.title.contains(query, ignoreCase = true) }
    }

    fun onRetryClicked() {
        loadCategories(isRetried = true)
    }
}