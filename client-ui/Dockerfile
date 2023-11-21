FROM nginx:alpine

RUN apk update && apk --no-cache upgrade

COPY docker/nginx.conf /etc/nginx/conf
COPY /dist/microservice-clientui /usr/share/nginx/html
