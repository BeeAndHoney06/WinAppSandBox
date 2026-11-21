package org.BeeAndHoney06.WinAppSandBox;

import okhttp3.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;

public class FileIntegrity {
    private static final OkHttpClient CLIENT = new OkHttpClient();

    public static void main(String[] args) throws Exception {
        Path directory = Paths.get("C:\\Program Files\\WinAppSandBox");

        String LICENSE = "LICENSE";
        String GithubRepoLicenseFile =
                "https://raw.githubusercontent.com/BeeAndHoney06/WinAppSandBox/main/LICENSE";

        String README_MD = "README.md";
        String GithubRepoReadmeFile =
                "https://raw.githubusercontent.com/BeeAndHoney06/WinAppSandBox/main/README.md";

        ensureDirectory(directory);
        Path License = directory.resolve(LICENSE);
        Path Readme = directory.resolve(README_MD);

        if (!Files.exists(License)) {
            System.out.println("License file downloading...");
            DownloadFile(GithubRepoLicenseFile, License);
        }

        if (!Files.exists(Readme)) {
            System.out.println("Readme file downloading...");
            DownloadFile(GithubRepoReadmeFile, Readme);
        }
    }

    private static void ensureDirectory(Path directory) throws IOException {
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
    }

    private static void DownloadFile(String url, Path target) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Download failed: HTTP " + response.code());
            }
            try (InputStream in = response.body().byteStream();
                 OutputStream out = Files.newOutputStream(target)) {
                byte[] buffer = new byte[8192];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            }
        }
    }
}