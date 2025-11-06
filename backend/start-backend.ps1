# Start backend PowerShell helper
# Usage:
#  ./start-backend.ps1            -> builds (using gradlew) and starts the jar in background, logs to ./logs/
#  ./start-backend.ps1 -NoBuild   -> does not build, just starts existing jar
#  ./start-backend.ps1 -Open      -> after start, opens Swagger and H2 console in default browser
param(
    [switch]$NoBuild,
    [switch]$Open
)

$root = Split-Path -Parent $MyInvocation.MyCommand.Definition
Push-Location $root

# Ensure logs folder
$logDir = Join-Path $root 'logs'
if (-not (Test-Path $logDir)) {
    New-Item -ItemType Directory -Path $logDir | Out-Null
}

if (-not $NoBuild) {
    if (Test-Path "./gradlew.bat") {
        Write-Host "Running build with gradle wrapper..."
        & .\gradlew.bat clean build
        if ($LASTEXITCODE -ne 0) {
            Write-Error "Build failed. Inspect output and fix errors before starting."
            Pop-Location
            exit $LASTEXITCODE
        }
    } else {
        Write-Warning "gradlew.bat not found. Skipping build step."
    }
}

# Find jar
$libs = Join-Path $root 'build\libs'
if (-not (Test-Path $libs)) {
    Write-Error "build\libs directory not found. Run the build or check paths."
    Pop-Location
    exit 1
}

$jars = Get-ChildItem -Path $libs -Filter '*.jar' | Sort-Object LastWriteTime -Descending
if ($jars.Count -eq 0) {
    Write-Error "No jar found in build\libs. Build the project first."
    Pop-Location
    exit 1
}

# Prefer the executable (non '-plain') jar when available
$preferred = $jars | Where-Object { $_.Name -notmatch '-plain' }
if ($preferred.Count -gt 0) {
    $jar = $preferred[0].FullName
} else {
    $jar = $jars[0].FullName
}
Write-Host "Starting jar: $jar"

$stdout = Join-Path $logDir 'backend.log'
$stderr = Join-Path $logDir 'backend.err'

# Start process
$proc = Start-Process -FilePath java -ArgumentList "-jar", "$jar" -RedirectStandardOutput $stdout -RedirectStandardError $stderr -PassThru
Write-Host "Started process PID: $($proc.Id)"

# Wait for the application to become available (poll /swagger-ui.html) up to 30s
$maxWait = 30
$sw = 0
$up = $false
while ($sw -lt $maxWait) {
    Start-Sleep -Seconds 1
    try {
        $resp = Invoke-WebRequest -Uri http://localhost:8080/swagger-ui.html -UseBasicParsing -TimeoutSec 2 -ErrorAction Stop
        if ($resp.StatusCode -eq 200) { $up = $true; break }
    } catch {
        # not up yet
    }
    $sw++
}

if ($up) {
    Write-Host "Application is up (swagger reachable)."
} else {
    Write-Warning "Application did not respond on port 8080 within $maxWait seconds. Check logs: $stdout"
}

Write-Host "Tailing logs (last 20 lines):"
Start-Sleep -Seconds 1
if (Test-Path $stdout) {
    Get-Content -Path $stdout -Tail 20 -ErrorAction SilentlyContinue
} else {
    Write-Host "Log file not yet created: $stdout"
}

if ($Open -and $up) {
    Start-Process "http://localhost:8080/swagger-ui.html"
    Start-Process "http://localhost:8080/h2-console"
}

Pop-Location
