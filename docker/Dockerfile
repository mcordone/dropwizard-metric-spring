#
# Start with a clean CentOS 7 image; update, and install tomcat. Install
# deltarpm before updating to reduce download sizes. Clean up caches so they
# don't bloat image sizes.
#
FROM docker.us-east-1.aws.aol.com/aopim/micro_base_img:4

ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
# increase default tomcat memory allocation
ENV JAVA_OPTS="-Xms512m -Xmx1536m"
RUN mkdir -p "$CATALINA_HOME"
WORKDIR $CATALINA_HOME

# see https://www.apache.org/dist/tomcat/tomcat-8/KEYS
#RUN gpg --keyserver pool.sks-keyservers.net --recv-keys \
RUN gpg --keyserver sks-keyservers.net --recv-keys \
    05AB33110949707C93A279E3D3EFE6B686867BA6 \
    07E48665A34DCAFAE522E5E6266191C37C037D42 \
    47309207D818FFD8DCD3F83F1931D684307A10A5 \
    541FBE7D8F78B25E055DDEE13C370389288584E7 \
    61B832AC2F1C5A90F0F9B00A1C506407564C17A3 \
    79F7026C690BAA50B92CD8B66A3AD3F4F22C4FED \
    9BA44C2621385CB966EBA586F72C284D731FABEE \
    A27677289986DB50844682F8ACB77FC2E86E29AC \
    A9C5DF4D22E99998D9875A5110C01C5A2F6059E7 \
    DCFD35E0BF8CA7344752DE8B6FB21E8933C60243 \
    F3A04C595DB5B6A5F1ECA43E3B7BBB100D811BBE \
    F7DA48BB64BCB84ECBA7EE6935CD23C10D498E23

ENV TOMCAT_MAJOR 8
ENV TOMCAT_VERSION 8.0.28
#ENV TOMCAT_TGZ_URL https://www.apache.org/dist/tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz
ENV TOMCAT_TGZ_URL https://archive.apache.org/dist/tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz

RUN set -x \
    && curl -fSL "$TOMCAT_TGZ_URL" -o tomcat.tar.gz \
    && curl -fSL "$TOMCAT_TGZ_URL.asc" -o tomcat.tar.gz.asc \
    && gpg --verify tomcat.tar.gz.asc \
    && tar -xvf tomcat.tar.gz --strip-components=1 \
    && rm bin/*.bat \
    && rm tomcat.tar.gz*

#
# Copy in our application
#

RUN mkdir -p /var/lib/application/logs
RUN rm -rf /usr/local/tomcat/webapps
RUN mkdir /usr/local/tomcat/webapps
ADD application /var/lib/application

RUN rm "$CATALINA_HOME/conf/server.xml"

#
# Copy in our configuration templates
#
ADD config /etc/tiller

#
# Expose port 8080 for tomcat.
#
EXPOSE 8080

#
# Set default command to configure application and run when container is run
#
CMD ["/usr/local/bin/tiller", "-v"]

