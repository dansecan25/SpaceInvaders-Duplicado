package proyecto1.Imagenes;

import javafx.scene.image.Image;

import java.io.InputStream;

/*
usar singleton para cargar cada imagen que se usa solo una vez
 */

/**
 * La clase Imagenes, un Singleton para cargar las imagenes utilizadas solo una vez.
 */
public class Imagenes{
    private static Imagenes INSTANCIA = null;

    public static final String IMG_TITULO = "/Imagenes/titulo.png";
    public static final String IMG_FONDO = "/Imagenes/fondo.png";
    public static final String IMG_BOTONSTART = "/Imagenes/START.png";
    public static final String IMG_UFO1 = "/Imagenes/UFO1.png";
    public static final String IMG_UFO2 = "/Imagenes/UFO2.png";
    public static final String IMG_UFO3 = "/Imagenes/UFO3.png";
    public static final String IMG_UFOBOSS1 = "/Imagenes/boss.png";
    public static final String IMG_UFOBOSS2 = "/Imagenes/boss2.png";
    public static final String IMG_UFOBOSS3 = "/Imagenes/boss3.png";
    public static final String IMG_UFOBOSS4 = "/Imagenes/boss4.png";
    public static final String IMG_BOTONCONFIGURACION = "/Imagenes/configIC.png";
    public static final String IMG_NAVEUSUARIO = "/Imagenes/navecita.png";
    public static final String IMG_BOTONMUSICAON = "/Imagenes/sonidoON.png";
    public static final String IMG_FONDO1 = "/Imagenes/fondo1.png";
    public static final String IMG_FONDO2 = "/Imagenes/fondo2.png";
    public static final String IMG_FONDO3 = "/Imagenes/fondo3.png";
    public static final String IMG_FONDO4 = "/Imagenes/fondo4.png";
    public static final String IMG_FONDO5 = "/Imagenes/fondo5.png";
    public static final String IMG_LASER = "/Imagenes/laser.png";
    public static final String IMG_EXPLOSION = "/Imagenes/explosion.png";
    public static final String IMG_BOTONEXIT = "/Imagenes/botonEXIT.png";
    public static final String IMG_NAVEANIMACION = "/Imagenes/navePantallaPrincipal.png";



    // imagenes disponibles
    private Image naveUsuario = null;
    private Image titulo = null;
    private Image fondo = null;
    private Image botonStart = null;
    private Image ufo1 = null;
    private Image ufo2 = null;
    private Image ufo3 = null;
    private Image ufoBoss1 = null;
    private Image ufoBoss2 = null;
    private Image ufoBoss3 = null;
    private Image ufoBoss4 = null;
    private Image botonConfiguracion = null;
    private Image botonMusicaON = null;
    private Image fondo1 = null;
    private Image fondo2 = null;
    private Image fondo3 = null;
    private Image fondo4 = null;
    private Image fondo5 = null;
    private Image laser = null;
    private Image explosion = null;
    private Image botonExit = null;
    private Image naveAnimacion=null;
    //private double parametroEncoger = 0.8;


    // constructor privado para que solo exista una instancia
    private Imagenes(){
        cargarImagenes();
    }

    /**
     * Obtener instancia imagenes.
     *
     * @return imagenes
     */
// metodo sincronizado para evitar problemas con hilos
    public static synchronized Imagenes getInstancia(){
        if (INSTANCIA == null){
            INSTANCIA = new Imagenes();
        }
        return INSTANCIA;
    }

