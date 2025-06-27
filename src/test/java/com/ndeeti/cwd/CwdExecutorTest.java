package com.ndeeti.cwd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.nio.file.Path;

class CwdExecutorTest {

	static Path workingDir = null;
	static CwdExecutor cwdExecutor = null;
	static StringSelection stringSelection;
	static Clipboard systemClipboard;
	static String workingAbsolutePath;
	static String workingRealPath;

	@BeforeAll
	static void setUpPath() throws IOException {
		workingDir = Path.of("");
		cwdExecutor = new CwdExecutor();
		stringSelection = new StringSelection(workingDir.toAbsolutePath().toString());
		systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		systemClipboard.setContents(stringSelection, null);
		workingAbsolutePath = workingDir.toAbsolutePath().toString();
		workingRealPath = workingDir.toRealPath().toString();
	}

	@Test
	void workingDir() {
		Path targetPath = cwdExecutor.workingDir();
		Assertions.assertNotNull(targetPath);
		Assertions.assertEquals(workingDir, targetPath);
	}

	@Test
	void absolutePath() {
		Path targetPath = cwdExecutor.workingDir();
		Assertions.assertEquals(workingDir.toAbsolutePath(), targetPath.toAbsolutePath());
	}

	@Test
	void realPath() throws IOException {
		Path targetPath = cwdExecutor.workingDir();
		Assertions.assertEquals(workingDir.toRealPath(), targetPath.toAbsolutePath());
	}

	@Test
	void setCwdToClipboard() {
		cwdExecutor.setCwdToClipboard(workingAbsolutePath);
		Assertions.assertEquals(workingAbsolutePath, getOneData());
	}

	@Test
	void absoluteCWD() {
		String absoluteCWD = cwdExecutor.absoluteCWD();
		Assertions.assertEquals(workingAbsolutePath, absoluteCWD);
	}

	@Test
	void realCWD() throws IOException {
		String realCWD = cwdExecutor.realCWD();
		Assertions.assertEquals(workingRealPath, realCWD);
	}

	static String getOneData() {
		java.awt.datatransfer.Transferable contents = systemClipboard.getContents(null);

		boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		String result = "";
		if (hasTransferableText) {
			try {
				result = (String) contents.getTransferData(DataFlavor.stringFlavor);
			}
			catch (UnsupportedFlavorException | IOException ex) {
				System.err.println("Error getting clipboard data: " + ex.getMessage());
			}
		}
		return result;
	}

}