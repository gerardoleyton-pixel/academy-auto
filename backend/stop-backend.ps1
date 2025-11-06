# Stop backend helper
# Usage:
#  ./stop-backend.ps1         -> stops java process running academy-backend jar (best-effort)
#  ./stop-backend.ps1 -Pid 1234 -> stops process by PID
param(
    [int]$Pid
)

$root = Split-Path -Parent $MyInvocation.MyCommand.Definition
Push-Location $root

if ($Pid) {
    try { Stop-Process -Id $Pid -Force -ErrorAction Stop; Write-Host "Stopped PID $Pid" } catch { Write-Warning "Could not stop PID $Pid: $_" }
    Pop-Location
    return
}

# Try to find java processes that were started with our jar name
$targetName = 'academy-backend'
$procs = Get-CimInstance Win32_Process | Where-Object { $_.CommandLine -and ($_.CommandLine -match $targetName) }
if ($procs.Count -eq 0) {
    Write-Host "No matching java process found with '$targetName' in command line. Listing java processes instead:"
    Get-Process java -ErrorAction SilentlyContinue
    Pop-Location
    exit 0
}

foreach ($p in $procs) {
    try {
        Write-Host "Stopping PID $($p.ProcessId) -> $($p.CommandLine)"
        Stop-Process -Id $p.ProcessId -Force
    } catch {
        Write-Warning "Failed to stop PID $($p.ProcessId): $_"
    }
}

Pop-Location
