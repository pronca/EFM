CREATE TABLE FM_ANAGRAFICA_ASSISTITO
   (    "ID" VARCHAR2(255 BYTE),
    "NOME" VARCHAR2(255 BYTE),
    "COGNOME" VARCHAR2(255 BYTE),
    "DATANASCITA" DATE,
    "COMUNENASCITA" VARCHAR2(255 BYTE),
    "SESSO" VARCHAR2(255 BYTE),
    "CODICEFISCALE" VARCHAR2(255 BYTE),
    "CODICEPAZIENTE" VARCHAR2(255 BYTE),
    "COMUNERESIDENZA" VARCHAR2(255 BYTE),
    "NAZIONALITA" VARCHAR2(255 BYTE),
    "ABILITAZIONE" NUMBER(1,0),
    "ASLRESIDENZA" VARCHAR2(255 BYTE)
   );
   
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_CONFIGURATION','flowWithExternalUpdate','lista di flussi che prevedono caricamento parallelo da enti eterni','ASA(ASA_EXT)',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,to_date('01-01-1900','DD-MM-YYYY'),to_date('01-01-2100','DD-MM-YYYY'),null,to_date('16-07-2021','DD-MM-YYYY'),'71c9face-0871-4344-9414-672172f0864b',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);

update fm_tabgen_value set field3 = 'DEFAULT=yyyy-MM-dd"/ASA_EXT=ddMMyyyy' where tabgen_id = 'FM_CONFIGURATION' and field1 = 'upload_file_date_parser';
update fm_tabgen_value set field3 = 'DEFAULT-10([0-9]{4}-[0-9]{2}-[0-9]{2})/ASA_EXT-8([0-9]{2}[0-9]{2}[0-9]{4})' where tabgen_id = 'FM_CONFIGURATION' and field1 = 'upload_file_date_format';

-- Parametro per TO4 (non crea problemi su altri clienti)
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_CONFIGURATION','flowToCopy','lista di flussi da copiare in ricezione','SICHER(SICHER2)',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,to_date('01-01-1900','DD-MM-YYYY'),to_date('01-01-2100','DD-MM-YYYY'),null,to_date('02-09-2021','DD-MM-YYYY'),'ad1399fd-87d3-4dd0-a14a-32731925f858',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
--

REM INSERTING into FM_TABGEN_VALUE
SET DEFINE OFF;
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0073','62dd1877-9ec5-400f-bd59-4d84b0a5e380','RICAVI_TOT_RIC_REG','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(SUM(to_number(replace(regexp_replace(nvl(nvl(importo,importo),0), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))), 0) v
        FROM
            fm_flow_asa_ext_1 p
            JOIN fm_flow_asa_ext_0 q ON p.codiceazienda = q.codiceazienda
                                    AND p.codicecontatto = q.codicecontatto
                                    AND p.strutturaerogatrice = q.strutturaerogatrice
        WHERE
            q.month_rif = to_number(z.mese)
            AND q.year_rif = to_number(z.anno)
            AND q.state_send_region = ''ACCETTATA''
            AND q.status_region in (''VALIDO'',''SEGNALAZIONE'')
            AND q.month_rif IN (
                :mesi
            )
            AND q.year_rif IN (
                :anni
            )
    ) tot
FROM
    dashboardmeseanno z
WHERE
    z.mese IN (
        :mesi
    )
    AND z.anno IN (
        :anni
    )
