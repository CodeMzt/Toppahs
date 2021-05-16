package indi.mzt.toppahs.plugin

import indi.mzt.toppahs.service.ScramblesService

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun main(){
    var test1=ScramblesService.SIX
    println(test1.scramble)
    print(test1.drawScramble())
}
