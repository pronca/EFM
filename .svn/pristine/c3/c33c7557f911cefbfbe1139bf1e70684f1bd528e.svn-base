package it.eng.care.domain.flow.b2b.service.impl;

import it.eng.care.domain.flow.b2b.service.ZipService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ZipServiceImpl implements ZipService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public File readFirstFile(InputStream openStream) throws IOException {
        // leggo il fle zip al volo e lo estraggo
        try (ZipInputStream zip = new ZipInputStream(openStream)) {
            ZipEntry entry = zip.getNextEntry();

            if (entry != null) {
                File tmpFile = File.createTempFile(UUID.randomUUID().toString(), ".json");
                logger.info("Saved file " + tmpFile.getAbsolutePath());

                // salvo il file zip sul disco
                IOUtils.copy(zip, new FileOutputStream(tmpFile));
                //
                return tmpFile;
            }
            return null;
        }
    }

    @Override
    public File readFirstFile(File zipFile) throws IOException {
        // apro il file
        try (FileInputStream in = new FileInputStream(zipFile)) {
            return readFirstFile(in);
        }
    }

}
