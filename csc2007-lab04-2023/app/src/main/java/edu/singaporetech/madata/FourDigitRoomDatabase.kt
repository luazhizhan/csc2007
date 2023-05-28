package edu.singaporetech.madata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FourDigit::class],
    version = 1,
    exportSchema = true
)
abstract class FourDigitRoomDatabase : RoomDatabase() {
    abstract fun fourDigitDao(): FourDigitDao

    companion object {

        @Volatile
        private var INSTANCE: FourDigitRoomDatabase? = null

        fun getDatabase(context: Context): FourDigitRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): FourDigitRoomDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                FourDigitRoomDatabase::class.java,
                "four_digit_database"
            )
                .build()
        }
    }
}