CREATE TABLE USERGROUP(
userId INTEGER, 
groupId INTEGER, 
FOREIGN KEY (userId) REFERENCES USERS(ID), 
FOREIGN KEY (groupId) REFERENCES GROUPS(ID));