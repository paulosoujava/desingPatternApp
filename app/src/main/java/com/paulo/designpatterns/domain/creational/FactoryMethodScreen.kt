package com.paulo.designpatterns.domain.creational

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulo.designpatterns.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FactoryMethodScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                elevation = 12.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Factory Method",
                        color = Color.White,
                    )
                }

            }

        }) {
        Column(
            modifier =
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = getDescription(),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = "Diagrama UML",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.factory_method),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = "Diagrama solução real",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.factory_method_1),
                contentDescription = null
            )
            Image(
                painter = painterResource(id = R.drawable.factory_method_4),
                contentDescription = null
            )
            Image(
                painter = painterResource(id = R.drawable.factory_method_3),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = "Pseudocódigo",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.pseudo_codigo_factory_method),
                contentDescription = null
            )
        }

    }
}

@Composable
private fun getDescription() = buildAnnotatedString {
    append("O Factory Method fornece uma")
    withStyle(style = SpanStyle(Color.Blue, fontWeight = FontWeight.Bold)) {
        append(" Interface")
    }
    append(" para criar ")
    withStyle(style = SpanStyle(Color.Blue, fontWeight = FontWeight.Bold)) {
        append(" objetos ")
    }
    append("em uma ")
    withStyle(style = SpanStyle(Color.Blue, fontWeight = FontWeight.Bold)) {
        append(" super classe ")
    }
    append("e permite que  as ")
    withStyle(style = SpanStyle(Color.Blue, fontWeight = FontWeight.Bold)) {
        append(" subclasses ")
    }
    append(" possam  ")
    withStyle(style = SpanStyle(Color.Blue, fontWeight = FontWeight.Bold)) {
        append(" alterar ")
    }
    append("o tipo de objetos que serão criados ")
}

