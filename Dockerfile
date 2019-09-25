FROM adoptopenjdk:11-jdk-hotspot

RUN groupadd -g 1000 runner && useradd -m -g 1000 runner

RUN mkdir /opt/app

COPY ./docker/run.sh /opt/app
RUN chmod +x /opt/app/run.sh

RUN chown -R runner /opt/app
USER runner

CMD ["/opt/app/run.sh"]

COPY ./build/libs/work-calendar-1.0-SNAPSHOT.jar /opt/app
