plugins {
    id("kotlin-library-convention")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":airflux-commons-types-kmp"))
    implementation(libs.bundles.kotest)
}
