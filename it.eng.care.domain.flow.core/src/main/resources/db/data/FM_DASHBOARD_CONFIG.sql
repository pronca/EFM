ALTER TABLE HC40_FLOW_MANAGER.FM_TABGEN_VALUE
MODIFY(FIELD4 VARCHAR2(4000 BYTE));
DELETE FROM FM_DASHBOARD_CONFIG;
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values ('business','REGIONALE','PRATICHE_REG','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
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
            fm_flow_sdoior_0
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
            AND (state_send_region = ''INVIATA''
            OR state_send_region = ''ACCETTATA'')
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
ORDER BY
    to_number(anno),
    to_number(mese)','card-header-rose','Pratiche Inviate a Regione','PRATICHE','0021','FOOOTER','TRUE',to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'cab05b54-a46f-4da6-b4af-3cde7aa0d2a0','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values ('business','REGIONALE','PRATICHE_RIC_REG','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
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
            fm_flow_sdoior_0
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
            AND state_send_region = ''ACCETTATA''
            AND status_region = ''VALID''
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
ORDER BY
    to_number(anno),
    to_number(mese)','card-header-rose','Pratiche Riconosciute da Regione','PRATICHE','0022','FOOOTER','TRUE',to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'25756176-3508-4ab0-b6ee-d37634aea79f','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values ('business','REGIONALE','PRATICHE_ERRATE_REG','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(COUNT(1), 0)
        FROM
            fm_flow_ps_er_0 b
        WHERE
            b.status_region in (''WARNING'', ''ERROR'')
            AND b.month_rif = to_number(z.mese)
            AND b.year_rif = to_number(z.anno)
            AND b.state_send_region = ''ACCETTATA''
    )
FROM
    dashboardmeseanno z','card-header-rose','Pratiche Errate Da Regione','PRATICHE_ERR','0023','FOOOTER','TRUE',to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'5c92cb85-9e23-4048-92e6-b45661618e6a','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values ('business','REGIONALE','PRATICHE_REG','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
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
            fm_flow_ps_er_0
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
            AND (state_send_region = ''INVIATA''
            OR state_send_region = ''ACCETTATA'')
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
ORDER BY
    to_number(anno),
    to_number(mese)','card-header-rose','Pratiche Inviate a Regione','PRATICHE','0024','FOOOTER','TRUE',to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'25eb6b9a-5358-428b-9839-7bd6639f06d8','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values ('business','REGIONALE','PRATICHE_RIC_REG','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
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
            fm_flow_ps_er_0
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
            AND state_send_region = ''ACCETTATA''
            AND status_region = ''VALID''
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
ORDER BY
    to_number(anno),
    to_number(mese)','card-header-rose','Pratiche Riconosciute da Regione','PRATICHE','0025','FOOOTER','TRUE',to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'486938bc-a7f6-4648-ba3d-a91d52277de0','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'RICAVI_TOTALI','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(SUM(to_number(replace(regexp_replace(nvl(importolordo, ''0''), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))), ''0'') v
        FROM
            fm_flow_ps_er_1 p
        WHERE
            p.status_group = ''VALID''
            AND p.month_rif = to_number(z.mese)
            AND p.year_rif = to_number(z.anno)
    ) tot
FROM
    dashboardmeseanno z',null,'Ricavi Totali',null,'0018',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'059f8384-da24-46ee-a083-b98b249d1e97','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'DATI_CLINICI_ERRATI','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
    z.mese,
    z.anno,
    nvl(v, 0)
FROM
    dashboardmeseanno z
    LEFT JOIN (
        SELECT
            p.month_rif,
            p.year_rif,
            COUNT(1) v
        FROM
            fm_flow_ps_er_1 p
            LEFT JOIN fm_flow_ps_er_2 q ON p.codiceazienda = q.codiceazienda
                                           AND p.codicestruttura = q.codicestruttura
                                           AND p.codiceaccessops = q.codiceaccessops
                                           AND p.status = q.status
            LEFT JOIN fm_flow_ps_er_3 w ON p.codiceazienda = w.codiceazienda
                                           AND p.codicestruttura = w.codicestruttura
                                           AND p.codiceaccessops = w.codiceaccessops
                                           AND p.status = w.status
        WHERE
            p.status IN (
                ''WARNING'',
                ''ERROR''
            )
        GROUP BY
            p.month_rif,
            p.year_rif
    ) c ON z.mese = month_rif
           AND z.anno = year_rif
ORDER BY
    to_number(z.anno),
    to_number(z.mese)',null,'Dati Clinici Errati',null,'0019',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'a6fdbf72-2d53-4dcd-bdd4-32b1c94586f8','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'ANAGRAFICHE_ERRATE','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
    mese,
    anno,
    nvl(cc, 0)
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            month_rif,
            year_rif,
            COUNT(1) cc
        FROM
            fm_flow_ps_er_0
        WHERE
            status IN (
                ''WARNING'',
                ''ERROR''
            )
        GROUP BY
            month_rif,
            year_rif
    ) ON mese = month_rif
         AND anno = year_rif',null,'Anagrafiche Errate',null,'0020',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'a00d0be3-9f97-432e-b082-609a961dcf87','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'SCARTO_ANAGRAFICHE','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(SUM(to_number(replace(regexp_replace(nvl(importolordo, ''0''), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))), 0)
        FROM
            fm_flow_ps_er_0 p
            JOIN fm_flow_ps_er_1 q ON p.codiceazienda = q.codiceazienda
                                      AND p.codicestruttura = q.codicestruttura
                                      AND p.codiceaccessops = q.codiceaccessops
        WHERE
            p.status in (''WARNING'', ''ERROR'')
            AND p.month_rif = to_number(z.mese)
            AND p.year_rif = to_number(z.anno)
    ) tot
FROM
    dashboardmeseanno z',null,'Scarto Anagrafiche',null,'0012',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'2d49e18c-607b-464c-b8a9-28dbda54853a','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'SCARTO_DATI_CLINICI','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
    mese,
    anno,
    nvl(v, 0)
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            month_rif,
            year_rif,
            SUM(to_number(replace(regexp_replace(nvl(importolordo, ''0''), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))) v
        FROM
            fm_flow_ps_er_1 p
        WHERE
            status IN (
                ''WARNING'',
                ''ERROR''
            )
            OR EXISTS (
                SELECT
                    1
                FROM
                    fm_flow_ps_er_2 q
                WHERE
                    p.codiceazienda = q.codiceazienda
                    AND p.codicestruttura = q.codicestruttura
                    AND p.codiceaccessops = q.codiceaccessops
                    AND q.status IN (
                        ''WARNING'',
                        ''ERROR''
                    )
            )
            OR EXISTS (
                SELECT
                    1
                FROM
                    fm_flow_ps_er_3 q
                WHERE
                    p.codiceazienda = q.codiceazienda
                    AND p.codicestruttura = q.codicestruttura
                    AND p.codiceaccessops = q.codiceaccessops
                    AND q.status IN (
                        ''WARNING'',
                        ''ERROR''
                    )
            )
        GROUP BY
            month_rif,
            year_rif
    ) ON mese = month_rif
         AND anno = year_rif
ORDER BY
    to_number(anno),
    to_number(mese)',null,'Scarto Dati Clinici',null,'0013',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'f3230d2d-4646-4b4e-a833-2cb47c7929c1','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'PRATICHE','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
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
            fm_flow_ps_er_0
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
ORDER BY
    to_number(anno),
    to_number(mese)',null,'Pratiche',null,'0015',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'ce64e004-eba2-46d1-98bf-384b208ad0c9','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'ERRORI_DISTINCT','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
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
            fm_flow_ps_er_0 a
            LEFT JOIN fm_flow_ps_er_0_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicestruttura = b.codicestruttura
                                                     AND a.codiceaccessops = b.codiceaccessops )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(distinct b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_ps_er_1 a
            LEFT JOIN fm_flow_ps_er_1_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicestruttura = b.codicestruttura
                                                     AND a.codiceaccessops = b.codiceaccessops )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(distinct b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_ps_er_2 a
            LEFT JOIN fm_flow_ps_er_2_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicestruttura = b.codicestruttura
                                                     AND a.codiceaccessops = b.codiceaccessops
                                                     AND b.progressivoprestazione = a.progressivoprestazione )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(distinct b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_ps_er_3 a
            LEFT JOIN fm_flow_ps_er_3_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicestruttura = b.codicestruttura
                                                     AND a.codiceaccessops = b.codiceaccessops
                                                     AND b.progressivodiagnosi = a.progressivodiagnosi )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
    ) q ON mese = month_rif
           AND anno = year_rif
