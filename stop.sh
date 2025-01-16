#!/bin/bash

kill -9 $(cat scheduler.pid)
kill -9 $(cat server.pid)

rm -f scheduler.out scheduler.pid server.out server.pid
