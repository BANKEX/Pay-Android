-keepattributes Signature,Exceptions,*Annotation*
-keep public class com.elegion.android.** {
    public static <fields>;
    public static <methods>;
    public <methods>;
    protected <methods>;
}

-keep class retrofit.** { *; }
-dontwarn retrofit.**
-dontwarn com.squareup.**
-dontwarn droidkit.**

# gson
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

# droidkit
-keep class droidkit.** { *; }
-keep class **$SQLiteTable { *; }
-keep class com.elegion.android.model.** { *; }
-dontwarn droidkit.**

# Joda Time
-dontwarn org.joda.convert.**

# DK loaders
-keep class **$LC* { *; }

# DK ViewInjectors
-keep class **.*$ViewInjector* { *; }

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

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

# okhttp
-dontwarn okio.**

### HockeyApp
-keep class javax.net.ssl.** { *; }
-keep class org.apache.http.** { *; }

-keep class net.hockeyapp.android.** { *; }
-dontwarn net.hockeyapp.android.**

# ReactiveX
-keep class rx.** { *; }
-dontwarn rx.**
