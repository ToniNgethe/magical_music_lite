plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.magicalmusic.feature_selected_song"
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(Dependencies.ktxCore)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeToolingPreview)
    implementation(Dependencies.composeMaterial)
    implementation(project(mapOf("path" to ":core-network")))
    implementation(project(mapOf("path" to ":core-media")))
    implementation(project(mapOf("path" to ":features:feature-song")))
    debugImplementation(Dependencies.composeTooling)
    debugImplementation(Dependencies.accompanistPermission)
    implementation( Dependencies.androidYoutubePlayer )

    // compose
    implementation(Dependencies.composeViewModel)
    implementation(Dependencies.composeNavigation)

    //Dagger - Hilt
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltNavigationCompose)

    implementation(project(":core-navigation"))
    implementation(project(":resources"))
    implementation(project(":core-design"))
    implementation(project(":core-database"))
    implementation(project(":core-utils"))
}