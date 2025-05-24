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
//        const val parcelize = "kotlin-parcelize"
        const val gradleSecrets = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin"
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
        const val material3 = "androidx.compose.material3:material3:1.3.1"
        const val navigation = "androidx.navigation:navigation-compose:2.8.5"
        const val activityCompose = "androidx.activity:activity-compose:1.9.3"
        const val foundation = "androidx.compose.foundation:foundation-android:$version"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
        const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:$version"
    }

    object Tests {
        const val junit4 = "junit:junit:4.13.2"
        const val junitExt = "androidx.test.ext:junit:1.2.1"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.6.1"
        const val truth = "com.google.truth:truth:1.4.4"
    }

    object DataStore {
        const val dataStorePreferences = "androidx.datastore:datastore-preferences:1.1.1"
    }

    object Hilt {
        const val version = "2.54"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val hiltWork = "androidx.hilt:hilt-work:1.2.0"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.2.0"
    }

    object MongoRealm {
        const val version = "3.0.0"
        const val libraryBase = "io.realm.kotlin:library-base:$version"
    }

    object ViewModel {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7"
    }

    object WorkManager {
        const val workManager = "androidx.work:work-runtime-ktx:2.10.0"
    }

    object CameraX {
        const val version = "1.4.1"
        const val core = "androidx.camera:camera-core:$version"
        const val camera2 = "androidx.camera:camera-camera2:$version"
        const val lifecycle = "androidx.camera:camera-lifecycle:$version"
        const val view = "androidx.camera:camera-view:$version"
    }

    object Location {
        const val location = "com.google.android.gms:play-services-location:21.3.0"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:33.7.0"
        const val analytics = "com.google.firebase:firebase-analytics"
        const val crashlytics = "com.google.firebase:firebase-crashlytics"
        const val auth = "com.google.firebase:firebase-auth-ktx:23.2.1"
        const val firestore = "com.google.firebase:firebase-firestore-ktx:25.1.4"
        const val storage = "com.google.firebase:firebase-storage"
    }

    object GoogleMaps {
        const val googleMapsCompose = "com.google.maps.android:maps-compose:6.4.0"
    }

    object Places {
        const val googleMapsPlaces = "com.google.android.libraries.places:places:4.1.0"
    }

    //Other (General)
    const val coreKtx = "androidx.core:core-ktx:1.15.0"
    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:2.8.7"
    const val coilCompose = "io.coil-kt:coil-compose:2.7.0"
    const val lottieCompose = "com.airbnb.android:lottie-compose:6.6.2"
    const val exportProjectWord = "org.apache.poi:poi:5.3.0"
    const val exportProjectWordooxml = "org.apache.poi:poi-ooxml:5.3.0"
}