@echo off
setlocal

powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0print_db_snapshot.ps1" %*

endlocal
