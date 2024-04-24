plugins {
    id("kover-merge-convention")
    id("licenses-convention")
    id("org.sonarqube") version "5.0.0.4638"
}

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        mavenCentral()
    }

    version = "0.0.1-SNAPSHOT"
    group = "io.github.airflux"
}

dependencies {
    kover(project(":airflux-commons-collections-kmp"))
    kover(project(":airflux-commons-kotest-assertions-kmp"))
    kover(project(":airflux-commons-text-kmp"))
    kover(project(":airflux-commons-types-kmp"))
    kover(project(":airflux-commons-types-test-kmp"))
}

sonar {
    properties {
        property("sonar.projectKey", "airflux_airflux-commons-kmp")
        property("sonar.organization", "airflux")
        property("sonar.host.url", "https://sonarcloud.io")
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${projectDir.path}/build/reports/jacoco/test/jacocoTestReport.xml"
        )
    }
}
