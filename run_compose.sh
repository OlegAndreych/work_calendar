#!/bin/sh
BASEDIR=$(dirname "$0")
docker-compose -f "$BASEDIR/docker/compose.yml" run --rm workcalendar