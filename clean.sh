#!/bin/bash

pushd server
mvn clean
popd

pushd scheduler
mvn clean
popd
