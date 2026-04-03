package com.benjianddaisy.store.data.repository

import com.benjianddaisy.store.data.datastore.BJDYSOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class BJDYSOnboardingRepo(
    private val bjdysOnboardingStoreManager: BJDYSOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return bjdysOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            bjdysOnboardingStoreManager.setOnboardedState(state)
        }
    }
}