# Agentic Mobile

A minimal Kotlin Multiplatform mobile starter app with a shared Compose Multiplatform UI for Android and iOS.

## Targets

- Android 12.0 and higher (`minSdk = 31`, API 31+)
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

## Quality checks

```bash
./gradlew detekt
./gradlew :composeApp:allTests
./gradlew :composeApp:validateDebugScreenshotTest
```

To refresh Compose preview screenshot baselines for Android:

```bash
./gradlew :composeApp:updateDebugScreenshotTest
```
