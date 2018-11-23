package com.example.burny.imbur.bindingAdapters

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.BindingAdapter

class DimensionRatioBindingAdapter {

    @BindingAdapter("dimensionRatio")
    fun View.setDimensionRatio(dimensionRatio: String) {
        val viewParent = this.parent
        val layout: ConstraintLayout? = when(viewParent) {
            is ConstraintLayout -> viewParent
            else -> null
        }

        if (layout != null) {
            val set = ConstraintSet()
            with(set) {
                clone(layout)
                setDimensionRatio(this@setDimensionRatio.id, dimensionRatio)
                applyTo(layout)
            }
        }
    }

}