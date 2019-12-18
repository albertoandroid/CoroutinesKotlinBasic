import kotlinx.coroutines.delay

fun main(args: Array<String>) {
    blokingExample()
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



