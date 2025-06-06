@Suppress("MemberVisibilityCanBePrivate")
object Dependencies {

    object Kotlin {
        const val version = "2.1.21"
        const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0-RC"
    }

    object Plugins {
        const val androidLibrary = "com.android.library"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val kotlinSerialization = "org.jetbrains.kotlin.plugin.serialization"
        const val hilt = "com.google.dagger.hilt.android"
        const val kotlin_kapt = "kotlin-kapt"
        const val composeCompiler = "org.jetbrains.kotlin.plugin.compose"
        const val androidApplication = "com.android.application"
    }

    object Gradle {
        const val agpVersion = "8.10.0"
    }

    object Compose {
        const val version = "1.7.6"

        //libs
        const val ui = "androidx.compose.ui:ui:$version"
        const val uiGoogleFonts = "androidx.compose.ui:ui-text-google-fonts:1.8.1"
        const val material3 = "androidx.compose.material3:material3:1.3.1"
        const val navigation = "androidx.navigation:navigation-compose:2.8.5"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
    }

    object DataStore {
        const val dataStorePreferences = "androidx.datastore:datastore-preferences:1.1.1"
    }

    object Hilt {
        const val version = "2.54"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.2.0"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:33.7.0"
        const val auth = "com.google.firebase:firebase-auth-ktx:23.2.1"
        const val firestore = "com.google.firebase:firebase-firestore-ktx:25.1.4"
    }
}