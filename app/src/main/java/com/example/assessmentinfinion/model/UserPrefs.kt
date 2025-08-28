package com.example.assessmentinfinion.model

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val PREFS_NAME = "user_prefs"
val Context.dataStore by preferencesDataStore(PREFS_NAME)

class UserPrefs @Inject constructor(@ApplicationContext private val context: Context) {
    private val KEY_FAV_CITY = stringPreferencesKey("fav_city")

    val favouriteCity: Flow<String?> = context.dataStore.data.map { it[KEY_FAV_CITY] }

    suspend fun setFavouriteCity(city: String) {
        context.dataStore.edit { it[KEY_FAV_CITY] = city }
    }
}