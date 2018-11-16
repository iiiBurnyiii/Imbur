package com.example.burny.imbur.di

import javax.inject.Scope

class Scopes {

    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class GalleryScope

    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class DataBindingScope

}