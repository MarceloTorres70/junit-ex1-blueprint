#!/usr/bin/env pwsh
Set-Location "D:\Archivos\Nube\Escritorio\VII SEMESTRE\VERIFICACIÓN Y VALIDACIÓN DE SOFTWARE\LABO 1\junit-ex1-blueprint"

Write-Host "=== Verificando estado de Git ===" -ForegroundColor Cyan
git status

Write-Host "`n=== Agregando archivos al staging ===" -ForegroundColor Cyan
git add .

Write-Host "`n=== Realizando commit ===" -ForegroundColor Cyan
git commit -m "Lab 6: Análisis Estático con SonarQube - Configuración Docker, integración Maven, supresión de warnings S4684"

Write-Host "`n=== Últimos commits ===" -ForegroundColor Cyan
git log --oneline -n 3

Write-Host "`n=== Subiendo a GitHub ===" -ForegroundColor Cyan
git push origin laboratorio-6

Write-Host "`n=== Proceso completado ===" -ForegroundColor Green

