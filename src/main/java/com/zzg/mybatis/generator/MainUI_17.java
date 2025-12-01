package com.zzg.mybatis.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * 这是本软件的主入口,要运行本软件请直接运行本类就可以了,不用传入任何参数
 * JDK 17
 */
public class MainUI_17 {

    public static void main(String[] args) throws Exception {
        String mp = resolveModulePath();
        List<String> cmd = new ArrayList<>();
        String javaExe = new File(System.getProperty("java.home"), "bin\\java.exe").getAbsolutePath();
        cmd.add(javaExe);
        if (mp != null && !mp.isEmpty()) {
            cmd.add("--module-path");
            cmd.add(mp);
            cmd.add("--add-modules");
            cmd.add("javafx.controls,javafx.fxml");
        }
        cmd.add("-cp");
        cmd.add(System.getProperty("java.class.path"));
        cmd.add(MainUI.class.getName());
        new ProcessBuilder(cmd).inheritIO().start();
    }

    private static String resolveModulePath() {
        String base = System.getProperty("user.home") + File.separator + ".m2" + File.separator + "repository" + File.separator + "org" + File.separator + "openjfx";
        String[] mods = new String[]{"javafx-controls", "javafx-graphics", "javafx-base", "javafx-fxml"};
        List<String> jars = new ArrayList<>();
        for (String m : mods) {
            File dir = new File(base + File.separator + m + File.separator + "17");
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File f : files) {
                        String name = f.getName();
                        if (name.endsWith(".jar")) {
                            jars.add(f.getAbsolutePath());
                        }
                    }
                }
            }
        }
        if (jars.isEmpty()) return "";
        String sep = File.pathSeparator;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jars.size(); i++) {
            if (i > 0) sb.append(sep);
            sb.append(jars.get(i));
        }
        return sb.toString();
    }
}