GROUP BY
    mese,
    anno
ORDER BY
    to_number(anno),
    to_number(mese)',null,'Errori Distinct',null,'0017',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'f7c95857-174e-414d-9417-d0166da73272','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'SCARTO_ANAGRAFICHE','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(SUM(to_number(replace(regexp_replace(nvl(importoaziendale, ''0''), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))),''0'')
        FROM
            fm_flow_sdoior_0 p
            JOIN fm_flow_sdoior_1 q ON p.codiceazienda = q.codiceazienda
                                       AND p.codicepresidio = q.codicepresidio
                                       AND p.progressivosdo = q.progressivosdo
        WHERE
            p.status in (''WARNING'', ''ERROR'')
            AND p.month_rif = to_number(z.mese)
            AND p.year_rif = to_number(z.anno)
    ) tot
FROM
    dashboardmeseanno z',null,'Scarto Anagrafiche',null,'0002',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'2b6bdfb9-b97f-4e8a-850b-a31c1896eeac','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'SCARTO_DATI_CLINICI','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
    mese,
    anno,
    nvl(v, 0)
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            month_rif,
            year_rif,
            SUM(to_number(replace(regexp_replace(nvl(importoaziendale, ''0''), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))) v
        FROM
            fm_flow_sdoior_1 p
        WHERE
            status IN (
                ''WARNING'',
                ''ERROR''
            )
            OR EXISTS (
                SELECT
                    1
                FROM
                    fm_flow_sdoior_2 q
                WHERE
                    p.codiceazienda = q.codiceazienda
                    AND p.codicepresidio = q.codicepresidio
                    AND p.progressivosdo = q.progressivosdo
                    AND q.status IN (
                        ''WARNING'',
                        ''ERROR''
                    )
            )
            OR EXISTS (
                SELECT
                    1
                FROM
                    fm_flow_sdoior_3 q
                WHERE
                    p.codiceazienda = q.codiceazienda
                    AND p.codicepresidio = q.codicepresidio
                    AND p.progressivosdo = q.progressivosdo
                    AND q.status IN (
                        ''WARNING'',
                        ''ERROR''
                    )
            )
            OR EXISTS (
                SELECT
                    1
                FROM
                    fm_flow_sdoior_4 q
                WHERE
                    p.codiceazienda = q.codiceazienda
                    AND p.codicepresidio = q.codicepresidio
                    AND p.progressivosdo = q.progressivosdo
                    AND q.status IN (
                        ''WARNING'',
                        ''ERROR''
                    )
            )
        GROUP BY
            month_rif,
            year_rif
    ) ON mese = month_rif
         AND anno = year_rif
