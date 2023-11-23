package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String HOME = System.getProperty("user.home");
    private static final String DEFAULT_FILE = "output.txt";

    private File file = new File(HOME + File.separator + DEFAULT_FILE);

    public void setFile(final File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            this.file = file;
        }
        else {
            throw new IllegalArgumentException("The folder doesn't exist");
        }
    }

    public File getFile() {
        return this.file;
    }

    public String getCurrentFilePath() {
        return this.file.getPath();
    }

    public void write(final String input) throws IOException {
        try (PrintStream ps = new PrintStream(file, StandardCharsets.UTF_8)) {
            ps.println(input);
        }
    }
}
