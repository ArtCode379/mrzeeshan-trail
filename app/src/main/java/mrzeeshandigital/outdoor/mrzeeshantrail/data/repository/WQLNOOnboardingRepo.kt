package mrzeeshandigital.outdoor.mrzeeshantrail.data.repository

import mrzeeshandigital.outdoor.mrzeeshantrail.data.datastore.WQLNOOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WQLNOOnboardingRepo(
    private val wqlnoOnboardingStoreManager: WQLNOOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return wqlnoOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            wqlnoOnboardingStoreManager.setOnboardedState(state)
        }
    }
}