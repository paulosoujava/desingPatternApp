package com.paulo.designpatterns.domain.creational.factoryMethodExample.example


//EXECUCAO DO CODIGO
fun main() {
    val banco = ClienteEx1()
    println(banco.geraBoleto(10, 100))
    println(banco.geraBoleto(20, 100))
    println(banco.geraBoleto(30, 100))
}


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

/*
    Cliente com responsabilidade de criar  objeto
    e para isso ele deve er ciencia das classes concretas
    imageina se voce tive-se 10 clientes teria que replicar
    aclausula when para todos os 10 clientes
 */
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
            """
                _____________________________________________
                
                Boleto gerado com sucesso no valor de: $valor
                Juros = ${boleto.calcularJuros()}
                Desconto = ${boleto.calcularDesconto()}
                Multa = ${boleto.calcularMulta()}
                _____________________________________________
                
            """.trimIndent()
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

