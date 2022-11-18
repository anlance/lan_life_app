package club.anlan.lanlife.app.model.http.client

import club.anlan.lanlife.app.model.domain.LocationRes
import club.anlan.lanlife.app.model.domain.ResultMessage
import club.anlan.lanlife.app.model.http.Network
import retrofit2.http.Body
import retrofit2.http.POST
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 *
 * basic client
 *
 * @author lan
 * @date 2022/11/13 14:47
 * @version 1.0
 */
interface BasicHttpClient {

    @POST("/basic/user/saveLocation")
    suspend fun saveLocation(@Body locationRes: LocationRes): ResultMessage<JvmType.Object>

    companion object {
        fun instance(): BasicHttpClient {
            return Network.createNetWork(BasicHttpClient::class.java)
        }
    }
}