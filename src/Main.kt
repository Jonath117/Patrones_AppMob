//patron proxy

interface AccesoBDD{
    fun mostrarDatos(): String
    fun eliminarDatos(usuario: String): String
}

class BasedeDatos : AccesoBDD{
    override fun mostrarDatos(): String {
        return "Mostrando datos.."
    }
    override fun eliminarDatos(usuario: String): String {
        return "Datos eliminados del usuario: $usuario"
    }
}

class ProxyProteccion(private val BDReal: AccesoBDD) : AccesoBDD{
    private val usuarioAdmin = "ADMIN"

    override fun mostrarDatos(): String {
        println("Proxy: Peticion mostrar datos delegada")
        return BDReal.mostrarDatos()
    }
    override fun eliminarDatos(usuario: String): String {
        if(usuario.uppercase() == usuarioAdmin){
            println("Proxy: Permiso concedido a $usuario. Eliminando")
            return BDReal.eliminarDatos(usuario)
        }
        else {
            println("Proxy: Permiso denegado para $usuario en la eliminacion")
            return "Error: Usuario $usuario no tiene permisos de administrador."
        }
    }
}

fun main() {
    val BD = BasedeDatos()
    val proxy = ProxyProteccion(BD)

    val usuarioNormal = "invitado"
    val usuarioAdmin = "admin"

    println("Caso invitado")
    println(proxy.mostrarDatos())
    println(proxy.eliminarDatos(usuarioNormal))

    println("Caso admin")
    println(proxy.mostrarDatos())
    println(proxy.eliminarDatos(usuarioAdmin))

}