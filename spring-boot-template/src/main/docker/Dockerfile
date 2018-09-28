FROM hzb-base-img:1.0

MAINTAINER guangxing zhengguangxing@huadata.com

RUN mkdir /usr/local/daiyoudao-files

ADD wd-daiyoudao-admin.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

WORKDIR /usr/local/tomcat/bin

CMD [ "catalina.sh", "run" ]