package com.pasandevin.android.android_mvvm_metadata_viewer

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.pasandevin.android.android_mvvm_metadata_viewer.Models.DatabaseVideo
import com.pasandevin.android.android_mvvm_metadata_viewer.Models.DevByteVideo
import com.pasandevin.android.android_mvvm_metadata_viewer.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Dao
interface VideoDao {
    @Query("select * from databasevideo")
    fun getVideos(): LiveData<List<DatabaseVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<DatabaseVideo>)

}

@Database(entities = [DatabaseVideo::class], version = 1)
abstract class VideosDatabase: RoomDatabase() {
    abstract val videoDao: VideoDao

    class Callback @Inject constructor(
        private val database: Provider<VideosDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}
