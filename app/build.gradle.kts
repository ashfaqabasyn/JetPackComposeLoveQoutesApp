import com.android.tools.r8.internal.im

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("kotlin-parcelize") // Add this line

}

android {
    namespace = "com.example.myloveqoutesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myloveqoutesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


}

composeCompiler {
    enableStrongSkippingMode=true
    reportsDestination=layout.buildDirectory.dir("compose_compiler")
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.storage)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)

    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.ssp.android)
    implementation(libs.sdp.android)
    implementation(libs.ui)
    implementation(libs.androidx.constraintlayout.v220beta01)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation("io.insert-koin:koin-android:3.5.3")
    implementation("io.insert-koin:koin-androidx-compose:3.5.3")

    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")




    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)

    kapt("androidx.room:room-compiler:2.6.1")

    // Koin core
    implementation("io.insert-koin:koin-core:3.4.0")
//
//    // Koin core
//    implementation "io.insert-koin:koin-core:3.4.0"
//
//// Koin Android
//    implementation "io.insert-koin:koin-android:3.4.0"

// Koin for Jetpack Compose
    implementation("io.insert-koin:koin-androidx-compose:3.5.3")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.31.0-alpha")







}
