package club.anlan.lanlife.app.ui.screens

import FunCard
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import club.anlan.lanlife.app.R
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

/**
 *
 * 快捷界面
 *
 * @author lan
 * @date 2022/9/13 1:00
 * @version 1.0
 */
@Composable
fun HomeFrame() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                FunCard(
                    name = "PC", R.drawable.ic_computer, click = {
                        wake()
                    }
                )
                FunCard(
                    name = "测试1", R.drawable.ic_computer, click = {
                    }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FunCard(
                    name = "测试2", R.drawable.ic_computer, click = {}
                )
                FunCard(
                    name = "测试3", R.drawable.ic_computer, click = { }
                )
            }
        }
    }
}

fun wake() {
    Log.d("HomeFrame", "click the button")
    Thread {
        doWake("192.168.0.113", "a8-5e-45-2c-3a-84", 9)
    }.start()
}


fun doWake(ip: String, macAddr: String, port: Int) {
    val macByte = ByteArray(6)
    val ips = macAddr.split("-").toTypedArray()
    Log.d("HomeFrame", "$ips")
    for (i in 0..5) {
        macByte[i] = ips[i].toInt(16).toByte()
    }
    // 用来存储网络唤醒数据包
    val bys = ByteArray(6 + 16 * macByte.size)
    for (i in 0..5) {
        bys[i] = 0xff.toByte();
    }
    var i = 6
    while (i < bys.size) {
        System.arraycopy(macByte, 0, bys, i, macByte.size)
        i += macByte.size
    }

    // 将字符形式的IP地址转换成标准的IP地址
    val address = InetAddress.getByName(ip);
    // 生成标准的数据报
    val pack = DatagramPacket(bys, bys.size, address, port);
    DatagramSocket().use { socket ->
        try {
            // 发送魔法包
            socket.send(pack);
            Log.i("HomeFrame", "发送数据包")
            socket.close();
        } catch (e: Exception) {
            Log.e("HomeFrame", e.printStackTrace().toString())
        }
    }
}

@Preview
@Composable
fun HomeFramePreview() {
    HomeFrame()
}