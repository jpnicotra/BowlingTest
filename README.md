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

## Execution

```sh
TODO
mvn compile exec:java -Dexec.args="data\sample-moves.txt"
```

## Features

TODO
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

- [Spring-Context] - version 5.3.4
- [Spring-Test] - version 5.3.4
- [slf4j-api] - version 1.7.30
- [jUnit] - version 4.13.2
- [JDK] - version 1.8

## Plugins

BowlingTest is currently extended with the following plugins.
Instructions on how to use them in your own application are linked below.

| Plugin | README |
| ------ | ------ |
| GitHub | [plugins/github/README.md][PlGh] |



## License

MIT
