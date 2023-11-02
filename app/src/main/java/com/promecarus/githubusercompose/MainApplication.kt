package com.promecarus.githubusercompose

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.promecarus.githubusercompose.BuildConfig.BASE_URL
import com.promecarus.githubusercompose.BuildConfig.DB_NAME
import com.promecarus.githubusercompose.BuildConfig.DEBUG
import com.promecarus.githubusercompose.BuildConfig.GITHUB_API_KEY
import com.promecarus.githubusercompose.BuildConfig.PREF_HISTORY
import com.promecarus.githubusercompose.BuildConfig.PREF_SETTING
import com.promecarus.githubusercompose.data.local.database.FavoriteDatabase
import com.promecarus.githubusercompose.data.local.preference.HistoryPreference
import com.promecarus.githubusercompose.data.local.preference.SettingPreference
import com.promecarus.githubusercompose.data.remote.ApiService
import com.promecarus.githubusercompose.data.repository.FavoriteRepository
import com.promecarus.githubusercompose.data.repository.HistoryRepository
import com.promecarus.githubusercompose.data.repository.SettingRepository
import com.promecarus.githubusercompose.data.repository.UserRepository
import com.promecarus.githubusercompose.ui.viewmodel.DetailViewModel
import com.promecarus.githubusercompose.ui.viewmodel.FavoriteViewModel
import com.promecarus.githubusercompose.ui.viewmodel.ListViewModel
import com.promecarus.githubusercompose.ui.viewmodel.SettingViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(app)
        }
    }

    companion object {
        private val converter: GsonConverterFactory = GsonConverterFactory.create()
        private val interceptor = Interceptor {
            it.proceed(
                it.request().newBuilder().addHeader("Authorization", "token $GITHUB_API_KEY")
                    .build()
            )
        }
        private val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(if (DEBUG) BODY else NONE)
        private val client =
            OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(loggingInterceptor)
                .build()
        private val Context.history: DataStore<Preferences> by preferencesDataStore(name = PREF_HISTORY)
        private val Context.setting: DataStore<Preferences> by preferencesDataStore(name = PREF_SETTING)

        val app = module {
            single {
                Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(converter).client(client)
                    .build().create(ApiService::class.java)
            }

            single {
                Room.databaseBuilder(get(), FavoriteDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration().build()
            }

            single { HistoryPreference(androidContext().history) }

            single { SettingPreference(androidContext().setting) }

            single { FavoriteRepository(get()) }

            single { HistoryRepository(get()) }

            single { SettingRepository(get()) }

            single { UserRepository(get(), get()) }

            viewModel { DetailViewModel(get(), get()) }

            viewModel { FavoriteViewModel(get()) }

            viewModel { ListViewModel(get(), get()) }

            viewModel { SettingViewModel(get()) }
        }
    }
}
