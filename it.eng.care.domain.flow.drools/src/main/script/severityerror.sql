create table fm_severity_error(
	SE_ID VARCHAR(20) primary key,
	WEIGHT INTEGER
);

INSERT INTO fm_severity_error VALUES('VALID',0);
INSERT INTO fm_severity_error VALUES('WARNING',1);
INSERT INTO fm_severity_error VALUES('ERROR',2);


COMMIT;
