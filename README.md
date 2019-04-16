# battlesnake

## application-properties

updated value `snake.name.prefix` to your prefix

## Strategy

Implement your own `MoveStrategy`

```java
public interface MoveStrategy {
    String getName();

    MOVE move(BattleSnakeRequest moveRequest);
}
```

## Run the application

The `Application` with `--enable-preview`

## Point battle snake to your application

make sure you name your snake the same as your Strategy name 

## Docker everything

There is a Dockerfile provided for the engine in 
`src/main/docker/`

### battle snake board

git clone https://github.com/battlesnakeio/board

change the `entrypoint.sh` for the nginx server to listen to `3009`

####Dockerfile
```dockerfile
FROM node:10.7.0-alpine AS build

COPY . .
RUN npm i
RUN npm run build

FROM nginx:1.15.2-alpine
RUN apk add --no-cache curl

COPY --from=build ./build/ /usr/share/nginx/html
COPY ./entrypoint.sh /bin/entrypoint.sh

ENTRYPOINT [ "/bin/entrypoint.sh" ]
```


