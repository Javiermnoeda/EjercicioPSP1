import kotlinx.coroutines.*
import kotlin.random.Random

var mostrarLogsDelBlocking = false

fun main() {
  /*
    println("Empezamos aquí")

    println("Entramos")
    GlobalScope.launch {
        // Este es el ejemplo más básico de una corrutina
        mostrarLog(1, this)
        funcionEnCorrutina(1)
    }
    println("Salimos")

    // Ponemos este sleeep para que la corrutina anterior no se entremezcle con las siguiente corrutinas
    Thread.sleep(3000)

    println("Entramos")
    runBlocking {
        mostrarLog(2, this)

        // this es el scope en el que se va a ejecutar las 2 corrutinas y el runblocking va a forzar a que terminen antes de poder salir
        this.launch {
            mostrarLog(3, this)
            funcionEnCorrutina(98)
        }
        this.launch {
            mostrarLog(4, this)

            funcionEnCorrutina(99)
        }
    }
    println("Salimos")

    println("Entramos")
    runBlocking {
        mostrarLog(5, this)
        // this es el scope en el que no se va a ejecutar  nada, por lo que las 2 corrutinas y el runblocking no van a tener un efecto real
        GlobalScope.launch {
            mostrarLog(6, this)
            funcionEnCorrutina(100)
        }
        GlobalScope.launch {
            mostrarLog(7, this)
            funcionEnCorrutina(101)
        }
    }
    println("Salimos")

    // Ponemos este sleeep para que las corrutinas anteriores no se entremezclen con las siguiente corrutinas
    Thread.sleep(3000)
*/
    GlobalScope.launch {
        funcionAsyncNormal()
    }
    Thread.sleep(3000)

    GlobalScope.launch {
        funcionAsyncLazy()
    }
    Thread.sleep(5000)
}


private suspend fun funcionAsyncNormal(){
    println("Estoy en la función")
    val resultDeferred = GlobalScope.async {
        val resultInCoroutine = Random.nextInt()
        val tiempoDeDormir = 2000L
        println("voy a devolver un $resultInCoroutine en $tiempoDeDormir")
        delay(tiempoDeDormir)
        println("He terminado el async")
        resultInCoroutine
    }
    delay(1000)
    println("Voy a hacer el await")
    // El await esperará a que el GlobalSncope.async {} termine de ejecutarse. Si el aync ya hubiera terminado,
    // entonces el await no esperaría, xq ya tiene el resultado
    println(resultDeferred.await())
    println("Voy a salir del await")
}

private suspend fun funcionAsyncLazy(){
    println("Estoy en la función")
    val resultDeferred = GlobalScope.async(start = CoroutineStart.LAZY) {
        val resultInCoroutine = Random.nextInt()
        val tiempoDeDormir = 2000L
        println("voy a devolver un $resultInCoroutine en $tiempoDeDormir")
        delay(tiempoDeDormir)
        println("He terminado el async")
        resultInCoroutine
    }
    delay(1000)
    // El await esperará a que el GlobalSncope.async {} termine de ejecutarse. Pero esa ejecución no comenzará hasta
    // hasta que alguien realice el await. De este modo funcionAsyncLazy será un segundo más lento que el funcionAsyncNormal
    println("Voy a hacer el await")
    println(resultDeferred.await())
    println("Voy a salir del await")
}


private fun mostrarLog(identificador : Int, scope : CoroutineScope){
    if (mostrarLogsDelBlocking) println("$identificador - ${scope.coroutineContext}")

}

private suspend fun funcionEnCorrutina(i : Int){
    println("Soy una corrutina $i y estoy ejecutandome en 2º plano")
    delay(Random.nextLong(1000, 3000))
    println("Soy una corrutina $i y he terminado mi trabajo")
}

