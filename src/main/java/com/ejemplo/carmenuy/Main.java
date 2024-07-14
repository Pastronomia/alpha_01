package com.ejemplo.carmenuy;

import com.ejemplo.carmenuy.manager.GitIgnoreManager;

public class Main {

    public static void main(String[] args) {
        GitIgnoreManager manager = new GitIgnoreManager();

        // Imprimir archivos ignorados
        System.out.println("Archivos ignorados por Git:");
        manager.printIgnoredFiles();

        // Verificar si un archivo debe ser ignorado
        String fileName = "example.class";
        boolean isIgnored = manager.isIgnored(fileName);
        System.out.println("¿Debe ser ignorado '" + fileName + "'? " + isIgnored);

        // Agregar un nuevo patrón
        String newPattern = "*.example";
        manager.addIgnorePattern(newPattern);
        System.out.println("Nuevo patrón agregado: " + newPattern);
        manager.printIgnoredFiles();

        // Eliminar un patrón
        manager.removeIgnorePattern(newPattern);
        System.out.println("Patrón eliminado: " + newPattern);
        manager.printIgnoredFiles();
    }
}
