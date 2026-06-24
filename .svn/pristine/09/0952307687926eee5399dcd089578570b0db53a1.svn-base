package it.eng.care.domain.flow.b2b.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ZipService {

    /**
     * Estra il primo file dal file ZIP passato
     *
     * @param zipFile File Zip
     * @return File estratto
     * @throws IOException
     */
    File readFirstFile(File zipFile) throws IOException;

    /**
     * Estrae il primo file dallo stream contenente un ZIP in una cartella
     * temporanea
     *
     * @param openStream Stream contenente il file
     * @return File estratto
     * @throws IOException
     */
    File readFirstFile(InputStream openStream) throws IOException;

}
