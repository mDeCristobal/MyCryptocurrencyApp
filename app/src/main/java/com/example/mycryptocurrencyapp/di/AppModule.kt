package com.example.mycryptocurrencyapp.di

import com.example.mycryptocurrencyapp.BuildConfig
import com.example.mycryptocurrencyapp.common.Constants.BASE_URL
import com.example.mycryptocurrencyapp.common.ui.loadingDialog.LoadingDialogFragment
import com.example.mycryptocurrencyapp.features.coins.data.remote.CoinsApi
import com.example.mycryptocurrencyapp.features.coins.data.repository.CoinRepositoryImpl
import com.example.mycryptocurrencyapp.features.coins.domain.repository.CoinRepository
import com.example.mycryptocurrencyapp.features.coins.domain.use_case.get_coin.GetCoinUseCase
import com.example.mycryptocurrencyapp.features.coins.domain.use_case.get_coins.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesInterceptor() : HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    @Provides
    @Singleton
    fun providesOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
       return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()
    }

    @Provides
    @Singleton
    fun providesCoinApi(okHttpClient: OkHttpClient) : CoinsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CoinsApi::class.java)

    }

    @Provides
    @Singleton
    fun providesRepository(api: CoinsApi) : CoinRepository {
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesLoadingDialogFragment() : LoadingDialogFragment {
        return LoadingDialogFragment()
    }

    //UseCases
    @Provides
    @Singleton
    fun providesGetCoinsUseCase(repository : CoinRepository) : GetCoinsUseCase {
        return GetCoinsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetCoinUseCase(repository : CoinRepository) : GetCoinUseCase {
        return GetCoinUseCase(repository)
    }

}