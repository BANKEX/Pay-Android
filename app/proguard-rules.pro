-keepattributes Signature,Exceptions,*Annotation*

-dontwarn com.squareup.okhttp.**
-dontwarn com.squareup.otto.**
-dontwarn okio.**
-dontwarn retrofit.**
-dontwarn droidkit.**

### Google Play Services
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

### HockeyApp
-keep class javax.net.ssl.** { *; }
-keep class org.apache.http.** { *; }

-keep class net.hockeyapp.android.** { *; }
-dontwarn net.hockeyapp.android.**

### Google GSON
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

### Android Annotations
-keep class org.androidannotations.** { *; }
-dontwarn org.androidannotations.**