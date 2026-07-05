# Task 1: Create Android Project Skeleton

**Files:**
- Create: `app/` through Android Studio using Empty Activity with Jetpack Compose.
- Create: `app/README.md`

**Interfaces:**
- Produces a buildable Android project using Kotlin and Compose.
- Later tasks rely on package name and Gradle project structure.

- [ ] **Step 1: Create project**

Use Android Studio:

```text
New Project -> Empty Activity
Name: ImageQualityAnalyzer
Package name: com.example.imagequalityanalyzer
Language: Kotlin
Minimum SDK: API 26 or higher
Build configuration language: Kotlin DSL if available
```

- [ ] **Step 2: Build the empty app**

Run in Android Studio:

```text
Build -> Make Project
```

Expected: build succeeds without source edits.

- [ ] **Step 3: Run on Redmi K70E or emulator**

Expected: default Compose screen opens.

- [ ] **Step 4: Add project note**

Create `app/README.md`:

```markdown
# Android App

This directory contains the Kotlin + Jetpack Compose implementation for the image quality analyzer.

Target device for validation: Redmi K70E.
```

## Controller Notes

The original brief names Android Studio as the creation path. If Android Studio is not available to the worker, create the closest standard Gradle Android project skeleton that is compatible with Kotlin and Jetpack Compose, or report `BLOCKED` with the missing dependency.

