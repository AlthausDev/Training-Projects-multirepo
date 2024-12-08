plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id ("kotlin-kapt")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")

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
        debug{
            applicationIdSuffix = ".debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding  {
        enable = true
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    // --- Librerías Core de Android ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.datastore.preferences)

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

    // --- Navegación ---
    implementation(libs.androidx.navigation.compose)

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
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

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
