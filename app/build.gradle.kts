plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.test"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.test"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")

    // AppCompat for backward compatibility
    implementation("androidx.appcompat:appcompat:1.7.0")

    // Material Design components
    implementation("com.google.android.material:material:1.12.0")

    // ConstraintLayout for complex layouts
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // ViewModel and LiveData for lifecycle-aware components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // Activity KTX for easier Activity management
    implementation("androidx.activity:activity-ktx:1.7.2")

    // Fragment KTX for easier Fragment management
    implementation("androidx.fragment:fragment-ktx:1.6.1")

    // RecyclerView for displaying lists
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation(libs.androidx.activity)

    // Unit Testing
    testImplementation("junit:junit:4.13.2")

    // AndroidX Test - Instrumentation testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}