FROM node:16-alpine AS build
WORKDIR /app
COPY /frontend/bookshelf/package*.json ./
COPY . .
RUN npm install \
&& npm run build

FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html