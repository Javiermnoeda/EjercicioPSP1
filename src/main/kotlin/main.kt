import kotlinx.coroutines.*

fun main(){


  runBlocking {
      GlobalScope.launch {
          for (i in 1..10){
              println(i)
              delay(1000)
          }
      }

      GlobalScope.launch {
          repeat(2){
              delay(5000)
              println("Sigo vivo")
          }

      }
      println("He terminado")
  }

    Thread.sleep(15000) // El hilo donde se esta ejecutando algo se para. se usa para que el hilo que se ejecuta fuera de la corrutina no lo haga

}
