package mrzeeshandigital.outdoor.mrzeeshantrail.di

import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.AppViewModel
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.CartViewModel
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.CheckoutViewModel
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.WQLNOOnboardingVM
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.OrderViewModel
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.ProductDetailsViewModel
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.ProductViewModel
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.WQLNOSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        WQLNOSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        WQLNOOnboardingVM(
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