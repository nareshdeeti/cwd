package com.ndeeti.cwd;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.nio.file.Path;

public class CwdExecutor {


    Path workingDir() {
        return Path.of("");
    }

    String absolutePath(Path dir) {
        Path absolutePath = dir.toAbsolutePath();
        return absolutePath.toString();
    }

    String realPath(Path dir) throws IOException {
        Path realPath = dir.toRealPath();
        return realPath.toString();
    }

    void setCwdToClipboard(String path) {
        StringSelection stringSelection = new StringSelection(path);
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        systemClipboard.setContents(stringSelection, null);
    }

    public void absoluteCWD() {
        String absolutePath = absolutePath(workingDir());
        setCwdToClipboard(absolutePath);
    }

    public void realCWD() throws IOException {
        String realPath = realPath(workingDir());
        setCwdToClipboard(realPath);
    }

}
