package it.eng.care.domain.flow.core.utility;

import java.util.Arrays;
import org.slf4j.Logger;

public class LogUtil {
    private static final String[] APP_PACKAGES = { "it.eng.care.domain.flow.api", "it.eng.care.domain.flow.core", "it.eng.care.domain.flow.drools","it.eng.care.domain.flow.jobs" };

    private LogUtil() {}

    public static void logException(Logger logger, String msg, Throwable t) {
        StackTraceElement origin = findAppOrigin(t);

        logger.error("{} - Errore nel metodo : {}.{} - della classe : {} alla riga : {} - Messaggio errore : {}",
                msg,
                origin.getClassName(),
                origin.getMethodName(),
                origin.getFileName(),
                origin.getLineNumber(),
                t.getMessage(),
                t); // <-- stacktrace completo
    }

    public static void logException(Logger logger, Throwable t) {
        logException(logger, "Errore", t);
    }

    private static StackTraceElement findAppOrigin(Throwable t) {
        if (t == null || t.getStackTrace() == null || t.getStackTrace().length == 0) {
            return new StackTraceElement("unknown", "unknown", "unknown", -1);
        }

        // Scava anche nelle cause, così se hai wrapper trovi il punto “vero”
        Throwable cur = t;
        while (cur.getCause() != null) {
            cur = cur.getCause();
        }

        StackTraceElement[] st = cur.getStackTrace();
        return Arrays.stream(st)
                .filter(LogUtil::isAppClass)
                .findFirst()
                .orElse(st[0]);
    }

    private static boolean isAppClass(StackTraceElement ste) {
        String cn = ste.getClassName();
        for (String p : APP_PACKAGES) {
            if (cn.startsWith(p)) return true;
        }
        return false;
    }
}
