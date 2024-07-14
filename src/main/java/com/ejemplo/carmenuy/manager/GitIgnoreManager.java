package com.ejemplo.carmenuy.manager;

import com.ejemplo.carmenuy.config.GitIgnoreConfig;

import java.util.List;

/**
 * Clase que maneja las operaciones relacionadas con los archivos y carpetas ignorados por Git.
 */
public class GitIgnoreManager {

    /**
     * Imprime la lista de patrones de archivos ignorados.
     */
    public void printIgnoredFiles() {
        List<String> gitIgnorePatterns = GitIgnoreConfig.getGitIgnorePatterns();
        for (String pattern : gitIgnorePatterns) {
            System.out.println(pattern);
        }
    }

    /**
     * Verifica si un archivo o carpeta debe ser ignorado por Git.
     *
     * @param fileName Nombre del archivo o carpeta.
     * @return true si debe ser ignorado, false en caso contrario.
     */
    public boolean isIgnored(String fileName) {
        List<String> gitIgnorePatterns = GitIgnoreConfig.getGitIgnorePatterns();
        for (String pattern : gitIgnorePatterns) {
            if (fileName.matches(pattern.replace("*", ".*"))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Agrega un nuevo patrón de archivo o carpeta a la lista de ignorados.
     *
     * @param pattern Patrón del archivo o carpeta.
     */
    public void addIgnorePattern(String pattern) {
        List<String> gitIgnorePatterns = GitIgnoreConfig.getGitIgnorePatterns();
        if (!gitIgnorePatterns.contains(pattern)) {
            gitIgnorePatterns.add(pattern);
        }
    }

    /**
     * Elimina un patrón de archivo o carpeta de la lista de ignorados.
     *
     * @param pattern Patrón del archivo o carpeta.
     */
    public void removeIgnorePattern(String pattern) {
        List<String> gitIgnorePatterns = GitIgnoreConfig.getGitIgnorePatterns();
        gitIgnorePatterns.remove(pattern);
    }
}