ORDER BY
    to_number(anno),
    to_number(mese)',null,'Scarto Dati Clinici',null,'0003',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'34e198d0-f7bb-4fca-ba35-3daf74e54a5f','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values ('attach_money','AZIENDALE','RICAVI_TOTALI','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(SUM(to_number(replace(regexp_replace(nvl(IMPORTOAZIENDALE, ''0''), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))), 0) v
        FROM
            fm_flow_sdoior_1 p
        WHERE
            p.status_group = ''VALID''
            AND p.month_rif = to_number(z.mese)
            AND p.year_rif = to_number(z.anno)
    ) tot
FROM
    dashboardmeseanno z','card-header-info','Ricavi Totali',null,'0004','Ricavi totali','TRUE',to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'9d5f4ea9-bef5-45b0-923f-71aa48af0f3b','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'DATI_CLINICI_ERRATI','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
    z.mese,
    z.anno,
    nvl(v, 0)
FROM
    dashboardmeseanno z
    LEFT JOIN (
        SELECT
            p.month_rif,
            p.year_rif,
            COUNT(1) v
        FROM
            fm_flow_sdoior_1 p
            LEFT JOIN fm_flow_sdoior_2 q ON p.codiceazienda = q.codiceazienda
                                            AND p.codicepresidio = q.codicepresidio
                                            AND p.progressivosdo = q.progressivosdo
                                            AND p.status = q.status
            LEFT JOIN fm_flow_sdoior_3 w ON p.codiceazienda = w.codiceazienda
                                            AND p.codicepresidio = w.codicepresidio
                                            AND p.progressivosdo = w.progressivosdo
                                            AND p.status = w.status
            LEFT JOIN fm_flow_sdoior_4 t ON p.codiceazienda = t.codiceazienda
                                            AND p.codicepresidio = t.codicepresidio
                                            AND p.progressivosdo = t.progressivosdo
                                            AND p.status = t.status
        WHERE
            p.status IN (
                ''WARNING'',
                ''ERROR''
            )
        GROUP BY
            p.month_rif,
            p.year_rif
    ) c ON z.mese = month_rif
           AND z.anno = year_rif
