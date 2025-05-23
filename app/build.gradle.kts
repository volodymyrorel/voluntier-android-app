plugins {
    id(Dependencies.Plugins.androidApplication)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.kotlin_kapt)
    id(Dependencies.Plugins.hilt)
    id(Dependencies.Plugins.kotlinSerialization)
    id(Dependencies.Plugins.composeCompiler)
}

android {
    namespace = "uzhnu.volodymyrorel.voluntier"
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = "uzhnu.volodymyrorel.voluntier"
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = Config.minifyEnabled
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //Compose
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiTooling)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.navigation)

    //Hilt
    implementation(Dependencies.Hilt.hilt)
    implementation(Dependencies.Hilt.hiltNavigation)
    kapt(Dependencies.Hilt.compiler)

    //Serialization
    implementation(Dependencies.Kotlin.serializationJson)

    //Data Store
    implementation(Dependencies.DataStore.dataStorePreferences)
}