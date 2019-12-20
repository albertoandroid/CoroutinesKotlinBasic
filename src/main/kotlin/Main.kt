import kotlinx.coroutines.*

fun main(args: Array<String>) {
    //blokingExample()
    //exampleSuspend()
    //dispatcher()
    //launch()
    //exampleJob()
    //Thread.sleep(5000)
    //asyncAwait()
    //asyncAwaitDeferred()
    //println(measureTimeMillis { asyncAwait() }.toString())
    //println(measureTimeMillis { asyncAwaitDeferred() }.toString())
    //println(measureTimeMillis { withContextIO() }).toString()
    cancelCoroutine()
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

fun exampleSuspend(){
    println("Tarea1 " + Thread.currentThread().name)
    runBlocking {
        delayCoroutine("Tarea2 :")
    }
    println("Tarea3 " + Thread.currentThread().name)
}

fun exampleSuspend2() = runBlocking {
    println("Tarea1 " + Thread.currentThread().name)
    delayCoroutine("Tarea2 :")
    println("Tarea3 " + Thread.currentThread().name)
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

fun launch(function: () -> Unit) {
    println("Tarea1 " + Thread.currentThread().name)
    GlobalScope.launch {
        delayCoroutine("Tarea2 :")
    }
    println("Tarea3 " + Thread.currentThread().name)
}

fun exampleJob(){
    println("Tarea1 " + Thread.currentThread().name)
    val job: Job = GlobalScope.launch {
        delayCoroutine("Tarea2 :")
    }
    println("Tarea3 " + Thread.currentThread().name)
    job.cancel()
}

suspend fun calculateHard(): Int{
    delay(2000)
    return 15
}

fun asyncAwait() = runBlocking {
    //println(System.currentTimeMillis().toString())
    val numero1: Int = async { calculateHard() }.await()
    //println(System.currentTimeMillis().toString())
    val numero2: Int = async { calculateHard() }.await()
    //println(System.currentTimeMillis().toString())
    val resultado = numero1 + numero2
    println(resultado.toString())
}

fun asyncAwaitDeferred() = runBlocking {
    //println(System.currentTimeMillis().toString())
    val numero1: Deferred<Int> = async { calculateHard() }
    //println(System.currentTimeMillis().toString())
    val numero2: Deferred<Int> = async { calculateHard() }
    //println(System.currentTimeMillis().toString())
    val resultado: Int = numero1.await() + numero2.await()
    println(resultado.toString())
}

fun withContextIO() = runBlocking {
    val numero1 = withContext(Dispatchers.IO){calculateHard()}
    val numero2 = withContext(Dispatchers.IO){calculateHard()}
    val resultado: Int = numero1 + numero2
    println(resultado.toString())
}

fun cancelCoroutine(){
    runBlocking {
        val job = launch {
            repeat(1000){
                i->
                println("job: $i")
                kotlinx.coroutines.delay(500L)
            }
        }
        delay(1400)
        job.cancel()
        println("main: cansado de esperar")
    }
}






