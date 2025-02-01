#!/usr/bin/env sh

kafka_dir=~/kafka/kafka_2.13-3.9.0

${kafka_dir}/bin/kafka-server-stop.sh &

## To sleep for 5 seconds: ##
sleep 5

${kafka_dir}/bin/zookeeper-server-stop.sh &

