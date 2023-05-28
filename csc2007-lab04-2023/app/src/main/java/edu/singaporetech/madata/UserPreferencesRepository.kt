package edu.singaporetech.madata

import android.content.Context
import androidx.datastore.core.DataStore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * This data class holds the preference settings that are saved in the DataStore. It is exposed
 * via the Flow interface.
 */
data class UserPreferences(val layout: String)


/**
 * Class that handles saving and retrieving user preferences, utilizing Preferences DataStore. This
 * class may be utilized in either the ViewModel or an Activity, depending on what preferences are
 * being saved.
 */
class UserPreferencesRepository(private val context: Context) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)

    private object PreferencesKeys {
        val LAYOUT = stringPreferencesKey("layout")
    }

    val layoutFlow: Flow<UserPreferences> = context.dataStore.data.map { preferences ->
        mapUserPreferences(preferences)
    }

    suspend fun storeLayout(layout: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAYOUT] = layout
        }
    }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        return UserPreferences(preferences[PreferencesKeys.LAYOUT] ?: "LINEAR")
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