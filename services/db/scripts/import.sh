#!/bin/sh
mysql -u$DB_USER -p$DB_PASSWORD $DB_NAME < /dump/02-dump.sql