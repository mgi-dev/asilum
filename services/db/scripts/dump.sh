#!/bin/sh

mysqldump --single-transaction --skip-lock-tables -u$DB_USER -p$DB_PASSWORD --databases $DB_NAME | gzip > /db_backup/database_`date '+%m-%d-%Y'`.sql.gz

