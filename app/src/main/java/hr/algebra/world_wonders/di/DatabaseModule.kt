package hr.algebra.world_wonders.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hr.algebra.world_wonders.db.WonderDatabase
import javax.inject.Singleton

private const val WONDER_DATABASE = "wonder_database"

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton // created at - Application#onCreate, destroyed at - Application#onDestroy
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : WonderDatabase {
        return Room.databaseBuilder(
            context,
            WonderDatabase::class.java,
            WONDER_DATABASE
        ).build()
    }
}