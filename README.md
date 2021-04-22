# BowlingTest
#### _By JPN_
BowlingTest requires last version of [Maven](https://maven.apache.org/) to run.

## Installation

Download code from [this repository](https://github.com/jpnicotra/BowlingTest.git) and start command line in this project directory


Install maven dependencies
```sh
mvn clean install
```

Generate Javadoc
```sh
mvn javadoc:javadoc
```

## Execution with output as requested

```sh
TODO
mvn compile exec:java -Dexec.args="data\sample-moves.txt" -Dspring.main.banner-mode=off -Dspring.main.log-startup-info=false
```

## Execution with custom output more human readable

```sh
TODO
mvn compile exec:java -Dexec.args="data\sample-moves.txt PlayerInfoClearFormatter" -Dspring.main.banner-mode=off -Dspring.main.log-startup-info=false
```

## Features

- User choose between bowling round files (located in data directory)
- App executes all moves and alert user if some invalid movements where involved
- App shows final scoreboard

## Bowling Movements Example

```sh
Jeff	10
John	3
John	7
Jeff	7
Jeff	3
John	6
John	3
Jeff	9
Jeff	0
John	10
Jeff	10
John	8
John	1
Jeff	0
Jeff	8
John	10
Jeff	8
Jeff	2
John	10
Jeff	F
Jeff	6
John	9
John	0
Jeff	10
John	7
John	3
Jeff	10
John	4
John	4
Jeff	10
Jeff	8
Jeff	1
John	10
John	9
John	0
```

## Tech

BowlingTest uses a number of open source projects to work properly:

- [Spring-Boot] - version 2.4.5
- [Log4j2] - version 2.13
- [slf4j-api] - version 2.13
- [junit-jupiter] - version 5.7.1
- [JDK] - version 1.8

## Plugins

BowlingTest is currently extended with the following plugins.
Instructions on how to use them in your own application are linked below.

| Plugin | README |
| ------ | ------ |
| GitHub | [plugins/github/README.md][PlGh] |



## License

MIT
