package ca.sheridan.sharmupm.assignment2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameScore::class], version = 1, exportSchema = false)
abstract class GameScoreDatabase: RoomDatabase(){

    abstract val gamescoreDao: GameScoreDao

    companion object{

        @Volatile
        private var INSTANCE: GameScoreDatabase? = null

        fun getInstance(context: Context): GameScoreDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GameScoreDatabase::class.java,
                        "game_score_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}