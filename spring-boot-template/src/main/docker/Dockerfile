FROM base-img:1.0

MAINTAINER your_name your_email

RUN mkdir /usr/local/template-files

ADD template.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

WORKDIR /usr/local/tomcat/bin

CMD [ "catalina.sh", "run" ]