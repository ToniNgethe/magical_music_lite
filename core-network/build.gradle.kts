import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

val localProperties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    namespace = "com.margicalmusic.core_network"
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        externalNativeBuild {
            cmake {
                cppFlags("")
            }
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", localProperties.getProperty("API_URL"))
        }
        release {
            buildConfigField("String", "BASE_URL", localProperties.getProperty("API_URL"))
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
            version = "3.18.1"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverterMoshi)
    implementation(Dependencies.loggingInterceptor)
    implementation(Dependencies.retrofitCoroutineAdapter)

    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
}