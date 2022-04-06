package pers.keafmd.accumulate.test

import java.util.EnumSet.range

class aa {

}
fun main(args: Array<String>) {
    val range = 'a'..'z'
    val joinTo = range.joinTo(StringBuilder(), ",", "", "", -1, "")
    joinTo.forEach {
        print(it+" ")
    }

//    println("Hello, world!")
}