package com.paulo.designpatterns.domain.creational

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulo.designpatterns.R
import com.paulo.designpatterns.domain.utils.Consts


private fun GoToExampleWeb(context: Context, url: String) {
    val urlIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
    context.startActivity(urlIntent)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FactoryMethodScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current

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
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
                //.horizontalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Text(
                modifier =
                Modifier.padding(20.dp),
                text = getDescription(),
                fontSize = 14.sp
            )

            TextButton(onClick = {
                GoToExampleWeb(
                    context,
                    "${Consts.PATH_CREATIONAL.title}factoryMethodExample/example/example1.kt"
                )
                //
            }) {
                Text(text = "Ver exemplo 1")
            }
            TextButton(onClick = {
                GoToExampleWeb(
                    context,
                    "${Consts.PATH_CREATIONAL.title}factoryMethodExample/example/example2.kt"
                )
                //
            }) {
                Text(text = "Ver exemplo 2")
            }
            TextButton(onClick = {
                GoToExampleWeb(
                    context,
                    "${Consts.PATH_CREATIONAL.title}factoryMethodExample/example/example3.kt"
                )
                //
            }) {
                Text(text = "Ver exemplo 3")
            }

            val listAplicability = listOf(
                "Use o Factory Method quando não souber de antemão os tipos e dependências exatas dos objetos com os quais seu código deve funcionar.",
                "Use o Factory Method quando desejar fornecer aos usuários da sua biblioteca ou framework uma maneira de estender seus componentes internos.",
                "Use o Factory Method quando deseja economizar recursos do sistema reutilizando objetos existentes em vez de recriá-los sempre.",
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = "Aplicabilidade",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            listAplicability.forEach {
                Text(fontSize = 16.sp, text = it)
                Divider(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Black))
            }


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
            val realSolution = listOf(
                R.drawable.factory_method_1,
                R.drawable.factory_method_4,
                R.drawable.factory_method_3,
            )
            Text(
                text = "Diagrama solução real",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            realSolution.forEach {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null
                )
            }

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

