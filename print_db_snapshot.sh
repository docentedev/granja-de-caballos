#!/usr/bin/env bash
set -euo pipefail

# Usage examples:
#   ./print_db_snapshot.sh
#   DB_CONTAINER=db DB_NAME=caballos_db DB_USER=caballos_user DB_PASSWORD=caballos_password ./print_db_snapshot.sh
#
# Windows usage:
#   .\print_db_snapshot.cmd
#   powershell -ExecutionPolicy Bypass -File .\print_db_snapshot.ps1

DB_CONTAINER="${DB_CONTAINER:-db}"
DB_NAME="${DB_NAME:-caballos_db}"
DB_USER="${DB_USER:-caballos_user}"
DB_PASSWORD="${DB_PASSWORD:-caballos_password}"

MYSQL_CMD=(docker compose exec -T "$DB_CONTAINER" mysql -u"$DB_USER" -p"$DB_PASSWORD" -D "$DB_NAME")

print_line() {
  printf '%*s\n' "${COLUMNS:-100}" '' | tr ' ' '-'
}

echo "DB snapshot"
echo "Container: $DB_CONTAINER"
echo "Database:  $DB_NAME"
echo "User:      $DB_USER"
print_line

# Read table names one per line (-N removes headers, -s silent).
# Avoid mapfile for compatibility with older bash versions (macOS default).
TABLES=()
while IFS= read -r table_name; do
  [[ -n "$table_name" ]] && TABLES+=("$table_name")
done < <("${MYSQL_CMD[@]}" -N -s -e "SHOW TABLES")

if [[ ${#TABLES[@]} -eq 0 ]]; then
  echo "No tables found in $DB_NAME"
  exit 0
fi

echo "Tables found (${#TABLES[@]}):"
printf ' - %s\n' "${TABLES[@]}"
print_line

for table in "${TABLES[@]}"; do
  echo
  echo "TABLE: $table"
  print_line

  row_count=$("${MYSQL_CMD[@]}" -N -s -e "SELECT COUNT(*) FROM \`$table\`")
  echo "Rows: $row_count"

  if [[ "$row_count" == "0" ]]; then
    echo "(empty table)"
    continue
  fi

  # -t prints an ASCII table with headers.
  "${MYSQL_CMD[@]}" -t -e "SELECT * FROM \`$table\`"
done

print_line
echo "Done."
