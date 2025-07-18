import io.gitlab.arturbosch.detekt.Detekt
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.arturbosch.detekt)
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom(file("$rootDir/app/detekt.yml"))
    tasks.withType<Detekt>().configureEach {
        reports {
            html.required.set(true)  // build/reports/detekt/detekt.html
            xml.required.set(false)
            txt.required.set(false)
            sarif.required.set(false)
        }
        ignoreFailures = true
    }
}

android {
    namespace = "com.example.yafinance"
    compileSdk = 35

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").reader())

    defaultConfig {
        applicationId = "com.example.yafinance"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_TOKEN", "\"${properties.getProperty("token")}\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue("string", "token", properties.getProperty("token"))
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
    buildFeatures {
        compose = true
    }
    buildFeatures.buildConfig = true
}

dependencies {
    implementation(project(":feature:expense"))
    implementation(project(":feature:income"))
    implementation(project(":feature:account"))
    implementation(project(":feature:category"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:editTransaction"))


    implementation(project(":core:common:utils"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:di"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":core:design"))
    implementation(project(":core:database"))
    implementation(project(":core:workManager"))

    //WorkManager
    implementation(libs.androidx.work)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Serialization
    implementation(libs.jetbrains.kotlinx.serialization.json)

    // Lottie
    implementation(libs.airbnb.lottie.compose)

    //Material
    implementation(libs.google.android.material)

    //Dagger
    implementation(libs.google.dagger.dagger)
    ksp(libs.google.dagger.compiler)

    //Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.converter.kotlinx.serialization)

    //Okhttp
    implementation(libs.squareup.okhttp)

    //DataStore
    implementation(libs.androidx.datastore.preferences)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}