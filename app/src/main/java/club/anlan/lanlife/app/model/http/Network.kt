package club.anlan.lanlife.app.model.http

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 *
 * 网络请求
 *
 * @author lan
 * @date 2022/11/13 14:42
 * @version 1.0
 */
object Network {

    private const val BASE_URl = "http://anlan.club"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URl)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()


    fun <T> createNetWork(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}