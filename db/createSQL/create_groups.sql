CREATE TABLE GROUPS (
  id INTEGER IDENTITY NOT NULL PRIMARY KEY,
  groupname varchar(255) NOT NULL,
  description varchar(255),
  creationDate TIMESTAMP,
  updateDate TIMESTAMP
)