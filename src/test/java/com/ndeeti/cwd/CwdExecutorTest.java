package com.ndeeti.cwd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CwdExecutorTest {

	static Path workingDir = null;
	static CwdExecutor cwdExecutor = null;

	@BeforeAll
	static void setUpPath() {
		workingDir = Path.of("");
		cwdExecutor = new CwdExecutor();
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

}