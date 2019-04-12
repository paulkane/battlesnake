# docker build -t battlesnake-server2 .
FROM alpine

COPY engine_0.2.23_Linux_x86_64.tar.gz /
# COPY snake-config.json /.snake-config.json

RUN apk add --no-cache tar && \
    tar --warning=no-unknown-keyword -zxf engine_0.2.23_Linux_x86_64.tar.gz && \
    rm -rf engine_0.2.23_Linux_x86_64.tar.gz

VOLUME /battlesnake

CMD ["./engine", "dev"]