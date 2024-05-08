// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies{
        classpath ("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false

    id ("com.google.dagger.hilt.android") version "2.48" apply false
    id ("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false

}