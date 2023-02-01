package com.paulo.designpatterns.domain.creational

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
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
import androidx.compose.ui.draw.clip
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
                //.horizontalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Text(
                modifier =
                Modifier.padding(20.dp),
                text = getDescription(),
                fontSize = 14.sp
            )
            Column(
                modifier = Modifier
                    .background(Color.DarkGray.copy(alpha = .2f))
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
            ) {

                Text(
                    modifier =
                    Modifier.padding(20.dp),
                    text = """ 
                        
               abstract class BoletoEx1(
                    var valor: Int,
                    var juros: Float ,
                    var desconto: Float ,
                    var multa: Float
                ) {
              
                    fun calcularJuros(): Float {
                        return valor * juros
                    }
                
                    fun calcularDesconto(): Float {
                        return valor * desconto
                    }
                
                    fun calcularMulta(): Float {
                        return valor * multa
                    }
                }
                
                class ClienteEx1 {

                    lateinit var boleto: BoletoEx1

                    fun geraBoleto(vencimento: Int, valor: Int): String {
                        boleto = when (vencimento) {
                            10 -> BoletoCaixa10DiasEx1(valor)
                            20 -> BoletoCaixa20DiasEx1(valor)
                            30 -> BoletoCaixa30DiasEx1(valor)
                            else -> throw Exception("Vencimento indisponivel")
                        }

                        return try {
                                
                                Boleto gerado com sucesso no valor de: valor
                                Juros = boleto.calcularJuros()
                                Desconto = boleto.calcularDesconto()
                                Multa = boleto.calcularMulta()
                            
                                
                            
                        } catch (e: Exception) {
                            e.message ?: ""
                        }

                    }
                }
                
                class BoletoCaixa10DiasEx1(valor: Int) : BoletoEx1(
                    valor = valor,
                    juros = 0.02f,
                    desconto = 0.1f,
                    multa = 0.05f
                )
                
                class BoletoCaixa20DiasEx1(valor: Int) : BoletoEx1(
                    valor = valor,
                    juros = 0.05f,
                    desconto = 0.5f,
                    multa = 0.10f
                )
                
                class BoletoCaixa30DiasEx1(valor: Int) : BoletoEx1(
                    valor = valor,
                    juros = 0.10f,
                    desconto = 0.10f,
                    multa = 0.20f
                 )
                 
                 A execução seria:
                 val banco = ClienteEx1()
                println(banco.geraBoleto(10, 100))
                println(banco.geraBoleto(20, 100))
                println(banco.geraBoleto(30, 100))
                
                O ClienteEx1 conhece as classes concretas BoletoCaixa10DiasEx1, BoletoCaixa30DiasEx1, BoletoCaixa30DiasEx1
                tendo assim um FORTE ACOPLAMENTO.  Agora imagina voce ter varios clientes como:
                ClienteEx1, ClienteEx2, ... você teria que repetir o código em 
                cada um dos clientes:
                
                boleto = when (vencimento) {
                            10 -> BoletoCaixa10DiasEx1(valor)
                            20 -> BoletoCaixa20DiasEx1(valor)
                            30 -> BoletoCaixa30DiasEx1(valor)
                            else -> throw Exception("Vencimento indisponivel")
                        }

                
                Refatorando
                
              Vamos tirar a responsabilidade do client (ClienteEx1), criaremos uma classe onde a unica
              responsabilide vai ser criar objetos, esta classe tera um unico método para tal.
              Assim TODOS os clientes passam a chamar esta classe para criar objetos. Resolvemos a 
              dependencia direta do cliente com as classes concretas, MAS passamos o acoplamento e o
              conhecimento para a nova classe criado, este é o lado ruim, poré, agora vc pode ter
              quantos clientes quiser chamando a nova classe criada, isto não é um design pattern, mas 
              para resolver problemas simples ele serve, chamamos de Simple Factory a principio vc pode estar
              pensando, mas só não jogamos o problema para a nova classe criada? Sim e Não.
              Sim, o problema foi jogado para a nova classe.
              Não, pois se você precisa ter N tipo de Consumidores da nova classe a chamada sera sempre
              para uma unica fonte de dado.
              
               PseudoCódigo:
               
               Interface X{}
              
               Class A : X{}
               Class B : X{}
               Class C : X{}
              
              // devolve a instancia de um novo objeto A,B ou C
              class SimpleFactory{
              //cria objeto da classe A,B,C
              }
              
              
              //AQUI ESTA O GANHO DA SIMPLE FACTORY, os clientes abaixo depende do SimpleFactory
              //sem instancia de novos objetos
              Class Client1{
                //retorna a SimpleFactory
              }
               Class Client2{
                //retorna a SimpleFactory
              }
               Class Client3{
                //retorna a SimpleFactory
              }
               
               Código da Refatoração:
               
               abstract class BoletoEx2(
                   var valor: Int,
                   var juros: Float,
                   var desconto: Float,
                   var multa: Float
               ) {

                   fun calcularJuros(): Float {
                       return valor * juros
                   }

                   fun calcularDesconto(): Float {
                       return valor * desconto
                   }

                   fun calcularMulta(): Float {
                       return valor * multa
                   }
               }
               //AQUI CRIAMOS A SIMPLE FACTORY QUE TEM A UNICA RESPONSABILIDADE 
               /DE DEVOLVER UM OBEJTO BOLETO
               
               class BoletoSimpleFactory {
                   fun criarBoleto(vencimento: Int, valor: Int): BoletoEx2 {
                       return when (vencimento) {
                           10 -> BoletoCaixa10DiasEx2(valor)
                           20 -> BoletoCaixa20DiasEx2(valor)
                           30 -> BoletoCaixa30DiasEx2(valor)
                           else -> throw Exception("Vencimento indisponivel")
                       }
                   }
               }
               //COM A CLASSE ACIMA CRIADA, PODEMOS TER QUANTOS CLIENTES QUISERMOS
               // ex: Cliente1Ex2, Cliente2Ex2, Cliente3Ex2, com suas respectivas regras
               //SPOILER:: Vamos melhorar isto mais abaixo
               
                class Cliente1Ex2(val boletoSimpleFactory: BoletoSimpleFactory) {
                
                    fun geraBoleto(vencimento: Int, valor: Int) {
                        val boleto = boletoSimpleFactory.criarBoleto(vencimento, valor)
                        println(
                            ""
                                
                                Boleto gerado com sucesso no valor de: valor
                                Juros = boleto.calcularJuros()
                                Desconto = boleto.calcularDesconto()
                                Multa = boleto.calcularMulta()
                              
                                
                            "".trimIndent()
                        )
                    }
                }
                
                class BoletoCaixa10DiasEx2(valor: Int) : BoletoEx2(
                    valor = valor,
                    juros = 0.02f,
                    desconto = 0.1f,
                    multa = 0.05f
                )
                
                class BoletoCaixa20DiasEx2(valor: Int) : BoletoEx2(
                    valor = valor,
                    juros = 0.05f,
                    desconto = 0.5f,
                    multa = 0.10f
                )
                
                class BoletoCaixa30DiasEx2(valor: Int) : BoletoEx2(
                    valor = valor,
                    juros = 0.10f,
                    desconto = 0.10f,
                    multa = 0.20f
                )
               
               Bom isso não faz muito sentido para quando vc tem somente um CLIENTE, no caso acima
               só passamos de um lado para o outro, mostrando a possibilidade de personalizar a
               subclasse.
               
               Va os implementar como deve ser de fato:
               
               abstract class BoletoEx3(
                   var valor: Int,
                   var juros: Float,
                   var desconto: Float,
                   var multa: Float
               ) {

                   fun calcularJuros(): Float {
                       return valor * juros
                   }

                   fun calcularDesconto(): Float {
                       return valor * desconto
                   }

                   fun calcularMulta(): Float {
                       return valor * multa
                   }
               }
                
                abstract class Banco {

                    abstract fun criarBoleto(vencimento: Int, valor: Int):BoletoEx3

                    fun geraBoleto(vencimento: Int, valor: Int) {
                        val boleto = criarBoleto(vencimento, valor)
                        println(
                            ""
                               
                                Boleto  gerado com sucesso no valor de: valor
                                Juros = boleto.calcularJuros()
                                Desconto = boleto.calcularDesconto()
                                Multa = boleto.calcularMulta()
                               
                                
                            "".trimIndent()
                        )
                    }
                }
                //CLIENTES RESPONSAVEIS POR SEUS OBJTOS
                
                class BancoCaixa(): Banco() {

                    override fun criarBoleto(vencimento: Int , valor: Int): BoletoEx3 {
                        return when (vencimento) {
                            10 -> BoletoCaixa10DiasEx3(valor)
                            20 -> BoletoCaixa20DiasEx3(valor)
                            30 -> BoletoCaixa30DiasEx3(valor)
                            else -> throw Exception("Caixa: Vencimento indisponivel")
                        }
                    }
                }
                
                class BancoBB(): Banco() {
                    override fun criarBoleto(vencimento: Int, valor: Int): BoletoEx3 {
                        return when (vencimento) {
                            10 -> BoletoBB10DiasEx3(valor)
                            20 -> BoletoBB20DiasEx3(valor)
                            else -> throw Exception("BB: Vencimento indisponivel")
                        }
                    }
                }
                /*
                 * BOLETOS DO BANCO CAIXA
                 */


                class BoletoCaixa10DiasEx3(valor: Int) :
                    BoletoEx3(valor = valor, juros = 0.02f, desconto = 0.1f, multa = 0.05f)

                class BoletoCaixa20DiasEx3(valor: Int) :
                    BoletoEx3(valor = valor, juros = 0.05f, desconto = 0.5f, multa = 0.10f)

                class BoletoCaixa30DiasEx3(valor: Int) :
                    BoletoEx3(valor = valor, juros = 0.10f, desconto = 0.10f, multa = 0.20f)

                /*
                 * BANCO DO BRASIL
                 */

                class BoletoBB10DiasEx3(valor: Int) :
                    BoletoEx3(valor = valor, juros = 0.08f, desconto = 0.1f, multa = 0.18f)

                class BoletoBB20DiasEx3(valor: Int) :
                    BoletoEx3(valor = valor, juros = 0.05f, desconto = 0.2f, multa = 0.11f)


                Algumas considerações:
                O Factory Method também é utilizado para criar objetos complexos, nos examplos acima,
                criamos um unico objeto, do qual não valeria o esforço, mentira sempre vale o esforço
                pois o condigo SEMPRE TENDE A MUDAR
         
               
            """.trimIndent()
                )
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

