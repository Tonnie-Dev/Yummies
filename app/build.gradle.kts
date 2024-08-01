plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.parcelize.plugin)
}

android {
    namespace = "com.uxstate.yummies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.uxstate.yummies"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
            //multiDexEnabled = true
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Coil
    implementation(libs.coil.compose)

    // Dagger Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Retrofit
    implementation(Square.retrofit2)
    implementation(Square.okHttp3)
    implementation(Square.okHttp3.loggingInterceptor)


    // Moshi Library Dependencies - Core Moshi JSON Library and Moshi"s Kotlin support and converter factory
    implementation(Square.moshi)
    implementation(Square.moshi.kotlinReflect)
    implementation(Square.retrofit2.converter.moshi)

    // Room components
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Lottie Animation
    implementation(libs.lottie.compose)

    // Compose Nav Destinations
    implementation(libs.compose.destinations.core.one)
    ksp(libs.compose.destinations.ksp.one)

    //shimmer

    implementation(libs.shimmer)

    //Firebase Analytics
    //implementation libs.firebase.crashlytics.ktx
    //implementation libs.firebase.analytics.ktx


    //Flow Layout

    implementation(libs.accompanist.flowlayout)

    //Timber Logging
    implementation(libs.timber)

    // Swipe to Refresh - Accompanist

    implementation(libs.accompanist.swiperefresh)
    // System UI Controller - Accompanist
    //implementation libs.accompanist.systemuicontroller
    implementation(libs.accompanist.systemuicontroller)

}