--SHUTDOWN
--
--SHUTDOWN [IMMEDIATELY | COMPACT | SCRIPT[2]];
--Closes the current database.
--
--Varieties of the SHUTDOWN command
--
--SHUTDOWN
--Performs a checkpoint to creates a new .script file that has the minimum size and contains the data for memory tables only. It then backs up the .data file containing the CACHED TABLE data in zipped format to the .backup file and closes the database.
--
--SHUTDOWN IMMEDIATELY
--Just closes the database files (like when the Java process for the database is terminated); this command is used in tests of the recovery mechanism. This command should not be used as the routine method of closing the database.
--
--SHUTDOWN COMPACT
--Writes out a new .script file which contains the data for all the tables, including CACHED and TEXT tables. It then deletes the existing text table files and the .data file before rewriting them. After this, it backs up the .data file in the same way as normal SHUTDOWN. This operation shrinks all files to the minimum size.
--
--SHUTDOWN SCRIPT
--Similar to SHUTDOWN COMPACT but after writing the script and deleting the existing files, it does not rewrite the .data and text table files. After SHUTDOWN SCRIPT, only the .script and .properties file remain. At the next startup, these files are processed and the .data and .backup files are created. This command in effect performs part of the job of SHUTDOWN COMPACT, leaving the other part to be performed automatically at the next startup.
--
--This command produces a full script of the database which can be edited for special purposes prior to the next startup.
--
--Only an administrator may use the SHUTDOWN command.

SHUTDOWN COMPACT