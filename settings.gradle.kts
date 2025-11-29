pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CoursesApp"
include(":app")
include(":feature:auth")
include(":feature:main")
include(":core:designsystem")
include(":feature:course")
include(":core:data")
include(":core:network")
include(":core:model")
include(":feature:profile")
include(":feature:bookmarks")
include(":core:ui")
