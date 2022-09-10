#!/usr/bin/env sh

kafka_dir=~/kafka_2.13-2.7.0

${kafka_dir}/bin/zookeeper-server-start.sh ${kafka_dir}/config/zookeeper.properties &

## To sleep for 5 seconds: ##
sleep 5

${kafka_dir}/bin/kafka-server-start.sh ${kafka_dir}/config/server.properties &
