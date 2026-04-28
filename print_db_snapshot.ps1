param(
    [string]$DbContainer = "db",
    [string]$DbName = "caballos_db",
    [string]$DbUser = "caballos_user",
    [string]$DbPassword = "caballos_password"
)

$ErrorActionPreference = "Stop"

function Print-Line {
    param([int]$Width = 100)
    Write-Output ("-" * $Width)
}

function Invoke-MySql {
    param([string]$Sql, [switch]$Table)

    $args = @("compose", "exec", "-T", $DbContainer, "mysql", "-u$DbUser", "-p$DbPassword", "-D", $DbName)
    if ($Table) {
        $args += @("-t", "-e", $Sql)
    } else {
        $args += @("-N", "-s", "-e", $Sql)
    }

    return (& docker @args 2>&1)
}

Write-Output "DB snapshot"
Write-Output "Container: $DbContainer"
Write-Output "Database:  $DbName"
Write-Output "User:      $DbUser"
Print-Line

$tablesRaw = Invoke-MySql -Sql "SHOW TABLES"
if (-not $tablesRaw -or $tablesRaw.Count -eq 0) {
    Write-Output "No tables found in $DbName"
    exit 0
}

$tables = @($tablesRaw | Where-Object { $_ -and $_.Trim() -ne "" -and $_ -notmatch "^mysql:" })

if ($tables.Count -eq 0) {
    Write-Output "No tables found in $DbName"
    exit 0
}

Write-Output "Tables found ($($tables.Count)):"
$tables | ForEach-Object { Write-Output " - $_" }
Print-Line

foreach ($table in $tables) {
    Write-Output ""
    Write-Output "TABLE: $table"
    Print-Line

    $rowCountRaw = Invoke-MySql -Sql "SELECT COUNT(*) FROM ``$table``"
    $rowCount = ($rowCountRaw | Where-Object { $_ -and $_.Trim() -ne "" -and $_ -notmatch "^mysql:" } | Select-Object -First 1)
    if (-not $rowCount) {
        $rowCount = "0"
    }

    Write-Output "Rows: $rowCount"

    if ($rowCount -eq "0") {
        Write-Output "(empty table)"
        continue
    }

    $tableOutput = Invoke-MySql -Sql "SELECT * FROM ``$table``" -Table
    $filtered = $tableOutput | Where-Object { $_ -notmatch "^mysql:" }
    $filtered | ForEach-Object { Write-Output $_ }
}

Print-Line
Write-Output "Done."
