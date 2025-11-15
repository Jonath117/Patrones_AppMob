interface Inversor{
    fun actualizar(simboloAccion: String, nuevoPrecio: Double)
}

class BolsaDeValores(private val simboloAccion: String){
    private val inversores = mutableListOf<Inversor>()
    private var precio: Double = 100.0

    fun subscribe(inversor: Inversor){
        inversores.add(inversor)
        println("-> ${inversor.javaClass.simpleName} se ha suscrito a ${simboloAccion}")
    }

    fun unsubscribe(inversor: Inversor){
        inversores.remove(inversor)
        println("-> ${inversor.javaClass.simpleName} se ha desuscrito de ${simboloAccion}")
    }

    fun establecerPrecio(nuevoPrecio: Double){
        println("\n*** Precio de $simboloAccion ha cambiado a $$nuevoPrecio ***")
        this.precio = nuevoPrecio
        notificarObservadores()
    }

    private fun notificarObservadores(){
        inversores.forEach{
            inversor -> inversor.actualizar(simboloAccion, precio)
        }
    }
}

class InversorA: Inversor{
    override fun actualizar(simboloAccion: String, nuevoPrecio: Double) {
        println("[InversorA]: Recibido el nuevo precio de $simboloAccion: $$nuevoPrecio. ¡Estoy considerando vender!")
    }
}

class InversorB: Inversor{
    override fun actualizar(simboloAccion: String, nuevoPrecio: Double) {
        if (nuevoPrecio < 110.0) {
            println("[InversorB]: ¡Alerta de compra! $simboloAccion está a $$nuevoPrecio, es un buen momento.")
        } else {
            println("[InversorB]: $simboloAccion a $$nuevoPrecio. No hago nada.")
        }
    }
}

fun main() {
    val tesla = BolsaDeValores("TESLA")

    val ana = InversorA()
    val carlos = InversorB()
    val juan = InversorA()

    tesla.subscribe(ana)
    tesla.subscribe(carlos)

    tesla.establecerPrecio(105.50)

    tesla.subscribe(juan)

    tesla.establecerPrecio(120.50)

    tesla.unsubscribe(carlos)

    tesla.establecerPrecio(109.90)
}