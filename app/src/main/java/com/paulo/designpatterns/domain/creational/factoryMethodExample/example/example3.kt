package com.paulo.designpatterns.domain.creational.factoryMethodExample.example


//EXECUCAO DO CODIGO
fun main() {

    val banco = BancoBB()
    banco.geraBoleto(10, 100)
    banco.geraBoleto(20, 100)
    banco.geraBoleto(30, 100)

    val bancoC = BancoCaixa()
    bancoC.geraBoleto(10, 100)
    bancoC.geraBoleto(20, 100)
}


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
            """
               _____________________________________________
                Boleto  gerado com sucesso no valor de: $valor
                Juros = ${boleto.calcularJuros()}
                Desconto = ${boleto.calcularDesconto()}
                Multa = ${boleto.calcularMulta()}
                _____________________________________________
                
            """.trimIndent()
        )
    }
}

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

/********************************************************
 * BOLETOS DO BANCO CAIXA
 */


class BoletoCaixa10DiasEx3(valor: Int) :
    BoletoEx3(valor = valor, juros = 0.02f, desconto = 0.1f, multa = 0.05f)

class BoletoCaixa20DiasEx3(valor: Int) :
    BoletoEx3(valor = valor, juros = 0.05f, desconto = 0.5f, multa = 0.10f)

class BoletoCaixa30DiasEx3(valor: Int) :
    BoletoEx3(valor = valor, juros = 0.10f, desconto = 0.10f, multa = 0.20f)

/********************************************************
 * BANCO DO BRASIL
 */

class BoletoBB10DiasEx3(valor: Int) :
    BoletoEx3(valor = valor, juros = 0.08f, desconto = 0.1f, multa = 0.18f)

class BoletoBB20DiasEx3(valor: Int) :
    BoletoEx3(valor = valor, juros = 0.05f, desconto = 0.2f, multa = 0.11f)



