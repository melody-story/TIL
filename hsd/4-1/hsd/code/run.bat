@echo off
if "%~1"=="" (
    echo Usage: %0 ClassName
    exit /b 1
)

echo Compiling %1.java...
javac %1.java
if %errorlevel% neq 0 (
    echo Compilation failed.
    exit /b %errorlevel%
)

echo ================================
echo Running %1...
java %1
