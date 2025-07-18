FROM node:22-alpine

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm install -g @angular/cli@19.2.0 && \
    npm install

COPY . .

EXPOSE 4200

CMD ["ng", "serve", "--open", "--host", "0.0.0.0", "--port", "4200", "--poll", "1000"]