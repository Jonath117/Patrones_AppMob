//patron strategy

interface StrategyOrdenacion{
    fun ordenar(lista: List<Int>): List<Int>
}

class OrdenacionAscendente : StrategyOrdenacion {
    override fun ordenar(lista: List<Int>): List<Int> {
        println("Ordenacion Ascendente")
        return lista.sorted()
    }
}

class OrdenacionDescendente : StrategyOrdenacion {
    override fun ordenar(lista: List<Int>): List<Int> {
        println("Ordenacion Descendente")
        return lista.sortedDescending()
    }
}

class ProcesadorLista(private var strategy: StrategyOrdenacion) {
    fun setStrategy(newStrategy: StrategyOrdenacion) {
        println("Cambiando estrategia de ordenacion")
        this.strategy = newStrategy
    }

    fun ejecutarOrdenacion(datos: List<Int>): List<Int> {
        return strategy.ordenar(datos)
    }
}

fun main() {
    val numeros = listOf(20, 4, 2, 5, 3, 1, 10, 9, 8, 6)
    println("lista original: $numeros")

    val procesador = ProcesadorLista(OrdenacionAscendente())

    val resul1 = procesador.ejecutarOrdenacion(numeros)
    println("Resultado ascendente: $resul1")

    procesador.setStrategy(OrdenacionDescendente())

    val result2 = procesador.ejecutarOrdenacion(numeros)
    println("Resultado descendente: $result2")

}