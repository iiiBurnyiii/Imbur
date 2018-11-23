package com.example.burny.imbur.bindingAdapters

import androidx.databinding.DataBindingComponent
import com.example.burny.imbur.di.AppComponent
import com.example.burny.imbur.di.Scopes
import dagger.Component

@Scopes.DataBindingScope
@Component(dependencies = [AppComponent::class], modules = [
    BindingModule::class])
interface BindingComponent : DataBindingComponent {

    override fun getImageBindingAdapter(): ImageBindingAdapter
    override fun getListBindingAdapter(): ListBindingAdapter
    override fun getTitleBindingAdapter(): TitleBindingAdapter
    override fun getDimensionRatioBindingAdapter(): DimensionRatioBindingAdapter

}