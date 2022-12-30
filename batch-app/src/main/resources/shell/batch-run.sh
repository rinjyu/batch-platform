#!/bin/bash

echo "Batch Run!!!"
cd ../../../../
cd target

java -jar batch-0.0.1-SNAPSHOT.jar --spring.batch.job.enabled=true --job.name=$@