package com.paulo.designpatterns.presenter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.paulo.designpatterns.domain.RootActivity
import com.paulo.designpatterns.domain.utils.Page
import com.paulo.designpatterns.presenter.ui.theme.DesignPattersTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesignPattersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val intent = Intent(this, RootActivity::class.java)
                    var selectedItem by remember { mutableStateOf(0) }
                    val pages = Page.values()
                    val icons =
                        listOf(
                            Icons.Filled.Home,
                            Icons.Filled.Search,
                            Icons.Filled.Settings,
                            Icons.Filled.List,
                            Icons.Filled.Favorite,
                            Icons.Filled.Person,
                        )
                    Row {
                        NavigationRail {
                            pages.forEachIndexed { index, item ->
                                when (item) {
                                    Page.CREATIONAL -> {
                                        NavigationRailItem(
                                            label = { Text(item.title) },
                                            icon = { Icon(icons[index], contentDescription = "") },
                                            selected = selectedItem == index,
                                            onClick = { selectedItem = index },
                                            alwaysShowLabel = false
                                        )
                                    }
                                    Page.BEHAVIORAL -> {
                                        NavigationRailItem(
                                            label = { Text(item.title) },
                                            icon = { Icon(icons[index], contentDescription = "") },
                                            selected = selectedItem == index,
                                            onClick = { selectedItem = index },
                                            alwaysShowLabel = false
                                        )
                                    }
                                    Page.STRUCTURAL -> {
                                        NavigationRailItem(
                                            label = { Text(item.title) },
                                            icon = { Icon(icons[index], contentDescription = "") },
                                            selected = selectedItem == index,
                                            onClick = { selectedItem = index },
                                            alwaysShowLabel = false
                                        )
                                    }
                                    Page.SOLID -> {
                                        NavigationRailItem(
                                            label = { Text(item.title) },
                                            icon = { Icon(icons[index], contentDescription = "") },
                                            selected = selectedItem == index,
                                            onClick = { selectedItem = index },
                                            alwaysShowLabel = false
                                        )
                                    }
                                    Page.COLABORATOR -> {
                                        NavigationRailItem(
                                            label = { Text(item.title) },
                                            icon = { Icon(icons[index], contentDescription = "") },
                                            selected = selectedItem == index,
                                            onClick = { selectedItem = index },
                                            alwaysShowLabel = false
                                        )
                                    }
                                    Page.FONT -> {
                                        NavigationRailItem(
                                            label = { Text(item.title) },
                                            icon = { Icon(icons[index], contentDescription = "") },
                                            selected = selectedItem == index,
                                            onClick = { selectedItem = index },
                                            alwaysShowLabel = false
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start
                        ) {


                            LazyColumn {
                                stickyHeader {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.Black),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            pages[selectedItem].content,
                                            Modifier.padding(10.dp),
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                                items(pages[selectedItem].patterns) { name ->
                                    ItemList(
                                        name,
                                        this@MainActivity
                                    ) {
                                        intent.action = it
                                        startActivity(intent)
                                    }
                                }
                            }

                        }


                    }
                }
            }
        }
    }
}

@Composable
private fun ItemList(
    name: String,
    context: Context,
    onnClick: (String) -> Unit,
) {
    val hasFont = name.contains("*")
    val (title, url) = if (hasFont) name.split("*") else listOf("","")

    Column(modifier = Modifier
        .clickable {
            if (hasFont) {
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                )
                context.startActivity(urlIntent)
            } else {
                onnClick(name)
            }

        }
        .padding(top = 40.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                if (hasFont) title else name,
                Modifier.padding(start = 30.dp),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 20.sp
            )
            Icon(
                Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Black
            )
        }

        Divider(
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )
    }
}