ORDER BY
    z.mese,
    z.anno','Ricavi Totali Riconosciute Da Regione','card-header-primary','attach_money','Ricavi totali regionali','TRUE','REGIONALE',null,'Somma degli importi delle pratiche con stato di invio a regione = ACCETTATA e risultanti VALIDE o con SEGNALAZIONI a seguito della validazione da parte della regione',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'ae10bbeb-d254-45d2-b194-242446437cc5',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0061','62dd1877-9ec5-400f-bd59-4d84b0a5e380','ANAGRAFICHE_ERRATE','SELECT    mese,    anno,    nvl(cc, 0)FROM    dashboardmeseanno left    JOIN (        SELECT            month_rif,            year_rif,            COUNT(1) cc        FROM            FM_FLOW_ASA_EXT_0        WHERE            status = ''ERROR''  and month_rif in (:mesi)            and year_rif in (:anni)        GROUP BY            month_rif,            year_rif    ) ON mese = month_rif         AND anno = year_rif         where mese in (:mesi)            and anno in (:anni)','Anagrafiche Errate',null,null,null,null,null,null,'PROVA TOOLTIP',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'da96ce67-c8ad-4440-ba85-228ec7cec43b',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0062','62dd1877-9ec5-400f-bd59-4d84b0a5e380','SCARTO_ANAGRAFICHE','SELECT     z.mese,     z.anno,     (         SELECT             nvl(SUM(to_number(replace(regexp_replace(nvl(importo, ''0''), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))), ''0'')         FROM             FM_FLOW_ASA_EXT_0 p             JOIN FM_FLOW_ASA_EXT_1 q ON p.codiceazienda = q.codiceazienda                                        AND p.strutturaerogatrice = q.strutturaerogatrice                                        AND p.codicecontatto = q.codicecontatto         WHERE             p.status = ''ERROR''             AND p.month_rif = to_number(z.mese)             AND p.year_rif = to_number(z.anno)             AND p.month_rif IN (                 :mesi             )             AND p.year_rif IN (                 :anni             )     ) tot from dashboardmeseanno z where z.mese in (:mesi) and z.anno in ( :anni )','Scarto Anagrafiche',null,null,null,null,null,null,'PROVA TOOLTIP',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'b5dcf845-890b-4616-a0bf-5c1137367887',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0063','62dd1877-9ec5-400f-bd59-4d84b0a5e380','SCARTO_DATI_CLINICI','SELECT    mese,    anno,    nvl(v, 0)FROM    dashboardmeseanno left    JOIN (        SELECT            month_rif,            year_rif,            SUM(to_number(replace(regexp_replace(nvl(importo, ''0''), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))) v        FROM            FM_FLOW_ASA_EXT_1 p        WHERE            status  = ''ERROR''   and month_rif in (:mesi)            and year_rif in (:anni)                   GROUP BY            month_rif,            year_rif    ) ON mese = month_rif         AND anno = year_rif         where mese in (:mesi)         and anno in (:anni)ORDER BY    to_number(anno),    to_number(mese)','Scarto Dati Clinici',null,null,null,null,null,null,'PROVA TOOLTIP',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'17366f9f-1ed9-439d-bc64-743c954fdc46',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0064','62dd1877-9ec5-400f-bd59-4d84b0a5e380','RICAVI_TOTALI','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(SUM(to_number(replace(regexp_replace(nvl(nvl(importo,importo),0), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))), 0) v
        FROM
            FM_FLOW_ASA_EXT_1 p
        WHERE
            p.status_group in (''VALID'',''WARNING'')
            AND p.month_rif = to_number(z.mese)
            AND p.year_rif = to_number(z.anno)
            and p.month_rif in (:mesi)
            and p.year_rif in (:anni)
    ) tot
FROM
    dashboardmeseanno z
    where z.mese in (:mesi)
    and z.anno in (:anni)','Ricavi Totali Pratiche non Errate','card-header-primary','attach_money','Ricavi totali','FALSE','AZIENDALE',null,'Somma degli importi delle pratiche risultanti VALIDE e che in seguito alla validazione da parte del FlowManager presentano WARNING.',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'486e2c3a-d3f8-4f8a-b254-4c26bf773f51',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0065','62dd1877-9ec5-400f-bd59-4d84b0a5e380','DATI_CLINICI_ERRATI','SELECT    z.mese,    z.anno,    nvl(v, 0)FROM    dashboardmeseanno z    LEFT JOIN (        SELECT            p.month_rif,            p.year_rif,            COUNT(1) v        FROM            FM_FLOW_ASA_EXT_1 p            WHERE            p.status=   ''ERROR''          and p.month_rif in (:mesi)            and p.year_rif in (:anni)        GROUP BY            p.month_rif,            p.year_rif    ) c ON z.mese = month_rif           AND z.anno = year_rif           where z.mese in (:mesi)           and z.anno in (:anni)ORDER BY    to_number(z.anno),    to_number(z.mese)','Dati Clinici Errati',null,null,null,null,null,null,'PROVA TOOLTIP',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'5cb375d9-812b-4d45-8408-136e328df11d',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0067','62dd1877-9ec5-400f-bd59-4d84b0a5e380','PRATICHE','SELECT
    mese,
    anno,
    nvl(c, 0)
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            COUNT(1) c,
            month_rif,
            year_rif
        FROM
            FM_FLOW_ASA_EXT_0
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
            and month_rif in (:mesi)
            and year_rif in (:anni)
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
           where mese in (:mesi)
and anno in (:anni)
ORDER BY
    to_number(anno),
    to_number(mese)','Pratiche',null,null,null,null,null,null,'Pratiche ricevute dal verticale',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'45c34ce6-6b8a-4e0a-8851-79cfa747ee59',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0080','62dd1877-9ec5-400f-bd59-4d84b0a5e380','PRATICHE_SEGNALAZIONI_REG','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(COUNT(1), 0)
        FROM
            fm_flow_asa_ext_0 b
        WHERE
            b.status_region = ''SEGNALAZIONE''
            AND b.month_rif = to_number(z.mese)
            AND b.year_rif = to_number(z.anno)
            AND b.state_send_region = ''ACCETTATA''
            AND b.month_rif IN (
                :mesi
            )
            AND b.year_rif IN (
                :anni
            )
    )
