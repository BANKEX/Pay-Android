-keepattributes Signature,Exceptions,InnerClasses,EnclosingMethod,*Annotation*
-keep public class com.elegion.android.** {
    public static <fields>;
    public static <methods>;
    public <methods>;
    protected <methods>;
}

# gson
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

# support library
-keep class android.support.v4.** { *; }
-dontwarn android.support.v4.**

-keep class android.support.v7.** { *; }
-dontwarn android.support.v7.**

-keep class android.support.design.** { *; }
-dontwarn android.support.design.**

# Retrofit
-dontwarn okio.**
-dontwarn retrofit2.**

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

# RxJava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#RxSQLite
-keep class sqlite4a.** { *; }
-keep class **$$Schema { *; }

-keep public class ru.globus.app.behavior.ParallaxBehavior {
    public <init>(...);
    public <methods>;
}


# ButterKnife
-dontwarn butterknife.internal.**
-keep class butterknife.** { *; }
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
