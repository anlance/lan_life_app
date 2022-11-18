package club.anlan.lanlife.app.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import club.anlan.lanlife.app.model.domain.NavigationItem

/**
 *
 * 主页面
 *
 * @author lan
 * @date 2022/9/13 1:00
 * @version 1.0
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainFrame() {

    val navigationItems = listOf(
        NavigationItem(title = "快捷", icon = Icons.Filled.Home),
        NavigationItem(title = "分析", icon = Icons.Filled.DateRange),
        NavigationItem(title = "我的", icon = Icons.Filled.Person),
    )

    var currentIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            navigationItems.forEachIndexed { index, navigationItem ->
                BottomNavigationItem(
                    selected = currentIndex == index,
                    onClick = {
                        currentIndex = index
                    },
                    //直接考试结果页面，进入查看页面，返回直接回到列表？
                    icon = {
                        Icon(
                            imageVector = navigationItem.icon,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = navigationItem.title)
                    },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF999999)
                )
            }
        }
    }) {
        if (currentIndex == 0) {
            HomeFrame()
        }
    }
}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame()
}