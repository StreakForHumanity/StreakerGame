# StreakerGame

## Description:

Up and down scrolling game similar to Frogger.

Obstacles come from top of screen to bottom.
Guards come from right and left of the screen.

Player can move any direction while screen is scrolling without exiting bounds of the screen.

## To Run the Game:

#### 1) Build via ant build.xml file in either command line or IDE of choice, e.g.
```
ant build
```

#### 2) from root game directory (StreakerGame), run:
```
java -cp /ABSOLUTE/PATH/TO/GAME_DIRECTORY/bin logic.client.Client
```
##### Note: replace absolute path with the correct location for your local system.

## Build Statuses:

### Travis CI
[![Build Status](https://travis-ci.org/brobinson15/StreakerGame.svg?)](https://travis-ci.org/brobinson15/StreakerGame)

### SonarCloud
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=streakergame)](https://sonarcloud.io/dashboard/index/streakergame:refactor)
[![LOC](https://sonarcloud.io/api/badges/measure?key=streakergame&metric=ncloc)](https://sonarcloud.io/dashboard/index/streakergame:master)
[![Code Smells](https://sonarcloud.io/api/badges/measure?key=streakergame&metric=code_smells)](https://sonarcloud.io/dashboard/index/streakergame)
