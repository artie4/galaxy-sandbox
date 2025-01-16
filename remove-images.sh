#!/usr/bin/env sh

eureka_image=localhost/galaxy-sandbox/eureka-server
generator_image=localhost/galaxy-sandbox/event-generator
consumer_image=localhost/galaxy-sandbox/event-consumer
datatier_image=localhost/galaxy-sandbox/data-tier

podman rmi $eureka_image $generator_image $consumer_image $datatier_image
