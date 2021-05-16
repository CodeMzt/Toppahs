package indi.mzt.toppahs.main

import net.mamoe.mirai.utils.ExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import java.io.File

object useKt {
    public fun getExternalResource(file: File):ExternalResource{ return file.toExternalResource(); }
}