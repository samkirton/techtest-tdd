package com.worldpay.techtest.app.authorize

import androidx.lifecycle.ViewModel
import com.worldpay.techtest.app.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthorizeFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizeViewModel::class)
    internal abstract fun contributesAuthorizeViewModel(viewModel: AuthorizeViewModel): ViewModel
}