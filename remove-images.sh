#!/usr/bin/env sh

eureka_image=galaxy-sandbox/eureka-server
generator_image=galaxy-sandbox/event-generator
consumer_image=galaxy-sandbox/event-consumer

podman rmi $eureka_image $generator_image $consumer_image