FROM
    dashboardmeseanno z
WHERE
    z.mese IN (
        :mesi
    )
    AND z.anno IN (
        :anni
    )','Pratiche Segnalazioni Da Regione','card-header-warning','warning','Visualizza Pratiche Segnalazioni da Regione','TRUE','REGIONALE',null,'Pratiche con stato di invio a regione = ACCETTATA e con SEGNALAZIONI a seguito della validazione da parte della regione',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'02593be4-3cc5-4e07-a901-b796b54f0fa0',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0069','62dd1877-9ec5-400f-bd59-4d84b0a5e380','ERRORI_DISTINCT','SELECT
    mese,
    anno,
    SUM(nvl(mes, 0))
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            COUNT(distinct b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            FM_FLOW_ASA_EXT_0 a
            LEFT JOIN FM_FLOW_ASA_EXT_0_message b ON ( a.codiceazienda = b.codiceazienda AND a.strutturaerogatrice = b.strutturaerogatrice AND a.codicecontatto = b.codicecontatto )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
            and a.month_rif in (:mesi)
and a.year_rif in (:anni)
AND B.SEVERITY = ''ERROR''
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(distinct b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            FM_FLOW_ASA_EXT_1 a
            LEFT JOIN FM_FLOW_ASA_EXT_1_message b ON ( a.codiceazienda = b.codiceazienda AND a.strutturaerogatrice = b.strutturaerogatrice AND a.codicecontatto = b.codicecontatto )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
            and a.month_rif in (:mesi)
and a.year_rif in (:anni)
AND B.SEVERITY = ''ERROR''
        GROUP BY
            a.year_rif,
            a.month_rif

    ) q ON mese = month_rif
           AND anno = year_rif
           where mese in (:mesi)
and anno in (:anni)
GROUP BY
    mese,
    anno
ORDER BY
    to_number(anno),
    to_number(mese)','Errori Distinct',null,null,null,null,null,null,'PROVA TOOLTIP',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'064c5fb8-c50b-43a8-b6e7-13d729e2a9c9',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0078','62dd1877-9ec5-400f-bd59-4d84b0a5e380','SEGNALAZIONI_REG','SELECT
    mese,
    anno,
    nvl(c, 0)
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            COUNT(1) c,
            month_rif,
            year_rif
        FROM
            fm_flow_asa_ext_0
            JOIN asa_reg_scarti_regione ON asa_reg_scarti_regione.codiceazienda = fm_flow_asa_ext_0.codiceazienda
                                           AND asa_reg_scarti_regione.strutturaerogatrice = fm_flow_asa_ext_0.strutturaerogatrice
                                           AND fm_flow_asa_ext_0.codicecontatto IN (
                SELECT
                    codicecontatto
                FROM
                    fm_flow_asa_reg_0 reg
                WHERE
                    reg.codicecontatto = asa_reg_scarti_regione.codicecontatto
                    AND reg.codiceazienda = asa_reg_scarti_regione.codiceazienda
                    AND reg.strutturaerogatrice = asa_reg_scarti_regione.strutturaerogatrice
            )
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
            AND month_rif IN (
                :mesi
            )
            AND year_rif IN (
                :anni
            )
            AND severity = ''SEGNALAZIONE''
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
WHERE
    mese IN (
        :mesi
    )
    AND anno IN (
        :anni
    )
ORDER BY
    to_number(anno),
    to_number(mese)','Segnalazioni Regionali','card-header-warning','warning','Visualizza Segnalazioni Regionali','TRUE','REGIONALE','ERRORI','SEGNALAZIONI presenti nelle pratiche in seguito alla validazione da parte della regione',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'d295a393-a2bd-4292-8649-0ddd52658873',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0066','62dd1877-9ec5-400f-bd59-4d84b0a5e380','PRATICHE_ERRATE','SELECT     z.mese,     z.anno,     (         SELECT             nvl(COUNT(1), 0)         FROM             FM_FLOW_ASA_EXT_0 b         WHERE             b.status_group = ''ERROR''             AND b.month_rif = to_number(z.mese)             AND b.year_rif = to_number(z.anno)             AND b.month_rif IN (                 :mesi             )             AND b.year_rif IN (                 :anni             )     ) FROM     dashboardmeseanno z where z.mese IN (     :mesi ) AND z.anno IN (     :anni )','Pratiche Errate',null,null,null,null,null,null,'Pratiche ricevute dal verticale che, a seguito della validazione interna da parte del FlowManager, presentano ERRORI',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'0fc6b5f1-4013-47e7-a59b-2fda6158dac7',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0070','62dd1877-9ec5-400f-bd59-4d84b0a5e380','PRATICHE_ERRATE_REG','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(COUNT(1), 0)
        FROM
            fm_flow_asa_ext_0 b
        WHERE
            b.status_region = ''SCARTO''
            AND b.month_rif = to_number(z.mese)
            AND b.year_rif = to_number(z.anno)
            AND b.state_send_region = ''ACCETTATA''
            AND b.month_rif IN (
                :mesi
            )
            AND b.year_rif IN (
                :anni
            )
    )
