plugins {
    val kotlinVersion = "1.4.30"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("net.mamoe.mirai-console") version "2.6.3"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}
group = "indi.mzt"
version = "0.1.0"
val scramble_version: String by project
val xmlgraphics_version: String by project
repositories {
    //特别注意，mavenCentral一定要放在maven前面，否侧外部库导入可能失败
    mavenCentral()
    maven{ url =uri("https://maven.aliyun.com/nexus/content/groups/public/")}
    jcenter()
    mavenLocal()
}
dependencies{
    //在IDE内运行的mcl添加滑块模块，请参考https://github.com/project-mirai/mirai-login-solver-selenium把版本更新为最新
    //runtimeOnly("net.mamoe:mirai-login-solver-selenium:1.0-dev-15")
    implementation("org.apache.xmlgraphics:batik-transcoder:$xmlgraphics_version")
    implementation("org.apache.xmlgraphics:batik-codec:$xmlgraphics_version")
    implementation("org.worldcubeassociation.tnoodle:lib-scrambles:$scramble_version")
}
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>{
    archiveFileName.set("${baseName}.${extension}")
}