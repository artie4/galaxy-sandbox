#!/usr/bin/env sh

for module in 'eureka-server' 'event-consumer' 'event-generator'
do
  echo ${module} &&
  gradle :${module}:build &&
  cd ./${module} && podman build -t galaxy-sandbox/${module} . && cd -;
done

exit
