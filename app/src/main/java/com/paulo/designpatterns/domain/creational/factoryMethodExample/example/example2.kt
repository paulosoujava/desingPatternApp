package com.paulo.designpatterns.domain.creational.factoryMethodExample.example


//EXECUCAO DO CODIGO
fun main() {
    val boletoSimpleFactory = BoletoSimpleFactory()
    val banco = Cliente1Ex2(boletoSimpleFactory)
    banco.geraBoleto(10, 100)
    banco.geraBoleto(20, 100)
    banco.geraBoleto(30, 100)
}


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

class Cliente1Ex2(val boletoSimpleFactory: BoletoSimpleFactory) {

    fun geraBoleto(vencimento: Int, valor: Int) {
        val boleto = boletoSimpleFactory.criarBoleto(vencimento, valor)
        println(
            """
               _____________________________________________
                
                Boleto gerado com sucesso no valor de: $valor
                Juros = ${boleto.calcularJuros()}
                Desconto = ${boleto.calcularDesconto()}
                Multa = ${boleto.calcularMulta()}
                _____________________________________________
                
            """.trimIndent()
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

