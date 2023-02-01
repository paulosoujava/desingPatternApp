package com.paulo.designpatterns.domain


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.paulo.designpatterns.domain.creational.FactoryMethodScreen
import com.paulo.designpatterns.domain.utils.Consts
import com.paulo.designpatterns.presenter.ui.theme.DesignPattersTheme

class RootActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesignPattersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val it = intent
                    val pageTitle = it.action
                    if (pageTitle == null)
                        finish()
                    else {
                        when (pageTitle) {
                            Consts.FACTORY_METHOD.title-> FactoryMethodScreen {
                                finish()
                            }

                            else->finish()
                        }
                        }
                    }
                }
            }
        }
    }