ORDER BY
    to_number(z.anno),
    to_number(z.mese)',null,'Dati Clinici Errati',null,'0005',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'7fcb36e5-e78c-4dcc-81e0-674c20fdc9d6','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'PRATICHE_ERRATE','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(COUNT(1), 0)
        FROM
            fm_flow_sdoior_0 b
        WHERE
            b.status_group in (''WARNING'', ''ERROR'')
            AND b.month_rif = to_number(z.mese)
            AND b.year_rif = to_number(z.anno)
    )
FROM
    dashboardmeseanno z',null,'Pratiche Errate',null,'0006',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'dd699322-75ed-4b37-96a3-669a27bdb9c2','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'PRATICHE','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
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
            fm_flow_SDOIOR_0
        WHERE
            month_rif IS NOT NULL
            AND year_rif IS NOT NULL
        GROUP BY
            month_rif,
            year_rif
    ) a ON mese = month_rif
           AND anno = year_rif
ORDER BY
    to_number(anno),
    to_number(mese)',null,'Pratiche',null,'0007',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'75977a7c-253e-4249-97a6-a39384975222','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'ERRORI_DISTINCT','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
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
            fm_flow_sdoior_0 a
            LEFT JOIN fm_flow_sdoior_0_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicepresidio = b.codicepresidio
                                                     AND a.progressivosdo = b.progressivosdo )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(distinct b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_sdoior_1 a
            LEFT JOIN fm_flow_sdoior_1_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicepresidio = b.codicepresidio
                                                     AND a.progressivosdo = b.progressivosdo )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(distinct b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_sdoior_2 a
            LEFT JOIN fm_flow_sdoior_2_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicepresidio = b.codicepresidio
                                                     AND a.progressivosdo = b.progressivosdo
                                                     AND b.progressivodiagnosi = a.progressivodiagnosi )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(distinct b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_sdoior_3 a
            LEFT JOIN fm_flow_sdoior_3_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicepresidio = b.codicepresidio
                                                     AND a.progressivosdo = b.progressivosdo
                                                     AND b.progressivotrasferimento = a.progressivotrasferimento )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
            UNION ALL
        SELECT
            COUNT(distinct b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_sdoior_4 a
            LEFT JOIN fm_flow_sdoior_4_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicepresidio = b.codicepresidio
                                                     AND a.progressivosdo = b.progressivosdo
                                                     AND b.progressivointervento = a.progressivointervento )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
    ) q ON mese = month_rif
           AND anno = year_rif
GROUP BY
    mese,
    anno
ORDER BY
    to_number(anno),
    to_number(mese)',null,'Errori Distinct',null,'0009',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'e048ce47-ec1b-4b84-b1c1-57742b71e4d3','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'ANAGRAFICHE_ERRATE','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
    mese,
    anno,
    nvl(cc, 0)
FROM
    dashboardmeseanno left
    JOIN (
        SELECT
            month_rif,
            year_rif,
            COUNT(1) cc
        FROM
            fm_flow_sdoior_0
        WHERE
            status IN (
                ''WARNING'',
                ''ERROR''
            )
        GROUP BY
            month_rif,
            year_rif
    ) ON mese = month_rif
         AND anno = year_rif',null,'Anagrafiche Errate',null,'0001',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'d7fc674a-67b7-4654-ae89-39a100661b1c','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values ('business','REGIONALE','PRATICHE_ERRATE_REG','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(COUNT(1), 0)
        FROM
            fm_flow_sdoior_0 b
        WHERE
            b.status_region in (''WARNING'', ''ERROR'')
            AND b.month_rif = to_number(z.mese)
            AND b.year_rif = to_number(z.anno)
            AND b.state_send_region = ''ACCETTATA''
    )
FROM
    dashboardmeseanno z','card-header-rose','Pratiche Errate Da Regione','PRATICHE_ERR','0011','FOOOTER','TRUE',to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'99','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values ('business','REGIONALE','RICAVI_TOT_RIC_REG','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(SUM(to_number(replace(regexp_replace(nvl(importoaziendale, ''0''), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))), 0) v
        FROM
            fm_flow_sdoior_1 p
            JOIN fm_flow_sdoior_0 q ON p.codiceazienda = q.codiceazienda
                                       AND p.progressivosdo = q.progressivosdo
                                       AND p.codicepresidio = q.codicepresidio
        WHERE
            q.month_rif = to_number(z.mese)
            AND q.year_rif = to_number(z.anno)
            AND q.state_send_region = ''ACCETTATA''
            AND q.status_region = ''VALID''
    ) tot
