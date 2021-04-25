package com.codemzt.toppahs.plugin.cube.scrambles

import com.codemzt.toppahs.config.ServiceConfig
import com.codemzt.toppahs.plugin.cube.Cube
import java.io.File
import java.net.URL
import java.net.URLEncoder
import java.util.*

object Scrambles  {
    fun getScramble(cube: Cube):Cube{
        cube.scramble= URL("${ServiceConfig.cube}/scramble/${cube.type}").readText()
        cube.scramble.replace("\r","")
        if (cube.scramble.endsWith("\n")) {
            cube.scramble = cube.scramble.substring(0, cube.scramble.length-1)
        }
        if ("minx" == cube.type) {
            cube.scramble = cube.scramble.replace("U' ", "U'\n")
            cube.scramble = cube.scramble.replace("U ", "U\n")
        }
        var now=Date().time
        cube.pic = "${ServiceConfig.cube}/view/${cube.type}?format=png&scramble=" + URLEncoder.encode(cube.scramble, "utf-8")
        /*cube.picF= File("$now-scramble.png")
        var picUrl=URL(cube.pic).openConnection()
        cube.picF.writeBytes(picUrl.getInputStream().readBytes())*/
        return cube
    }
}