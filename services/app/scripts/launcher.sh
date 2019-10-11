#/bin/sh
cd /
cp application.properties /usr/APP_NAME/src/main/resources/
cd /usr/APP_NAME
chmod 777 gradlew
./gradlew bootRun
