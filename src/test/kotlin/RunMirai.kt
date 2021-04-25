package org.example.mirai.plugin

import com.codemzt.toppahs.main.PluginMain
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader

suspend fun main() {
    //println("Toppahs")
    MiraiConsoleTerminalLoader.startAsDaemon()

    //如果是kotlin
    PluginMain.load()
    PluginMain.enable()
    //如果是java
//    JavaPluginMain.INSTANCE.load()
//    JavaPluginMain.INSTANCE.enable()

    //下面填机器人信息
    val bot = MiraiConsole.addBot(123456789, "password") {
        fileBasedDeviceInfo()
    }.alsoLogin()

    MiraiConsole.job.join()
}
