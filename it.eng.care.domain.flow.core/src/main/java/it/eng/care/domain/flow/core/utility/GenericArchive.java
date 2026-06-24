package it.eng.care.domain.flow.core.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

public abstract class GenericArchive {

    private static int BUFFER = 2048;

    public GenericArchive() {
    }

    /**
     * Copia i file dalla stream inStream nello stream outStream.
     *
     * @param inStream  Stream input.
     * @param outStream Stream di output.
     * @throws IOException
     */
    public static void extractFile(InputStream inStream, OutputStream outStream) throws IOException {
        byte[] buf = new byte[BUFFER];
        int l;
        while ((l = inStream.read(buf)) >= 0) {
            outStream.write(buf, 0, l);
        }
        inStream.close();
        outStream.close();
    }

    /**
     * Cancella la directory dir con gli eventuali file che contiene.
     *
     * @param dir La directory da cencellare.
     * @return true se la cancellazione ï¿½ andata a buon fine, false altrimenti.
     */
    public static boolean DelDir(File dir) {
        if (dir.isDirectory()) {
            String[] contenuto = dir.list();
            for (int i = 0; i < contenuto.length; i++) {
                if (!DelDir(new File(dir, contenuto[i]))) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

    /**
     * Estrae dal file di input il nome del file.
     *
     * @param path percorso da cui estrarre il nome del file
     * @return Il nome del file estratto dal path di input
     * @throws IOException
     */
    public static String getNameFileFromPath(String path) {
        if ((path != null) && ((path.lastIndexOf("/") != -1) || (path.lastIndexOf("\\") != -1))) {
            int pos = (path.lastIndexOf("/") != -1) ? path.lastIndexOf("/") : path.lastIndexOf("\\");
            return path.substring(pos + 1);
        }

        return path;
    }

    /**
     * Restituisce la lista dei file contenuti nell'archivio identificato dal path pathFile.
     *
     * @param pathFile             percorso che identifica l'archivio.
     * @param normalizeNameEntries specifica se si vuole o meno come output l'intero path del file contenuto nell'archivio di input.
     * @param WithDirectory        specifica se tre le entry di output devono essere incluse le directory.
     * @return La lista dei file contenuti nell'archivio. Se l'operazione non va a buon fine viene restituito null.
     */
    public abstract List<String> getEntries(String pathFile, boolean normalizeNameEntries, boolean WithDirectory);

    /**
     * Comprime i file contenuti nella directory dirFiles, salvandoli nella directory pathFilesOutput. Nel caso in cui sia specificato il parametro fileName, vengono compressi solamenti i file presenti in quest'insieme.
     *
     * @param dirFilesZIP     percorso che contiene i file da comprimere.
     * @param pathFilesOutput percorso in cui verranno salvati i file compressi.
     * @param fileNames       specifica la lista dei file che devono essere effettivamente estratti.
     * @return O nel cosa l'operazione sia andata a buon fine, -1 altrimenti.
     */
    public abstract Integer compressingFiles(String dirFiles, String pathFilesOutput, String... fileNames) throws UnsupportedOperationException;

    /**
     * Decomprime l'archivio identificato dal path pathFileinput nella directory pathDirOutput escludento tutti i files presenti nell'insieme fileNotValid.
     *
     * @param pathFileinput   percorso che contiene i file da decomprimere.
     * @param pathFilesOutput percorso in cui verranno salvati i file decompressi.
     * @param fileNames       specifica la lista dei file che devono essere effettivamente estratti.
     * @return O nel cosa l'operazione sia andata a buon fine, -1 altrimenti.
     */
    public abstract Integer decompressingFiles(String pathFileinput, String pathDirOutput, Set<String> fileNotValid);

    /**
     * Resituisce l'estensione dell'archivio che la classe gestisce.
     *
     * @return
     */
    public abstract String getExtension();

}
