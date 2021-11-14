FROM node:16-alpine
WORKDIR /opt/portfolio
RUN npm i -g serve
COPY . .
CMD ["serve", "-s", ".", "-l", "3000"]
