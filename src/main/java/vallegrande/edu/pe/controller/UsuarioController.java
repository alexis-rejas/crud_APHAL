package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.dao.UsuarioDAO;
import vallegrande.edu.pe.model.Usuario;
import vallegrande.edu.pe.util.Validador;

import java.util.List;

public class UsuarioController {
    private final UsuarioDAO dao = new UsuarioDAO();

    public String guardar(String nombre, String correo,
                          String telefono, String asunto, String mensaje) {
        if (!Validador.requerido(nombre))       return "El nombre es requerido.";
        if (!Validador.soloLetras(nombre))      return "El nombre solo acepta letras.";
        if (!Validador.requerido(correo))       return "El correo es requerido.";
        if (!Validador.esCorreoValido(correo))  return "El correo no es válido.";
        if (!Validador.esTelefonoPE(telefono))  return "Teléfono inválido (9 dígitos, empieza en 9).";
        if (!Validador.requerido(asunto))       return "Selecciona un asunto.";
        if (!Validador.requerido(mensaje))      return "El mensaje es requerido.";

        Usuario u = new Usuario(0, nombre, correo, telefono, asunto, mensaje);
        return dao.insertar(u) ? "OK" : "Error al guardar en la base de datos.";
    }

    public String actualizar(int id, String nombre, String correo,
                             String telefono, String asunto, String mensaje) {
        if (!Validador.requerido(nombre))       return "El nombre es requerido.";
        if (!Validador.soloLetras(nombre))      return "El nombre solo acepta letras.";
        if (!Validador.esCorreoValido(correo))  return "El correo no es válido.";
        if (!Validador.esTelefonoPE(telefono))  return "Teléfono inválido.";

        Usuario u = new Usuario(id, nombre, correo, telefono, asunto, mensaje);
        return dao.actualizar(u) ? "OK" : "Error al actualizar.";
    }

    public boolean eliminar(int id) { return dao.eliminar(id); }

    public List<Usuario> listar() { return dao.listar(); }
}