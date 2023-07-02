// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
//    ext {
//        compose_ui_version = '1.4.3'
//    }


}

plugins {
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("com.android.application")  version "7.4.1" apply false
    id("com.android.library")  version "7.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}
