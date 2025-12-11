@ECHO OFF
SETLOCAL

SET "BASE_DIR=%~dp0"
IF "%BASE_DIR:~-1%"=="\" SET "BASE_DIR=%BASE_DIR:~0,-1%"
SET WRAPPER_JAR=%BASE_DIR%\.mvn\wrapper\maven-wrapper.jar
SET WRAPPER_PROPS=%BASE_DIR%\.mvn\wrapper\maven-wrapper.properties

IF NOT EXIST "%WRAPPER_JAR%" (
  ECHO Error: %WRAPPER_JAR% not found. Please re-download the wrapper.
  EXIT /B 1
)

SET JAVA_CMD=java
IF NOT "%JAVA_HOME%"=="" (
  SET JAVA_CMD=%JAVA_HOME%\bin\java
)

"%JAVA_CMD%" "-Dmaven.multiModuleProjectDirectory=%BASE_DIR%" -cp "%WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
ENDLOCAL

