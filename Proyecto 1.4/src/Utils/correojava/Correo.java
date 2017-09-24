/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.correojava;

/**
 *
 * @author Alienware
 */
public class Correo {
    private String usuarioCorreo;
    private String contrasenia;
    private String rutaArchivo;
    private String nombreArchivo;
    private String destinatarios;
    private String asunto;
    private String mensaje;
    private String organizador;
    
    public Correo(String mensaje, String destino){
        setContrasenia("mzbbhdnxxcnjbxzp");
        setUsuarioCorreo("sistemabibliotecaitcr@gmail.com");
        setAsunto("Reserva de Sala");
        setMensaje(mensaje);
        setDestinatarios(destino.trim());
        setNombreArchivo("reserva_exitosa.jpg");
        setRutaArchivo("img_correo/reserva_exitosa.jpg");
        //setOrganizador(organizador);
    }
    
    public Correo(String contrasenia, String usuarioCorreo, String asunto, String mensaje, String organizador, String destino, String nombreArchivo, String rutaArchivo){
        setContrasenia(contrasenia);
        setUsuarioCorreo(usuarioCorreo);
        setAsunto(asunto);
        setMensaje(mensaje);
        setDestinatarios(destino.trim());
        setNombreArchivo(nombreArchivo);
        setRutaArchivo(rutaArchivo);
        //setOrganizador(organizador);
    }
    /**
     * @return the usuarioCorreo
     */
    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    /**
     * @param usuarioCorreo the usuarioCorreo to set
     */
    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return the rutaArchivo
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * @param rutaArchivo the rutaArchivo to set
     */
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the destino
     */
    public String getDestinatarios() {
        return destinatarios;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestinatarios(String destino) {
        this.destinatarios = destino;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void enviarCorreo(){
        Controlador controlador=new Controlador();
        if (controlador.enviarCorreo(this))
            System.out.println("correo enviado"); //cambiar por ventanas de la interfaz
        else
            System.out.println("ERROR correo no enviado"); //cambiar por ventanas de la interfaz.
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }
    
    
}
