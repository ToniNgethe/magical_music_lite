object Versions {
    internal const val compose = "1.3.0-alpha01"
    internal const val retrofit = "2.9.0"
    internal const val coroutines = "1.6.3"
    internal const val room = "2.4.2"
    internal const val dataStore = "1.0.0"
    internal const val RETROFIT_KOTLIN_SERIALIZATION = "0.8.0"
    internal const val KOTLIN_SERIALIZATION_JSON = "1.3.2"
    const val COMPOSE_COMPILER = "1.3.0"
}

// TODO: user version catalog
object Dependencies {
    // core
    const val ktxCore = "androidx.core:core-ktx:1.8.0"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.0"

    // compose
    const val activityCompose = "androidx.activity:activity-compose:1.5.0"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeUiTest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0"
    const val composeNavigation = "androidx.navigation:navigation-compose:2.5.0"

    // ui
    const val accompanist = "com.google.accompanist:accompanist-systemuicontroller:0.17.0"
    const val accompanistPermission = "com.google.accompanist:accompanist-permissions:0.25.0"
    const val koil = "io.coil-kt:coil-compose:2.1.0"
    const val androidYoutubePlayer = "com.pierfrancescosoffritti.androidyoutubeplayer:core:11.0.1"

    // dagger - hilt
    const val hilt = "com.google.dagger:hilt-android:2.42"
    const val hiltLifecycleViewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:2.42"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"

    // tools
    const val timber = "com.jakewharton.timber:timber:5.0.1"

    // retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3"
    const val retrofitCoroutineAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    const val mockWebserver = "com.squareup.okhttp3:mockwebserver:4.9.3"
    const val retrofitKotlinSerialization =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.RETROFIT_KOTLIN_SERIALIZATION}"

    // Coroutines & Coroutine Lifecycle Scopes
    const val viewmodelLifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // room
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomKRuntime = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // data store
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"

    // test
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val jUnit = "junit:junit:4.13.2"
    const val expressoCore = "androidx.test.espresso:espresso-core:3.4.0"
    const val androidxJunit = "androidx.test.ext:junit:1.1.3"
    const val googleTruth = "com.google.truth:truth:1.1.3"
    const val mockk = "io.mockk:mockk:1.12.4"
    const val composeUiTestJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

    // others
    const val gson = "com.google.code.gson:gson:2.9.0"
    const val kotlinSerializationJson =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLIN_SERIALIZATION_JSON}"
}

