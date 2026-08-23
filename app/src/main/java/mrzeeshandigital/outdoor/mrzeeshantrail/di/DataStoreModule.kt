package mrzeeshandigital.outdoor.mrzeeshantrail.di

import mrzeeshandigital.outdoor.mrzeeshantrail.data.datastore.WQLNOOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { WQLNOOnboardingPrefs(androidContext()) }
}