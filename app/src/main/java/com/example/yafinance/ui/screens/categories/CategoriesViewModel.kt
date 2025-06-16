package com.example.yafinance.ui.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.domain.usecase.inter.GetCategoriesUseCase
import com.example.yafinance.ui.utils.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val getCategoriesUseCase: GetCategoriesUseCase) : ViewModel() {

    private val _categoriesState: MutableStateFlow<ScreenState<List<Category>>> =
        MutableStateFlow(ScreenState.Loading)
    val categoriesState: StateFlow<ScreenState<List<Category>>> = _categoriesState.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _categoriesState.update { ScreenState.Loading }
            try {
                val categories = getCategoriesUseCase.getCategories()

                if (categories.isEmpty()) {
                    _categoriesState.update { ScreenState.Empty }
                } else {
                    _categoriesState.update {
                        ScreenState.Success(
                            result = categories
                        )
                    }
                }
            } catch (e: Exception) {
                _categoriesState.update {
                    ScreenState.Error(
                        e.message ?: ""
                    )
                }
            }
        }
    }

}