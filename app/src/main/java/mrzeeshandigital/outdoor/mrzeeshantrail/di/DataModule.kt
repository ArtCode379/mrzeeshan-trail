package mrzeeshandigital.outdoor.mrzeeshantrail.di

import mrzeeshandigital.outdoor.mrzeeshantrail.data.repository.CartRepository
import mrzeeshandigital.outdoor.mrzeeshantrail.data.repository.WQLNOOnboardingRepo
import mrzeeshandigital.outdoor.mrzeeshantrail.data.repository.OrderRepository
import mrzeeshandigital.outdoor.mrzeeshantrail.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        WQLNOOnboardingRepo(
            wqlnoOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}