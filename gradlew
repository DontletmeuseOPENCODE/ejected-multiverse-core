#!/bin/sh
# Minimal POSIX gradlew. Używa gradle/wrapper/gradle-wrapper.jar (ściągany
# automatycznie przez Gradle przy pierwszym buildzie albo przez `gradle wrapper`).
#
# Pełny skrypt generuje się przez `gradle wrapper` z zainstalowanego Gradle'a,
# ale ten minimalny fallback działa na Linux/macOS/WSL bez zewnętrznych zależności.

APP_HOME="$(cd "$(dirname "$0")" && pwd)"
WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
WRAPPER_PROPS="$APP_HOME/gradle/wrapper/gradle-wrapper.properties"

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "ERROR: gradle-wrapper.jar not found at $WRAPPER_JAR" >&2
  echo "Run: gradle wrapper --gradle-version 8.10.2" >&2
  exit 1
fi

if [ ! -f "$WRAPPER_PROPS" ]; then
  echo "ERROR: gradle-wrapper.properties not found at $WRAPPER_PROPS" >&2
  exit 1
fi

# JAVA_HOME detection (fallback to java on PATH)
if [ -z "$JAVA_HOME" ]; then
  JAVA_HOME_RAW="$(command -v java 2>/dev/null)"
  if [ -n "$JAVA_HOME_RAW" ]; then
    # Strip /bin/java suffix
    JAVA_HOME="$(dirname "$(dirname "$JAVA_HOME_RAW")")"
  fi
fi

if [ -z "$JAVA_HOME" ] || [ ! -x "$JAVA_HOME/bin/java" ]; then
  echo "ERROR: JAVA_HOME is not set and no java found on PATH" >&2
  exit 1
fi

exec "$JAVA_HOME/bin/java" \
  -Dorg.gradle.appname="$(basename "$0")" \
  -classpath "$WRAPPER_JAR" \
  org.gradle.wrapper.GradleWrapperMain "$@"
