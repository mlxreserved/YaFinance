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

rootProject.name = "YaFinance"
include(":app")

include(":feature:expense")

include(":core:common:utils")
include(":core:ui")
include(":core:design")
include(":core:model")
include(":core:network")
include(":core:domain")
include(":core:data")
include(":core:di")
include(":feature:income")
include(":feature:account")
include(":feature:category")
include(":feature:settings")
include(":feature:editTransaction")
include(":core:database")
include(":core:workManager")
