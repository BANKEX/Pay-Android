-keepattributes Signature,Exceptions,*Annotation*

-keep class retrofit.** { *; }
-dontwarn retrofit.**
-dontwarn com.squareup.**
-dontwarn droidkit.**

-keep class ru.ok.okgifts.** {
    @droidkit.inject.* <fields>;
    @droidkit.inject.* <methods>;
}

-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

# gson
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

# droidkit
-keep class droidkit.** { *; }
-dontwarn droidkit.**

# loaders
-keep class **.*$LC* { *; }

# support library
-keep class android.support.v4.** { *; }
-dontwarn android.support.v4.**

-keep class android.support.v7.** { *; }
-dontwarn android.support.v7.**

-keep class android.support.design.** { *; }
-dontwarn android.support.design.**

# retrofit
-keep class retrofit.** { *; }
-keepclassmembernames interface * {
    @retrofit.http.* <methods>;
}

# picasso
-dontwarn com.squareup.okhttp.**

# okhttp
-dontwarn okio.**

### HockeyApp
-keep class javax.net.ssl.** { *; }
-keep class org.apache.http.** { *; }

-keep class net.hockeyapp.android.** { *; }
-dontwarn net.hockeyapp.android.**
