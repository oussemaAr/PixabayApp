package io.studio.pixabayapp.common.ui

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import io.studio.pixabayapp.R

fun Context.createChip(content: String): Chip {
    return Chip(this).apply {
        text = content
        setChipBackgroundColorResource(R.color.teal_200)
        isCloseIconVisible = false
        setTextColor(ContextCompat.getColor(context, R.color.white))
    }
}