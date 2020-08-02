package iman.mohammadpour.batman.di

import androidx.room.Room
import iman.mohammadpour.batman.BuildConfig
import iman.mohammadpour.batman.config.Const
import iman.mohammadpour.batman.data.api.MovieApi
import iman.mohammadpour.batman.data.dao.AppDatabase
import iman.mohammadpour.batman.data.repositories.MovieRepository
import iman.mohammadpour.batman.data.repositories.impl.MovieRepositoryImpl
import iman.mohammadpour.batman.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */


val repositoryModule = module {

    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }

}

val viewModelModule = module {

    viewModel { MainViewModel(get()) }

}

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single<MovieApi> { get<Retrofit>().create(MovieApi::class.java) }
}

val dataModule = module {

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "batman.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().movieDao() }

}