package it.eng.care.domain.flow.core.utility;

import java.io.File;

public class ExcelFile implements FileType {
    private File file;
    private String extension;
    private String fileName;

    public ExcelFile(File file, String extension, String fileName) {
        this.file = file;
        this.extension = extension;
        this.fileName = fileName;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String getName() {
        return fileName;
    }
    
    @Override
    public String getExtension() {
        return extension;
    }
}
