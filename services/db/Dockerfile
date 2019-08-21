FROM mysql:5.7.26

ENV MYSQL_ROOT_PASSWORD 'yes'

ARG DB_NAME
ARG DB_USER
ARG DB_PASSWORD
ARG USER_UID

ENV DB_NAME=$DB_NAME
ENV DB_USER=$DB_USER
ENV DB_PASSWORD=$DB_PASSWORD
ENV USER_UID=$USER_UID


COPY provisionning/01-databases.sql /docker-entrypoint-initdb.d/01-databases.sql


RUN sed -i 's/DB_NAME/'$DB_NAME'/g' /docker-entrypoint-initdb.d/01-databases.sql
RUN sed -i 's/DB_USER/'$DB_USER'/g' /docker-entrypoint-initdb.d/01-databases.sql
RUN sed -i 's/DB_PASSWORD/'$DB_PASSWORD'/g' /docker-entrypoint-initdb.d/01-databases.sql

COPY scripts/dump.sh /dump.sh
RUN sed -i 's/DB_NAME/'$DB_NAME'/g' /dump.sh
RUN sed -i 's/DB_USER/'$DB_USER'/g' /dump.sh
RUN sed -i 's/DB_PASSWORD/'$DB_PASSWORD'/g' /dump.sh

RUN echo "connect_timeout=604800" >> /etc/mysql/mysql.conf.d/mysqld.cnf

COPY scripts/dump.sh /usr/bin/dump.sh
RUN chmod +x /dump.sh # &&   \
#    chmod +x /usr/bin/import.sh
