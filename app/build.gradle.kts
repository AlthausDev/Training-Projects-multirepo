plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.ksp)
    alias(libs.plugins.daggerHiltAndroid)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.crashlytics)
}

android {
    namespace = "com.althaus.dev.cinemaNexus"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.althaus.dev.cinemaNexus"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // --- Librerías Core de Android ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.kotlinx.metadata.jvm)

    // --- Ciclo de Vida ---
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // --- Compose y UI ---
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.runtime)

    // --- Navegación ---
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.navigation.compose)


    // --- Firebase ---
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.realtime)
    implementation(libs.firebase.config)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.storage.ktx)

    // --- Firebase App Check ---
    implementation(libs.firebase.appcheck.playintegrity)
    implementation(libs.firebase.appcheck.safetynet)

    // --- Inyección de Dependencias con Hilt ---
    implementation(libs.google.firebase.auth)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)


    // --- Servicios de Google ---
    implementation(libs.googlePlayServicesAuth)
    implementation(libs.play.services.location)
    implementation(libs.googleid)

    // --- Utilidades para Compose ---
    implementation(libs.accompanist.flowlayout)

    // --- Manejo de Imágenes ---
    implementation(libs.coil)
    implementation(libs.picasso)

    // --- Diseño Material ---
    implementation(libs.material)

    // --- Persistencia con Room ---
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    // --- API REST con Retrofit ---
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // --- Testing ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.espresso.core)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.mockk)
}