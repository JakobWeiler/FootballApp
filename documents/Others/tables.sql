/*
DROP TABLE Player CASCADE CONSTRAINTS;
DROP TABLE Position CASCADE CONSTRAINTS;
DROP TABLE occupy CASCADE CONSTRAINTS;
DROP TABLE Statistic CASCADE CONSTRAINTS;
DROP TABLE Match CASCADE CONSTRAINTS;
DROP TABLE Participation CASCADE CONSTRAINTS;
*/

CREATE TABLE Player(
	id INTEGER,
	name VARCHAR2(15),
	password VARCHAR2(15),
	isAdmin INTEGER,
	wins INTEGER,
	tieds INTEGER,
	losses INTEGER,
	goalDifference INTEGER,
	
	CONSTRAINT pkPlayer PRIMARY KEY(id)
);

CREATE TABLE Position(
	position VARCHAR2(15),
  
  CONSTRAINT pkPosition PRIMARY KEY(position)
);

CREATE TABLE occupy(
	playerId INTEGER,
	positionName VARCHAR2(15),
	
	CONSTRAINT pkOccupyId PRIMARY KEY(playerId, positionName),
	CONSTRAINT fkPlayerId FOREIGN KEY(playerId) REFERENCES Player(id),
	CONSTRAINT fkPosName FOREIGN KEY(positionName) REFERENCES Position(position)
);

CREATE TABLE Statistic(
	id INTEGER,
	gDefault INTEGER,
	gHead INTEGER,
	gHeadSnow INTEGER,
	gNutmeg INTEGER,
	gOwn INTEGER,
	gPenalty INTEGER,
	positionName VARCHAR2(15),
	
	CONSTRAINT pkStatistic PRIMARY KEY(id),
	CONSTRAINT fkPosNameStatistic FOREIGN KEY(positionName) REFERENCES Position(position)
);

CREATE TABLE Match(
	id INTEGER,
	firmDate DATE,
	goalsA INTEGER,
	goalsB INTEGER,
	
	CONSTRAINT pkMatch PRIMARY KEY(id)
);

CREATE TABLE Participation(
	playerId INTEGER,
	matchID INTEGER,
	statisticId INTEGER,
	team VARCHAR2(1),
	
	CONSTRAINT pkParticipation PRIMARY KEY(playerId, matchID),
	CONSTRAINT fkStatistic FOREIGN KEY(statisticId) REFERENCES Statistic(id)
);