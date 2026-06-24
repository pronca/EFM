alter table fm_flow_exporting_request add DRG NUMBER(1,0);
--inizializzazione
update fm_flow_exporting_request set DRG = 0;