FROM
    dashboardmeseanno z
WHERE
    z.mese IN (
        :mesi
    )
    AND z.anno IN (
        :anni
    )','Pratiche Errate Da Regione','card-header-danger','error','Visualizza pratiche errate da regione','TRUE','REGIONALE','PRATICHE_ERR','Pratiche con stato di invio a regione = ACCETTATA e con SCARTI a seguito della validazione da parte della regione',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'e5306d42-5388-4b79-8552-9d159b280c6f',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0068','62dd1877-9ec5-400f-bd59-4d84b0a5e380','ERRORI','SELECT
    mese,
    anno,
    SUM(nvl(mes, 0))
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            COUNT(b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            FM_FLOW_ASA_EXT_0 a
            LEFT JOIN FM_FLOW_ASA_EXT_0_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.strutturaerogatrice = b.strutturaerogatrice
                                                     AND a.codicecontatto = b.codicecontatto )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
            and a.month_rif in (:mesi)
and a.year_rif in (:anni)
and b.severity = ''ERROR''
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            FM_FLOW_ASA_EXT_1 a
            LEFT JOIN FM_FLOW_ASA_EXT_1_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.strutturaerogatrice = b.strutturaerogatrice
                                                     AND a.codicecontatto = b.codicecontatto )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
            and a.month_rif in (:mesi)
and a.year_rif in (:anni)
and b.severity = ''ERROR''
        GROUP BY
            a.year_rif,
            a.month_rif

    ) q ON mese = month_rif
           AND anno = year_rif
           where mese in (:mesi)
and anno in (:anni)
GROUP BY
    mese,
    anno
