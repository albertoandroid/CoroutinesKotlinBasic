fun main(args: Array<String>) {
    blokingExample()
}

fun longTaskWithMessage(message: String){
    Thread.sleep(4000)
    println(message + Thread.currentThread().name)
}

fun blokingExample(){
    println("Tarea1 " + Thread.currentThread().name)
    longTaskWithMessage("Tarea2 ")
    println("Tarea3 " + Thread.currentThread().name)
}



