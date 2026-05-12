# Agentic Mobile

A minimal Kotlin Multiplatform mobile starter app with a shared Compose Multiplatform UI for Android and iOS.

## Preview

![Agentic Mobile preview](images/agentic-mobile-preview.png)

## Targets

- Android 12 and higher (`minSdk = 31`)
- iOS 16 and higher

## Identifiers

- Android application ID: `de.mistatee.agenticmobile`
- iOS bundle identifier: `de.mistatee.agenticmobile`

## Run the app

### Android

```bash
./gradlew :composeApp:assembleDebug
```

Open the project in Android Studio to run the Android app.

### iOS

Open `iosApp/iosApp.xcodeproj` in Xcode and run the `iosApp` target on an iOS 16+ simulator or device.