ORDER BY
    to_number(anno),
    to_number(mese)','Errori',null,null,null,null,null,null,'ERRORI presenti nelle pratiche in seguito alla validazione da parte del FlowManager',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'d20a641f-e0a2-4c5a-a721-ba0ba698de91',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0075','62dd1877-9ec5-400f-bd59-4d84b0a5e380','ERRORI_REG','SELECT
    mese,
    anno,
    nvl(c, 0)
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            COUNT(1) c,
            month_rif,
            year_rif
        FROM
            fm_flow_asa_ext_0
            JOIN asa_reg_scarti_regione ON asa_reg_scarti_regione.codiceazienda = fm_flow_asa_ext_0.codiceazienda
                                           AND asa_reg_scarti_regione.strutturaerogatrice = fm_flow_asa_ext_0.strutturaerogatrice
                                           AND fm_flow_asa_ext_0.codicecontatto IN (
                SELECT
                    codicecontatto
                FROM
                    fm_flow_asa_reg_0 reg
                WHERE
                    reg.codicecontatto = asa_reg_scarti_regione.codicecontatto
                    AND reg.codiceazienda = asa_reg_scarti_regione.codiceazienda
                    AND reg.strutturaerogatrice = asa_reg_scarti_regione.strutturaerogatrice
            )
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
            AND month_rif IN (
                :mesi
            )
            AND year_rif IN (
                :anni
            )
            AND severity = ''SCARTO''
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
WHERE
    mese IN (
        :mesi
    )
    AND anno IN (
        :anni
    )
ORDER BY
    to_number(anno),
    to_number(mese)','Errori Regionali','card-header-danger','error','Visualizza errori regionali','TRUE','REGIONALE','ERRORI','ERRORI presenti nelle pratiche in seguito alla validazione da parte della regione',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'9e3c32c4-86c3-4c3c-a162-23e45d128cbc',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0077','62dd1877-9ec5-400f-bd59-4d84b0a5e380','WARNING','SELECT mese, anno, SUM(nvl(mes, 0)) FROM dashboardmeseanno left JOIN (SELECT COUNT(b.message) mes,a.month_rif,a.year_rif FROM FM_FLOW_ASA_EXT_0 a LEFT JOIN FM_FLOW_ASA_EXT_0_message b ON ( a.codiceazienda = b.codiceazienda AND a.strutturaerogatrice = b.strutturaerogatrice AND a.codicecontatto = b.codicecontatto )WHERE
a.month_rif IS NOT NULL AND a.year_rif IS NOT NULL and a.month_rif in (:mesi) and a.year_rif in (:anni) and b.severity = ''WARNING'' GROUP BY a.year_rif, a.month_rif UNION ALL SELECT COUNT(b.message) mes, a.month_rif, a.year_rif FROM
FM_FLOW_ASA_EXT_1 a LEFT JOIN FM_FLOW_ASA_EXT_1_message b ON ( a.codiceazienda = b.codiceazienda AND a.strutturaerogatrice = b.strutturaerogatrice AND a.codicecontatto = b.codicecontatto )
WHERE a.month_rif IS NOT NULL AND a.year_rif IS NOT NULL and a.month_rif in (:mesi) and a.year_rif in (:anni) and b.severity = ''WARNING'' GROUP BY
a.year_rif,
a.month_rif
) q ON mese = month_rif
AND anno = year_rif
where mese in (:mesi)
and anno in (:anni)
GROUP BY mese, anno ORDER BY to_number(anno),to_number(mese)','Warning','card-header-warning','warning','Visualizza Warning','TRUE','AZIENDALE',null,'WARNING presenti nelle pratiche in seguito alla validazione da parte del FlowManager',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'20a873b1-5dc5-43f0-8ff6-327d04287db6',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0079','62dd1877-9ec5-400f-bd59-4d84b0a5e380','PRATICHE_WARNING','SELECT     z.mese,     z.anno,     (         SELECT             nvl(COUNT(1), 0)         FROM             FM_FLOW_ASA_EXT_0 b         WHERE             b.status_group = ''WARNING''             AND b.month_rif = to_number(z.mese)             AND b.year_rif = to_number(z.anno)             AND b.month_rif IN (                 :mesi             )             AND b.year_rif IN (                 :anni             )     ) FROM     dashboardmeseanno z WHERE     z.mese IN (         :mesi     )     AND z.anno IN (         :anni     )','Pratiche Warning','card-header-warning','warning','Visualizza Pratiche Warning','TRUE','AZIENDALE',null,'Pratiche ricevute dal verticale che, a seguito della validazione interna da parte del FlowManager, presentano WARNING',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'8fb8c140-b14a-4fe0-bc9a-7c944819d5cc',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0076','62dd1877-9ec5-400f-bd59-4d84b0a5e380','PAZIENTI_DIMESSI',null,'Pazienti Dimessi','card-header-info','person','Numero Pazienti Dimessi','FALSE','AZIENDALE',null,'Pazienti che OPM ha dimesso',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'0d831450-10e6-49b7-9f81-867f52392f52',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0074','62dd1877-9ec5-400f-bd59-4d84b0a5e380','PRATICHE_NOT_SEND_REG','SELECT
    mese,
    anno,
    nvl(c, 0)
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            COUNT(1) c,
            month_rif,
            year_rif
        FROM
            fm_flow_asa_ext_0
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
            AND ( state_send_region = ''DA_INVIARE''
                  AND month_rif IN (
                :mesi
            )
                  AND year_rif IN (
                :anni
            ) )
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
WHERE
    mese IN (
        :mesi
    )
    AND anno IN (
        :anni
    )
