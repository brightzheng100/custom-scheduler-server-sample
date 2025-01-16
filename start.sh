#!/bin/bash

pushd server
mvn clean package
nohup bash -c "INSTANA_SERVICE_NAME=server java -jar target/server-1.0.0.jar" &> ../server.out & echo $! > ../server.pid
popd

pushd scheduler
# make sure it uses our "original" code, without any Instana instrumentation
cp src/main/java/com/instana/sample/HttpClientJob.java.plain src/main/java/com/instana/sample/HttpClientJob.java
mvn clean package
nohup bash -c "INSTANA_SERVICE_NAME=scheduler java -jar target/scheduler-1.0.0.jar" &> ../scheduler.out & echo $! > ../scheduler.pid
popd
