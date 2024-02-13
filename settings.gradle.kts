import java.util.Properties

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

val githubPropertiesFile = file("github.properties")
val githubProperties = Properties()
//githubProperties.load(githubPropertiesFile.inputStream())

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven {
            url = uri("https://jitpack.io")
        }
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

rootProject.name = "GithubArtifactPoC"
include(":app")
include(":poc-sdk")
