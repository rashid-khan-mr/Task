package com.appointment.myapplication.util

import androidx.annotation.StringRes
import com.appointment.myapplication.key.R

/**
 * Error state holding values for error ui
 */
data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string
)