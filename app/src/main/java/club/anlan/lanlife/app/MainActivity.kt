package club.anlan.lanlife.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import club.anlan.lanlife.app.client.impl.DefaultLocationClient
import club.anlan.lanlife.app.service.LocationService
import club.anlan.lanlife.app.ui.screens.MainFrame
import club.anlan.lanlife.app.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            //实例系统UI控制
            val systemUiController = rememberSystemUiController()
            //设置为透明状态栏
            systemUiController.setStatusBarColor(
                Color.Transparent, darkIcons = MaterialTheme.colors.isLight
            )
            AppTheme {
                Surface(
                ) {
                    MainFrame()
                }
            }
        }
    }

    /**
     * 初始化
     */
    private fun init() {
        initService()
        initAMap()
    }

    /**
     * 初始化 service
     */
    private fun initService() {
        this.startForegroundService(Intent(this, LocationService::class.java))
    }

    /**
     * 初始化地图
     */
    private fun initAMap() {
        val client = application as DefaultLocationClient
        client.init(this, LocationService())
    }


}
