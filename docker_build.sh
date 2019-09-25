#!/bin/sh
BASEDIR=$(dirname "$0")
docker build --rm -t workcalendar -f "$BASEDIR/Dockerfile" .
