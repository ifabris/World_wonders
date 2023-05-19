package hr.algebra.world_wonders.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.algebra.world_wonders.api.PointsApi
import hr.algebra.world_wonders.api.QuizApi
import hr.algebra.world_wonders.api.WondersApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val POINTS_API_URL = "https://wondersapp-e8957-default-rtdb.firebaseio.com/wonder_points/"
private const val QUESTIONS_API_URL = "https://pastebin.com/"
private const val WONDERS_API_URL = "https://pastebin.com/"


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton // created at - Application#onCreate, destroyed at - Application#onDestroy
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @ExperimentalSerializationApi
    fun providePointsApi(okHttpClient: OkHttpClient) : PointsApi {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder()
            .baseUrl(POINTS_API_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(PointsApi::class.java)
    }

    @Provides
    @Singleton
    @ExperimentalSerializationApi
    fun provideQuestionsApi(okHttpClient: OkHttpClient) : QuizApi {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder()
            .baseUrl(QUESTIONS_API_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(QuizApi::class.java)
    }


    @Provides
    @Singleton
    @ExperimentalSerializationApi
    fun provideWondersApi(okHttpClient: OkHttpClient) : WondersApi {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder()
            .baseUrl(WONDERS_API_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(WondersApi::class.java)
    }
}