ORDER BY
    to_number(anno),
    to_number(mese)','Pratiche ancora non inviate a regione','card-header-rose','event_note','Visualizza pratiche ancora non inviate a regione','TRUE','REGIONALE','PRATICHE','Pratiche presenti in Flow Manager e che devono ancora essere inviate a Regione (stato di invio a regione = DA_INVIARE)',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'3170ed2c-acfc-48be-b526-b4619b229178',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0071','62dd1877-9ec5-400f-bd59-4d84b0a5e380','PRATICHE_REG','SELECT
    mese,
    anno,
    nvl(c, 0)
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            COUNT(1) c,
            month_rif,
            year_rif
        FROM
            fm_flow_asa_ext_0
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
            AND (state_send_region = ''INVIATA''
            OR state_send_region = ''ACCETTATA'')
            and month_rif in (:mesi)
and year_rif in (:anni)
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
           where mese in (:mesi)
and anno in (:anni)
ORDER BY
    to_number(anno),
    to_number(mese)','Pratiche Inviate a Regione','card-header-info','arrow_forward_ios','Visualizza pratiche inviate a regione','TRUE','REGIONALE','PRATICHE','Pratiche inviate a Regione ed eventualmente anche già accettate (stato di invio a regione = INVIATA o ACCETTATA)',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'5e5a926d-a13f-4da2-8777-9d538cb6661b',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into FM_TABGEN_VALUE (TABGEN_ID,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5,FIELD6,FIELD7,FIELD8,FIELD9,FIELD10,FIELD11,FIELD12,FIELD13,FIELD14,FIELD15,FIELD16,FIELD17,FIELD18,FIELD19,FIELD20,DT_ENABLE,DT_DISABLE,ALWAYS_ENABLED,OPERATION_DATE,TV_ID,FIELD21,FIELD22,FIELD23,FIELD24,FIELD25,FIELD26,FIELD27,FIELD28,FIELD29,FIELD30,FIELD31,FIELD32,FIELD33,FIELD34,FIELD35,FIELD40,FIELD36,FIELD37,FIELD38,FIELD39) values ('FM_DASHBOARD_CONFIG','0072','62dd1877-9ec5-400f-bd59-4d84b0a5e380','PRATICHE_RIC_REG','SELECT
    mese,
    anno,
    nvl(c, 0)
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            COUNT(1) c,
            month_rif,
            year_rif
        FROM
            fm_flow_asa_ext_0
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
            AND state_send_region = ''ACCETTATA''
            AND status_region in ( ''VALIDO''
,''SEGNALAZIONE'')
            and month_rif in (:mesi)
and year_rif in (:anni)
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
           where mese in (:mesi)
and anno in (:anni)
ORDER BY
    to_number(anno),
    to_number(mese)','Pratiche Riconosciute da Regione','card-header-success','grade','Visualizza pratiche riconosciute da regione','TRUE','REGIONALE','PRATICHE','Pratiche riconosciute da regione (stato di invio a regione = ACCETTATA e risultanti VALIDE o con SEGNALAZIONI a seguito della validazione da parte della regione)',null,null,null,null,null,null,null,null,to_date('01-01-2000','DD-MM-YYYY'),to_date('01-01-2000','DD-MM-YYYY'),null,to_date('28-10-2020','DD-MM-YYYY'),'4c05215a-05be-4844-994a-d9b2d18fbfa3',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);


COMMIT;