FROM
    dashboardmeseanno z
ORDER BY
    z.mese,
    z.anno','card-header-rose','Ricavi Totali Riconosciute Da Regione',null,'0026','FOOOTER','TRUE',to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'bdf2d465-3add-4fb6-91c4-0d36ad83e346','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values ('business','REGIONALE','RICAVI_TOT_RIC_REG','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(SUM(to_number(replace(regexp_replace(nvl(importolordo, ''0''), ''0[0*]0,[0*]0'', ''0''), '','', ''.''))), 0) v
        FROM
            fm_flow_ps_er_1 p
            JOIN fm_flow_ps_er_0 q ON p.codiceazienda = q.codiceazienda
                                       AND p.codicestruttura = q.codicestruttura
                                       AND p.codiceaccessops = q.codiceaccessops
        WHERE
            q.month_rif = to_number(z.mese)
            AND q.year_rif = to_number(z.anno)
            AND q.state_send_region = ''ACCETTATA''
            AND q.status_region = ''VALID''
    ) tot
FROM
    dashboardmeseanno z
ORDER BY
    z.mese,
    z.anno','card-header-rose','Ricavi Totali Riconosciute Da Regione',null,'0027','FOOOTER','TRUE',to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'ef91f446-60b5-4fb4-a365-32659f4a0627','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'ERRORI','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
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
            fm_flow_ps_er_0 a
            LEFT JOIN fm_flow_ps_er_0_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicestruttura = b.codicestruttura
                                                     AND a.codiceaccessops = b.codiceaccessops )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_ps_er_1 a
            LEFT JOIN fm_flow_ps_er_1_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicestruttura = b.codicestruttura
                                                     AND a.codiceaccessops = b.codiceaccessops )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_ps_er_2 a
            LEFT JOIN fm_flow_ps_er_2_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicestruttura = b.codicestruttura
                                                     AND a.codiceaccessops = b.codiceaccessops
                                                     AND b.progressivoprestazione = a.progressivoprestazione )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_ps_er_3 a
            LEFT JOIN fm_flow_ps_er_3_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicestruttura = b.codicestruttura
                                                     AND a.codiceaccessops = b.codiceaccessops
                                                     AND b.progressivodiagnosi = a.progressivodiagnosi )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
    ) q ON mese = month_rif
           AND anno = year_rif
GROUP BY
    mese,
    anno
ORDER BY
    to_number(anno),
    to_number(mese)',null,'Errori',null,'0016',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'fd001b86-5e85-4218-985e-1a4554f0f147','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'PRATICHE_ERRATE','4d971f52-0716-47bc-9255-5e9ca8f0d028','SELECT
    z.mese,
    z.anno,
    (
        SELECT
            nvl(COUNT(1), 0)
        FROM
            fm_flow_ps_er_0 b
        WHERE
            b.status_group in (''WARNING'', ''ERROR'')
            AND b.month_rif = to_number(z.mese)
            AND b.year_rif = to_number(z.anno)
    )
FROM
    dashboardmeseanno z',null,'Pratiche Errate',null,'0014',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'f9643af9-233f-4fdf-a455-63b685e1d266','FM_DASHBOARD_CONFIG');
