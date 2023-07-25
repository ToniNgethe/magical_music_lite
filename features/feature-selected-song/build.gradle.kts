plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.magicalmusic.feature_selected_song"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.bundles.compose)
    implementation(libs.timber)

    implementation(project(mapOf("path" to ":core:core-network")))
    implementation(project(mapOf("path" to ":core:core-media")))
    implementation(libs.accompanist.permissions)
    implementation(project(":core:songs"))
    debugImplementation(libs.accompanist.permissions)
    implementation( Dependencies.androidYoutubePlayer )
    
    //Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(project(":core:core-navigation"))
    implementation(project(":core:resources"))
    implementation(project(":core:core-design"))
    implementation(project(":core:core-database"))
    implementation(project(":core:core-utils"))
}