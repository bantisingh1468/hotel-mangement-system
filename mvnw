#!/bin/sh
# ----------------------------------------------------------------------------
# Maven Wrapper Bootstrap
# ----------------------------------------------------------------------------

set -e

BASEDIR=$(cd "$(dirname "$0")"; pwd)
WRAPPER_JAR="$BASEDIR/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_PROPS="$BASEDIR/.mvn/wrapper/maven-wrapper.properties"

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Error: $WRAPPER_JAR not found. Please re-download the wrapper." >&2
  exit 1
fi

JAVA_CMD="java"
JAVA_HOME=${JAVA_HOME:-}
if [ -n "$JAVA_HOME" ]; then
  JAVA_CMD="$JAVA_HOME/bin/java"
fi

exec "$JAVA_CMD" "-Dmaven.multiModuleProjectDirectory=$BASEDIR" -cp "$WRAPPER_JAR" org.apache.maven.wrapper.MavenWrapperMain "$@"

