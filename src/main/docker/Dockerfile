FROM java:8
EXPOSE 8080
VOLUME /tmp
ADD dictionaries/* /var/lib/jgloss/dictionaries/
ADD jgloss-live-0.1.0.jar jgloss-live.jar
RUN bash -c 'touch /jgloss-live.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jgloss-live.jar","--edictPath=/var/lib/jgloss/dictionaries/edict2"]