package edu.singaporetech.sitiwsp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class UserPreferences(
    val IWSPOptions: String?,
)


class UserPreferencesRepository(private val context: Context) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)

    private object PreferencesKeys {
        val IWSPOptions = stringPreferencesKey("IWSPOptions")
    }

    val IWSPOptionsFlow: Flow<UserPreferences> = context.dataStore.data.map { preferences ->
        mapUserPreferences(preferences)
    }

    suspend fun storeIWSPOptions(IWSPOptions: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IWSPOptions] = IWSPOptions
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        return UserPreferences(
            IWSPOptions = preferences[PreferencesKeys.IWSPOptions],
        )
    }


    companion object {
        // Constant for naming our DataStore - you can change this if you want
        private const val USER_PREFERENCES_NAME = "user_preferences"

        // The usual for debugging
        private val TAG: String = "UserPreferencesRepository"

        // Boilerplate-y code for singleton: the private reference to this self
        @Volatile
        private var INSTANCE: UserPreferencesRepository? = null

        /**
         * Boilerplate-y code for singleton: to ensure only a single copy is ever present
         * @param context to init the datastore
         */
        fun getInstance(context: Context): UserPreferencesRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }

                val instance = UserPreferencesRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }
}