package com.example.parcialfinal.Model;


public class Usuarios {
    private Long id;
    private String Usuario;
    private String Contrasena;
    private String Correo;

    public Usuarios() {
    }

    public Usuarios(Long id,String Usuario,  String Contrasena ,String Correo) {
        this.id = id;
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
        this.Correo = Correo;


    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", usuario='" + Usuario + '\'' +
                ", correo='" + Correo + '\'' +
                ", contrasena='" + Contrasena + '\'' +
                '}';
    }
}
