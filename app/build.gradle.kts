plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services") // Necesario para Firebase
}

android {
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.vista"  // Asegúrate de que este sea tu nombre de paquete
        minSdk = 34
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Firebase
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Google Sign-In (si es necesario)
    implementation("com.google.android.gms:play-services-auth:19.2.0")

    // Otras dependencias de Compose y Android
    implementation("androidx.compose.ui:ui:1.0.0")
    implementation("androidx.compose.material3:material3:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
}

