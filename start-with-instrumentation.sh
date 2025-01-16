#!/bin/bash

pushd server
mvn clean package
nohup bash -c "INSTANA_SERVICE_NAME=server java -jar target/server-1.0.0.jar" &> ../server.out & echo $! > ../server.pid
popd

pushd scheduler
# Use Instana instrumented code
cp src/main/java/com/instana/sample/HttpClientJob.java.instrumented src/main/java/com/instana/sample/HttpClientJob.java
mvn clean package
nohup bash -c "INSTANA_SERVICE_NAME=scheduler java -jar target/scheduler-1.0.0.jar" &> ../scheduler.out & echo $! > ../scheduler.pid
popd
