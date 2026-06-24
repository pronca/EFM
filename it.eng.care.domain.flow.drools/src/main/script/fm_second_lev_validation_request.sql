create table FM_SEC_LEV_VALIDATION_REQUEST (
id varchar(128),
user_id varchar(128),
month_ref varchar(2),
year_ref varchar(4),
flow_id varchar(128),
status varchar(50),
processing_date timestamp,
creation_date timestamp
);


alter table FM_FLOW_RULE_FILE add RULE_TYPE VARCHAR(20);
update FM_FLOW_RULE_FILE set rule_type= (SELECT RULE_TYPE FROM fm_flow_rule WHERE FR_ID = FLOW_RULE_ID);
alter table fm_flow_rule drop column RULE_TYPE;
commit;