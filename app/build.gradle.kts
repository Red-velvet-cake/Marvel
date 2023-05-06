plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.red_velvet.marvel"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.red_velvet.marvel"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    dataBinding{
        enable = true
    }
    viewBinding {
        enable = true
    }
    dataBinding {
        enable = true
    }
}

dependencies {
    val lifecycleVersion = "2.6.1"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.fragment:fragment-ktx:1.5.7")
    implementation("androidx.activity:activity-ktx:1.7.1")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // RxJava
    implementation("io.reactivex.rxjava3:rxjava:3.1.6")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
}