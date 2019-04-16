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

The simplest way to get the engine and the board running is:

* open two terminals

### battlesnake engine
```
docker run --rm --name battlesnake-server -p3004:3004 -p3005:3005 -p3010:3010  battlesnakeio/engine engine dev
```
### battlesnake board

```
docker run --rm --name battlesnake-board -p3009:80  battlesnakeio/board
```

