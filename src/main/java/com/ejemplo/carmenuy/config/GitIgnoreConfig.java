package com.ejemplo.carmenuy.config;

import java.util.Arrays;
import java.util.List;

/**
 * Clase que define las configuraciones de archivos ignorados para el proyecto.
 */
public class GitIgnoreConfig {

    // Lista de archivos y carpetas que deben ser ignorados por Git
    private static final List<String> gitIgnorePatterns = Arrays.asList(
            "*.class",            // Archivos compilados de Java
            "*.jar",              // Archivos de paquete
            "*.war",
            "*.ear",
            "target/",            // Carpeta target de Maven
            ".idea/",             // Archivos de IDE
            "*.iml",
            ".vscode/",
            "*.sublime-workspace",
            "application.properties", // Archivos de configuración local
            "application.yml",
            "*.db",
            "*.sqlite",
            "*.sqlite3",
            "*.log",              // Logs
            ".DS_Store",          // Archivos del sistema operativo
            "Thumbs.db",
            "*.tmp",              // Archivos temporales
            "*.bak",
            "*.swp",
            "*~.nib",
            "pom.xml.tag",        // Archivos específicos de Maven
            "pom.xml.releaseBackup",
            "pom.xml.versionsBackup",
            "pom.xml.next",
            "release.properties",
            "dependency-reduced-pom.xml",
            "buildNumber.properties",
            ".mvn/timing.properties",
            "*.properties",       // Archivos de configuración de base de datos
            "*.yml",
            "build/",             // Carpeta de compilación de Gradle
            ".classpath",         // Archivos específicos de Eclipse
            ".project",
            ".settings/",
            "nbproject/private/", // Archivos específicos de NetBeans
            "build/",
            "nbbuild/",
            "dist/",
            "nbdist/",
            ".nb-gradle/",
            ".spring-boot-devtools.properties" // Archivos específicos de Spring Boot
    );

    public static List<String> getGitIgnorePatterns() {
        return gitIgnorePatterns;
    }
}
