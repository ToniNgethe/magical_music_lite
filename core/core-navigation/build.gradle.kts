plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.core_navigation"
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
    implementation(Dependencies.ktxCore)
    implementation(Dependencies.lifecycleRuntime)
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")


    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeToolingPreview)
    implementation(Dependencies.composeMaterial)
    debugImplementation(Dependencies.composeTooling)
    debugImplementation(Dependencies.activityCompose)

    // compose
    implementation(Dependencies.composeViewModel)
    implementation(Dependencies.composeNavigation)

    implementation(project(mapOf("path" to ":core:resources")))
}