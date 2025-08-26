#!/bin/bash

NAME=sk082
IMAGE_NAME="myfirst-api-server"
VERSION="1.0.0"

DOCKER_REGISTRY="amdp-registry.skala-ai.com/skala25a"
DOCKER_REGISTRY_USER="robot\$skala25a"
DOCKER_REGISTRY_PASSWORD="1qB9cyusbNComZPHAdjNIFWinf52xaBJ"
DOCKER_CACHE="--no-cache"


# 1. Docker Resistry Login ( Option : Before launch this Script you should login to your account )
echo ${DOCKER_REGISTRY_PASSWORD} | docker login ${DOCKER_REGISTRY} \
	-u ${DOCKER_REGISTRY_USER}  --password-stdin \
   	|| { echo "Docker 로그인 실패"; exit 1; }

# 2. harbor 로 push 하기 위해 tag 추가
docker tag  ${NAME}-${IMAGE_NAME}:${VERSION} ${DOCKER_REGISTRY}/${NAME}-${IMAGE_NAME}:${VERSION}


# Docker 이미지 푸시
docker push ${DOCKER_REGISTRY}/${NAME}-${IMAGE_NAME}:${VERSION}
