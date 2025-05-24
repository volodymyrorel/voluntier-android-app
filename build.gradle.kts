// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Dependencies.Plugins.androidApplication) version Dependencies.Gradle.agpVersion apply false
    id(Dependencies.Plugins.androidLibrary) version Dependencies.Gradle.agpVersion apply false
    id(Dependencies.Plugins.kotlinAndroid) version Dependencies.Kotlin.version apply false
    id(Dependencies.Plugins.composeCompiler) version Dependencies.Kotlin.version apply false
    id(Dependencies.Plugins.kotlinSerialization) version Dependencies.Kotlin.version
    id("com.google.gms.google-services") version "4.4.2" apply false
    kotlin("kapt") version Dependencies.Kotlin.version
    kotlin("jvm") version Dependencies.Kotlin.version
    id(Dependencies.Plugins.hilt) version Dependencies.Hilt.version apply false
}