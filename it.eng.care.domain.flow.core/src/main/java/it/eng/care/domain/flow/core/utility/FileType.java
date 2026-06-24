package it.eng.care.domain.flow.core.utility;

import java.io.File;

public interface FileType {
	File getFile();
    String getName();
    String getExtension();
}
