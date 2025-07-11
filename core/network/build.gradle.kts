import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.example.network"
    compileSdk = 35

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").reader())

    defaultConfig {
        minSdk = 26

        buildConfigField("String", "API_TOKEN", "\"${properties.getProperty("token")}\"")

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures.buildConfig = true
}

dependencies {
    implementation(project(":core:model"))

    // Serialization
    api(libs.jetbrains.kotlinx.serialization.json)

    //Retrofit
    api(libs.squareup.retrofit)
    api(libs.squareup.converter.kotlinx.serialization)

    //Okhttp
    api(libs.squareup.okhttp)

    //Dagger
    api(libs.google.dagger.dagger)
    ksp(libs.google.dagger.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.android.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}