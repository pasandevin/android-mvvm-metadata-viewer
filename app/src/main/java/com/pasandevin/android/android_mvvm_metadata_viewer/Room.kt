package com.pasandevin.android.android_mvvm_metadata_viewer

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.pasandevin.android.android_mvvm_metadata_viewer.Models.DatabaseVideo
import com.pasandevin.android.android_mvvm_metadata_viewer.Models.DevByteVideo

@Dao
interface VideoDao {
    @Query("select * from databasevideo")
    fun getVideos(): LiveData<List<DatabaseVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<DatabaseVideo>)

}

@Database(entities = [DatabaseVideo::class], version = 1)
abstract class VideosDatabase: RoomDatabase() {
    abstract fun VideoDao(): VideoDao

    companion object {
        @Volatile
        private var INSTANCE: VideosDatabase? = null

        fun getDatabase(context: Context): VideosDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideosDatabase::class.java,
                    "videos_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }

        }

    }
}
