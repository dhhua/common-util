FROM java:8

RUN mkdir /work
WORKDIR /work
ADD run.sh /work/run.sh
ADD util-lock-1.0-SNAPSHOT.jar app.jar

CMD ["/bin/bash", "run.sh"]