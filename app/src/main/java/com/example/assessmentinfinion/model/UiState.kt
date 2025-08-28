package com.example.assessmentinfinion.model

data class UiState<T>(
    val data: T? = null,
    val loading: Boolean = false,
    val error: String? = null
)
