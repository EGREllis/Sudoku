package com.example.sudoku;

import android.util.Log;

public class Util {
    public static final String LOG_MESSAGE = "SUDOKU";

    public static void debug(String message) {
        Log.d(LOG_MESSAGE, message);
    }

    // This method is for "catching" exceptions in finally blocks.
    public static void requiredButExcessiveExceptionHandling(Exception e) {
        StackTraceElement[] elements = Thread.getAllStackTraces().get(Thread.currentThread());
        StringBuilder manualStackTrace = new StringBuilder();
        for (StackTraceElement element : elements) {
            manualStackTrace.append(String.format("\tFile: %1$s\tClass: %2$d\tMethod: %3$s\tLine: %4$d\n", element.getFileName(), element.getClassName(), element.getMethodName(), element.getLineNumber()));
        }
        debug(String.format("Exception inside finally!\n%1$s\n$2$s", e.getMessage(), manualStackTrace));
    }
}
