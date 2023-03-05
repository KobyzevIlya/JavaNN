package hw2.filehandler;

import java.io.File;

public class FileHandler {
    private File file = null;

    public void setFile(File file) {
        this.file = file;
    }

    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }
}
