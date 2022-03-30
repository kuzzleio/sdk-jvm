FROM node:14.18.2

COPY . /var/app
WORKDIR /var/app

RUN npm install

CMD [ "node /var/app/builder.js" ]