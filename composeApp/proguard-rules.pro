# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
########################################
## Jetpack Compose
########################################
# Keep Compose runtime classes (reflection + lambdas)
-keep class androidx.compose.** { *; }
-keep class kotlinx.coroutines.android.** { *; }

# Do not strip annotations used by Compose
-keepattributes *Annotation*

# Avoid warnings from Compose compiler-generated classes
-dontwarn androidx.compose.**

########################################
## Kotlin
########################################
# Keep Kotlin metadata (used for reflection and serialization)
-keepclassmembers class kotlin.Metadata { *; }
-keepattributes InnerClasses,EnclosingMethod,Signature,RuntimeVisibleAnnotations,AnnotationDefault

########################################
## Kotlinx Serialization
########################################
# Keep serializer classes
-keep class kotlinx.serialization.** { *; }
-dontwarn kotlinx.serialization.**

########################################
## Room (uses annotation processing & reflection)
########################################
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**
-keep class * extends androidx.room.RoomDatabase
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class **Database_Impl { *; }

# Keep entities, daos, and generated code
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Dao class *
-keep interface * implements androidx.room.RoomDatabase

########################################
## Koin (DI - reflection)
########################################
-keep class org.koin.** { *; }
-dontwarn org.koin.**

########################################
## Ktor (network client)
########################################
-dontwarn io.ktor.**
-keep class io.ktor.** { *; }

########################################
## Coil (image loading)
########################################
-keep class coil.** { *; }
-dontwarn coil.**

########################################
## Multiplatform Settings
########################################
-dontwarn com.russhwolf.settings.**

########################################
## FileKit
########################################
-dontwarn com.mayakapps.filekit.**

########################################
## General Rules
########################################
# Keep Application class
-keep class com.cairosquad.evolvefit.** { *; }

# Keep all models in case they're serialized/deserialized via reflection
-keep class com.cairosquad.evolvefit.model.** { *; }

# Allow shrinking of unused resources
-dontnote kotlin.**
-dontwarn kotlin.**