Insert into FM_DASHBOARD_CONFIG (ICON,TIPO,NAME,FLOW,QUERY,COLOR,LABEL,ACTIVITY,ID,FOOTER,BADGE,DT_ENABLE,DT_DISABLE,TV_ID,TABGEN_ID) values (null,null,'ERRORI','c299ad8c-012e-471c-8c0c-ac721fe242cf','SELECT
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
            fm_flow_sdoior_0 a
            LEFT JOIN fm_flow_sdoior_0_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicepresidio = b.codicepresidio
                                                     AND a.progressivosdo = b.progressivosdo )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_sdoior_1 a
            LEFT JOIN fm_flow_sdoior_1_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicepresidio = b.codicepresidio
                                                     AND a.progressivosdo = b.progressivosdo )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_sdoior_2 a
            LEFT JOIN fm_flow_sdoior_2_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicepresidio = b.codicepresidio
                                                     AND a.progressivosdo = b.progressivosdo
                                                     AND b.progressivodiagnosi = a.progressivodiagnosi )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
        UNION ALL
        SELECT
            COUNT(b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_sdoior_3 a
            LEFT JOIN fm_flow_sdoior_3_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicepresidio = b.codicepresidio
                                                     AND a.progressivosdo = b.progressivosdo
                                                     AND b.progressivotrasferimento = a.progressivotrasferimento )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
            UNION ALL
        SELECT
            COUNT(b.message) mes,
            a.month_rif,
            a.year_rif
        FROM
            fm_flow_sdoior_4 a
            LEFT JOIN fm_flow_sdoior_4_message b ON ( a.codiceazienda = b.codiceazienda
                                                     AND a.codicepresidio = b.codicepresidio
                                                     AND a.progressivosdo = b.progressivosdo
                                                     AND b.progressivointervento = a.progressivointervento )
        WHERE
            a.month_rif IS NOT NULL
            AND a.year_rif IS NOT NULL
        GROUP BY
            a.year_rif,
            a.month_rif
    ) q ON mese = month_rif
           AND anno = year_rif
GROUP BY
    mese,
    anno
ORDER BY
    to_number(anno),
    to_number(mese)',null,'Errori',null,'0008',null,null,to_date('01-JAN-00','DD-MON-RR'),to_date('01-JAN-00','DD-MON-RR'),'af7e8f67-7824-43ae-af51-e4ccbe808f31','FM_DASHBOARD_CONFIG');
Insert into HC40_FLOW_MANAGER.FM_DASHBOARD_CONFIG
   (ICON, TIPO, NAME, FLOW, QUERY, 
    COLOR, LABEL, ACTIVITY, ID, FOOTER, 
    BADGE, DT_ENABLE, DT_DISABLE, TV_ID, TABGEN_ID)
 Values
   ('grade', 'REGIONALE', 'PRATICHE_NOT_SEND_REG', 'c299ad8c-012e-471c-8c0c-ac721fe242cf', 'SELECT mese, anno, NVL (c, 0)   FROM dashboardmeseanno        LEFT JOIN        (  SELECT COUNT (1) c, month_rif, year_rif             FROM fm_flow_sdoior_0            WHERE     month_rif IS NOT NULL                  AND year_rif IS NOT NULL                  AND (   state_send_region = ''DA_INVIARE'')         GROUP BY month_rif, year_rif) a           ON mese = month_rif AND anno = year_rif           ORDER BY    to_number(anno),    to_number(mese)', 
    'card-header-success', 'Pratiche non ancora inviate a regione', 'PRATICHE', '0029', 'FOOOTER', 
    'TRUE', TO_DATE('1/1/1900', 'MM/DD/YYYY'), TO_DATE('1/1/2100', 'MM/DD/YYYY'), 'ffc907d0-e4aa-44ac-ae0e-a92a30b4a7fb', 'FM_DASHBOARD_CONFIG');
COMMIT;

    
    
    
SET DEFINE OFF;
Insert into HC40_FLOW_MANAGER.FM_DASHBOARD_CONFIG
   (ICON, TIPO, NAME, FLOW, QUERY, 
    COLOR, LABEL, ACTIVITY, ID, FOOTER, 
    BADGE, DT_ENABLE, DT_DISABLE, TV_ID, TABGEN_ID)
 Values
   ('grade', 'REGIONALE', 'PRATICHE_NOT_SEND_REG', '4d971f52-0716-47bc-9255-5e9ca8f0d028', 'SELECT mese, anno, NVL (c, 0)   FROM dashboardmeseanno        LEFT JOIN        (  SELECT COUNT (1) c, month_rif, year_rif             FROM fm_flow_ps_er_0            WHERE     month_rif IS NOT NULL                  AND year_rif IS NOT NULL                  AND (   state_send_region = ''DA_INVIARE'')         GROUP BY month_rif, year_rif) a           ON mese = month_rif AND anno = year_rif           ORDER BY    to_number(anno),    to_number(mese)', 
    'card-header-success', 'Pratiche non ancora inviate a regione', 'PRATICHE', '0028', 'footer', 
    'TRUE', TO_DATE('1/1/1900', 'MM/DD/YYYY'), TO_DATE('1/1/2100', 'MM/DD/YYYY'), '46aba0bc-dcdb-496f-bfce-6c44036916a2', 'FM_DASHBOARD_CONFIG');
COMMIT;
