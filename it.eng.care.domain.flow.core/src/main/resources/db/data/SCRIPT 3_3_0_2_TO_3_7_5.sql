/*
 * 3.3.0.2 -> 3.7.5
- NUOVE CONFIGURAZIONI
* CHECK_TIPO_TRASMISSIONE_PATH_{NOME FLUSSO} -> indica, se previsto, la prorietà json riportante il tipo di operazione di invio pratica da varticale (inserimento o cancellazione)
- ENABLE_CHECK_VARIATION -> se attivo, ignora le pratiche inviate dal verticale che non hanno variazioni rispetto alla pratica già presente su fm
- UPLOAD_RESULT_REG_{NOME FLUSSO} -> se il flusso non prevede il caricamento della validazione regionale allora imposta lo stato ACCETTATO
- tabgen FLXSDO_ERRORE, FLXSDO_STATO_TRAS, FLXSDO_STATO_OPERAZIONE: transcodifiche delle operazioni di trasmissione sdoxl alla regione
- FM_RECEIVER_LOG -> serve ad attivare la scrittura dei log in ricezione pratica da verticale
- 
 * 
 */

create table FM_FLOW_RECEIVER_LOG (
	ID VARCHAR2(100),
	FLOW_NAME VARCHAR2(100),
	VERSION_NAME VARCHAR2(100),
	OPERATION VARCHAR2(10),
	PK VARCHAR2(200),
	SENDING_DATE TIMESTAMP
);

alter table FM_JOB_TALEND_DEPENDENCIES add type varchar2(20);

ALTER TABLE FM_MONITOR_SDO_XL_FILE ADD id_estrazione varchar2(200);

alter table FM_MONITOR_SDO_XL_FILE drop column DATA_FILE;
alter table FM_MONITOR_SDO_XL_FILE add DATA_FILE BLOB;

