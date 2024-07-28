package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.UsuarioDAO;
import com.ejemplo.carmenuy.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UsuarioService {
    private static final Logger LOGGER = Logger.getLogger(UsuarioService.class.getName());
    private final UsuarioDAO usuarioDAO;

    public UsuarioService(Connection conexion) {
        this.usuarioDAO = new UsuarioDAO(conexion);
    }

    public void registrarUsuario(Usuario usuario) throws SQLException {
        if (usuarioDAO.obtenerUsuarioPorNombre(usuario.getNombre()) == null) {
            usuarioDAO.insertarUsuario(usuario);
            LOGGER.info("Usuario registrado: " + usuario.getNombre());
        } else {
            LOGGER.warning("El usuario ya existe: " + usuario.getNombre());
        }
    }

    public Usuario obtenerUsuarioPorNombre(String nombre) throws SQLException {
        return usuarioDAO.obtenerUsuarioPorNombre(nombre);
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.actualizarUsuario(usuario);
        LOGGER.info("Usuario actualizado: " + usuario.getNombre());
    }

    public void eliminarUsuario(int id) throws SQLException {
        usuarioDAO.eliminarUsuario(id);
        LOGGER.info("Usuario eliminado con ID: " + id);
    }
}
