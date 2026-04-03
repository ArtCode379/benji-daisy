package com.benjianddaisy.store.di

import com.benjianddaisy.store.ui.viewmodel.AppViewModel
import com.benjianddaisy.store.ui.viewmodel.CartViewModel
import com.benjianddaisy.store.ui.viewmodel.CheckoutViewModel
import com.benjianddaisy.store.ui.viewmodel.BJDYSOnboardingVM
import com.benjianddaisy.store.ui.viewmodel.OrderViewModel
import com.benjianddaisy.store.ui.viewmodel.ProductDetailsViewModel
import com.benjianddaisy.store.ui.viewmodel.ProductViewModel
import com.benjianddaisy.store.ui.viewmodel.BJDYSSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        BJDYSSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        BJDYSOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}