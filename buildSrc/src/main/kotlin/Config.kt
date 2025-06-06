import org.gradle.api.JavaVersion

object Config {
    const val compileSdk = 35
    const val targetSdk = 35
    const val minSdk = 26 // Android 8 (Oreo)
    const val versionCode = 13
    const val versionName = "0.0.5"
    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"
    const val minifyEnabled = true
}