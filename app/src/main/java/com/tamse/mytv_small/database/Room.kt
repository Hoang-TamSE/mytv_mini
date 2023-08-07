package com.tamse.mytv_small.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.tamse.mytv_small.data.model.Channel
import dagger.Provides
import javax.inject.Singleton

@Dao
interface ChannelDao {
    @Query("select * from channel")
    fun getChannels(): LiveData<List<DatabaseChannel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(channels: List<DatabaseChannel>)
}

@Database(entities = [DatabaseChannel::class], version = 2, exportSchema = false)
abstract class ChannelsDatabase: RoomDatabase() {
    abstract fun channelDao(): ChannelDao
}

