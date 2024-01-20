import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

val githubPropertiesFile = rootProject.file("github.properties")
val githubProperties = Properties()
githubProperties.load(githubPropertiesFile.inputStream())

android {
    namespace = "com.example.poc_sdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
//            from(components["java"])

            groupId = "com.poc"
            artifactId = "poc-sdk"
            version = "1.0-beta"
            artifact("$buildDir/outputs/aar/poc-sdk-release.aar")
        }

        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/amirraza/MyGithubPackages")

                credentials {
                    username = githubProperties.getProperty("username")?.toString() ?: ""
                    password = githubProperties.getProperty("token")?.toString() ?: ""
                }
            }
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}