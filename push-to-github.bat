@echo off
echo ========================================
echo Pushing Hotel Management System to GitHub
echo ========================================
echo.

cd /d "%~dp0"

echo Checking git status...
git status

echo.
echo Ready to push to GitHub!
echo.
echo You will be prompted for credentials:
echo   Username: bantisingh1468
echo   Password: [Enter your Personal Access Token]
echo.
echo If you don't have a token, get one from:
echo https://github.com/settings/tokens
echo.

pause

echo.
echo Pushing to GitHub...
git push -u origin main

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo SUCCESS! Code pushed to GitHub!
    echo ========================================
    echo Repository: https://github.com/bantisingh1468/hotel-mangement-system
) else (
    echo.
    echo ========================================
    echo ERROR: Push failed!
    echo ========================================
    echo Make sure you have:
    echo 1. Created a Personal Access Token
    echo 2. Entered correct credentials
)

pause

