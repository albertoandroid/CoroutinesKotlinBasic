import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    dispatcher()
    //blokingExample()
}

fun longTaskWithMessage(message: String){
    Thread.sleep(4000)
    println(message + Thread.currentThread().name)
}

fun blokingExample(){
    println("Tarea1 " + Thread.currentThread().name)
    //longTaskWithMessage("Tarea2 ")
    //delayCoroutine("tarea2")
    println("Tarea3 " + Thread.currentThread().name)
}

suspend fun delayCoroutine(message: String){
    delay(timeMillis = 4000)
    println(message + Thread.currentThread().name)
}

fun dispatcher(){
    runBlocking {
        println("Hilo en el que se ejecuta 1: ${Thread.currentThread().name}")
    }
    runBlocking(Dispatchers.Unconfined) {
        println("Hilo en el que se ejecuta 2: ${Thread.currentThread().name}")
    }
    runBlocking(Dispatchers.Default) {
        println("Hilo en el que se ejecuta 3: ${Thread.currentThread().name}")
    }
    runBlocking(Dispatchers.IO) {
        println("Hilo en el que se ejecuta 4: ${Thread.currentThread().name}")
    }
    runBlocking(newSingleThreadContext("MiHilo")) {
        println("Hilo en el que se ejecuta 5: ${Thread.currentThread().name}")
    }
    //runBlocking(Dispatchers.Main) {
    //    println("Hilo en el que se ejecuta 6: ${Thread.currentThread().name}")
    //}
}



