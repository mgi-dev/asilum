FROM openjdk:8-jdk-alpine


ARG DB_NAME
ARG DB_USER
ARG DB_PASSWORD
ARG APP_NAME

ENV DB_NAME=$DB_NAME
ENV DB_USER=$DB_USER
ENV DB_PASSWORD=$DB_PASSWORD
ENV APP_NAME=$APP_NAME


WORKDIR /usr/$APP_NAME

COPY services/app/config/application.properties /application.properties
COPY services/app/scripts/launcher.sh /launcher.sh

RUN sed -i 's/APP_NAME/'$APP_NAME'/g' /launcher.sh

RUN sed -i 's/DB_NAME/'$DB_NAME'/g' /application.properties
RUN sed -i 's/DB_USER/'$DB_USER'/g' /application.properties
RUN sed -i 's/DB_PASSWORD/'$DB_PASSWORD'/g' /application.properties
RUN sed -i 's/APP_NAME/'$APP_NAME'/g' /application.properties

RUN chmod +x /launcher.sh

ENTRYPOINT ["sh", "/launcher.sh"]
#ENTRYPOINT ["./gradlew", "bootRun"]