    //llama a todos los cargar imagenes de todas las imagenes
    private void cargarImagenes(){


        naveUsuario = cargarImagen(IMG_NAVEUSUARIO);
        titulo = cargarImagen(IMG_TITULO);
        fondo = cargarImagen(IMG_FONDO);
        botonStart = cargarImagen(IMG_BOTONSTART);
        ufo1 = cargarImagen(IMG_UFO1);
        ufo2 = cargarImagen(IMG_UFO2);
        ufo3 = cargarImagen(IMG_UFO3);
        ufoBoss1 = cargarImagen(IMG_UFOBOSS1);
        ufoBoss2 = cargarImagen(IMG_UFOBOSS2);
        ufoBoss3 = cargarImagen(IMG_UFOBOSS3);
        ufoBoss4 = cargarImagen(IMG_UFOBOSS4);
        botonConfiguracion = cargarImagen(IMG_BOTONCONFIGURACION);
        botonMusicaON = cargarImagen(IMG_BOTONMUSICAON);
        fondo1 = cargarImagen(IMG_FONDO1);
        fondo2 = cargarImagen(IMG_FONDO2);
        fondo3 = cargarImagen(IMG_FONDO3);
        fondo4 = cargarImagen(IMG_FONDO4);
        fondo5 = cargarImagen(IMG_FONDO5);
        laser = cargarImagen(IMG_LASER);
        explosion = cargarImagen(IMG_EXPLOSION);
        botonExit = cargarImagen(IMG_BOTONEXIT);
        naveAnimacion = cargarImagen(IMG_NAVEANIMACION);
    }

    /**
     * Carga imagenes
     * @param nombreImagen:String
     * @return resultado: Image
     */
    public Image cargarImagen(String nombreImagen){
        Image resultado = null;
        try {
             InputStream inputStream = this.getClass().getResourceAsStream(nombreImagen);
             resultado = new Image(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * Obtener nave usuario.
     *
     * @return  nave usuario
     */
    public Image getNaveUsuario() {
        return naveUsuario;
    }

    /**
     * Obtener titulo.
     *
     * @return  titulo
     */
    public Image getTitulo() {
        return titulo;
    }

    /**
     * Obtener fondo.
     *
     * @return fondo
     */
    public Image getFondo() {
        return fondo;
    }

    /**
     * Obtener boton start.
     *
     * @return boton start
     */
    public Image getBotonStart() {
        return botonStart;
    }

    /**
     * Obtnener ufo 1.
     *
     * @return ufo 1
     */
    public Image getUfo1() {
        return ufo1;
    }

    /**
     * Obtener ufo 2.
     *
     * @return ufo 2
     */
    public Image getUfo2() {
        return ufo2;
    }

    /**
     * Obtener ufo 3.
     *
     * @return ufo 3
     */
    public Image getUfo3() {
        return ufo3;
    }

    /**
     * Obtener ufo boss.
     *
     * @return ufo boss
     */
    public Image getUfoBoss1() {
        return ufoBoss1;
    }

    public Image getUfoBoss2() {
        return ufoBoss2;
    }

    public Image getUfoBoss3() {
        return ufoBoss3;
    }

    public Image getUfoBoss4() {
        return ufoBoss4;
    }

    /**
     * Obtener boton configuracion.
     *
     * @return boton configuracion
     */



    public Image getBotonConfiguracion() {
        return botonConfiguracion;
    }

    /**
     * Obtener boton musica on image.
     *
     * @return botonMusicaON
     */
    public Image getBotonMusicaON(){
        return botonMusicaON;
    }

    /**
     * Obtener fondo 1.
     *
     * @return fondo 1
     */
    public Image getFondo1() {
        return fondo1;
    }

    /**
     * Obtiene fondo 2.
     *
     * @return fondo 2
     */
    public Image getFondo2() {
        return fondo2;
    }

    /**
     * Obtiene fondo 3.
     *
     * @return fondo 3
     */
    public Image getFondo3() {
        return fondo3;
    }

    /**
     * Obtiene fondo 4.
     *
     * @return fondo 4
     */
    public Image getFondo4() {
        return fondo4;
    }

    /**
     * Obtiene fondo 5.
     *
     * @return fondo 5
     */
    public Image getFondo5() {
        return fondo5;
    }

    /**
     * Obtiene laser.
     *
     * @return laser
     */
    public Image getLaser() { return laser; }

    /**
     * Obtiene explosion.
     *
     * @return explosion
     */
    public Image getExplosion() {return explosion;}


    /** Obtiene explosion.
            *
            * @return explosion
     */
    public Image getBotonExit() {return botonExit;}

    /**
     * Obtiene naveAnimacion
     * @return naveAnimacion: Image
     */
    public Image getNaveAnimacion(){return naveAnimacion;}
}

