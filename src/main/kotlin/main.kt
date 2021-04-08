import kotlinx.coroutines.*
import kotlin.random.Random

fun main() {
    println("Empezamos aquí")

    /*for (i in 0..5) {
        GlobalScope.launch {
            funcionEnCorrutina(i)
        }
    }*/
    println("Entramos")
    runBlocking {
        this.launch {             
            funcionEnCorrutina(99)
        }
    }
    println("Salimos")

    println("Entramos")
    runBlocking {
        GlobalScope.launch {
            funcionEnCorrutina(100)
        }
    }
    println("Salimos")
    println("Esperamos un rato")
    Thread.sleep(200000)
    println("Terminamos aquí")


    var b = funcion()
    println("Hola")
    GlobalScope.launch {


    if (b.await()) {

    }
    }
}
private fun funcion(): Deferred<Boolean> {
    return GlobalScope.async {
        delay(10000000)
        true }
}

private suspend fun funcionEnCorrutina(i : Int){
    println("Soy una corrutina $i y estoy ejecutandome en 2º plano")
    delay(Random.nextLong(1000, 3000))
    println("Soy una corrutina $i y he terminado mi trabajo")
}

