#!/usr/bin/env sh

eureka_container=galaxysandbox_discovery_1
consumer_container=galaxysandbox_event-consumer_1
generator_container=galaxysandbox_event-generator_1
datatier_container=galaxysandbox_data-tier_1

podman rm -f $eureka_container $consumer_container $generator_container $datatier_container
