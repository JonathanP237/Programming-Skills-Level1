import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio3 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        ArrayList<Usuario> registrados = new ArrayList<Usuario>();
        ArrayList<Cita> citas = new ArrayList<Cita>();
        String especialidad[] = { "Medicina General", "Atención de Emergencias", "Análisis Clínicos", "Cardiología",
                "Neurología", "Nutrición", "Fisioterapia", "Traumatología", "Medicina Interna" };
        String horaDia[] = { "8:00", "8:30", "9:00", "9:30", "10:00", "10:30" };
        String horaTarde[] = { "14:00", "14:30", "15:00", "15:30", "16:00", "16:30" };
        String medico[] = { "medico1", "medico2", "medico3" };
        Usuario usActual = new Usuario();
        String id;
        registrados.add(new Usuario("6", "Luisa1015"));
        registrados.add(new Usuario("7", "Pedro1016"));
        System.out.println("Citas Médicas");
        do {
            System.out.println("\nIngrese su id o SALIR para salir:");
            id = in.readLine();
            usActual = login(id, registrados);
            if (usActual != null && usActual.getActivo() == true) {
                menu(usActual, citas, especialidad, horaDia, horaTarde, medico);
            } else if (!id.toUpperCase().equals("SALIR")) {
                System.out.println("Usuario no registrado o bloqueado.");
            }
        } while (!id.toUpperCase().equals("SALIR"));
    }

    private static void menu(Usuario usActual, ArrayList<Cita> citas, String[] especialidad, String[] horaDia,
            String[] horaTarde, String[] medico) {
        int option = 0;
        System.out.println("\nBienvenido " + usActual.getId());
        do {
            Cita cita = new Cita();
            System.out.println("\nElija una opción:");
            System.out.println("1-Asignar cita\n2-Ver citas\n3-Salir");
            option = validarEntero();            
                switch (option) {
                    case 1:
                    if (usActual.getCita().size() < 3) {
                        System.out.println("\nElija una especialidad:");
                        for (int i = 0; i < especialidad.length; i++) {
                            System.out.println((i + 1) + "-" + especialidad[i]);
                        }
                        int esp = validarEntero();
                        if (esp > 0 && esp <= especialidad.length && validaEsp(especialidad[esp - 1], usActual)) {
                            cita.setEspecialidad(especialidad[esp - 1]);
                            System.out.println("\nEspecialidad: " + cita.getEspecialidad());
                            System.out.println("\nElija Mañana o tarde:");
                            System.out.println("1-Mañana\n2-Tarde");
                            int franja = validarEntero();
                            switch (franja) {
                                case 1:
                                    System.out.println("\nElija una hora:");
                                    for (int i = 0; i < horaDia.length; i++) {
                                        System.out.println((i + 1) + "-" + horaDia[i]);
                                    }
                                    int hora = validarEntero();
                                    if (hora > 0 && hora <= horaDia.length) {
                                        System.out.println("\nElija un médico:");
                                        for (int i = 0; i < medico.length; i++) {
                                            System.out.println((i + 1) + "-" + medico[i]);
                                        }
                                        int med = validarEntero();
                                        if (med > 0 && med <= medico.length) {
                                            cita.setMedico(medico[med - 1]);
                                            cita.setHora(horaDia[hora - 1]);
                                            cita.setUsuario(usActual);
                                            citas.add(cita);
                                            usActual.addCita(cita);
                                            System.out.println("\nCita asignada.");
                                        } else {
                                            System.out.println("\nOpción inválida.");
                                            break;
                                        }
                                        break;
                                    } else {
                                        System.out.println("\nOpción inválida.");
                                        break;
                                    }
                                case 2:
                                    System.out.println("\nElija una hora:");
                                    for (int i = 0; i < horaTarde.length; i++) {
                                        System.out.println((i + 1) + "-" + horaTarde[i]);
                                    }
                                    hora = validarEntero();
                                    if (hora > 0 && hora <= horaTarde.length) {
                                        cita.setHora(horaTarde[hora - 1]);
                                        System.out.println("\nElija un médico:");
                                        for (int i = 0; i < medico.length; i++) {
                                            System.out.println((i + 1) + "-" + medico[i]);
                                        }
                                        int med = validarEntero();
                                        if (med > 0 && med <= medico.length) {
                                            cita.setMedico(medico[med - 1]);
                                            cita.setUsuario(usActual);
                                            citas.add(cita);
                                            usActual.addCita(cita);
                                            System.out.println("\nCita asignada.");
                                        } else {
                                            System.out.println("\nOpción inválida.");
                                            break;
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("\nOpción inválida.");
                                    break;
                            }
                        } else {
                            System.out.println(
                                    "\nOpción inválida o ya tiene asignada una cita con esa especialidad.");
                            break;
                        }
                        break;
                    } else {
                        System.out.println("\nYa alcanzó el máximo de citas.");
                        break;
                    }
                    case 2:
                        System.out.println("\nCitas asignadas:");
                        if (usActual.getCita().size() > 0) {
                            for (int i = 0; i < usActual.getCita().size(); i++) {
                                System.out.println("\nCita " + (i + 1));
                                System.out.println("Especialidad: " + usActual.getCita().get(i).getEspecialidad());
                                System.out.println("Hora: " + usActual.getCita().get(i).getHora());
                                System.out.println("Médico: " + usActual.getCita().get(i).getMedico());
                            }
                        } else {
                            System.out.println("\nNo tiene citas asignadas.");
                        }
                        break;
                    case 3:
                        System.out.println("\nGracias por usar el sistema.");
                        break;
                    default:
                        System.out.println("\nOpción inválida.");
                        break;
                }
            
        } while (option != 3);
    }

    private static boolean validaEsp(String esp, Usuario usActual) {
        boolean valida = true;
        for (int i = 0; i < usActual.getCita().size(); i++) {
            if (usActual.getCita().get(i).getEspecialidad().equals(esp)) {
                valida = false;
                break;
            }
        }
        return valida;
    }

    private static int validarEntero() {
        int entero = 0;
        try {
            entero = Integer.parseInt(in.readLine());
        } catch (Exception e) {
            entero = -1;
        }
        return entero;
    }

    public static Usuario login(String id, ArrayList<Usuario> registrados) throws IOException {
        int intentos = 0;
        Usuario usActual = new Usuario();
        for (int i = 0; i < registrados.size(); i++) {
            if (registrados.get(i).getId().equals(id)) {
                usActual = registrados.get(i);
                break;
            }
        }
        while (intentos < 3 && usActual.getActivo() == true) {
            if (usActual.getId() != null) {
                System.out.println("\nIngrese Contraseña:");
                String pass = in.readLine();
                if (usActual.getPass().equals(pass)) {
                    break;
                } else {
                    intentos++;
                    System.out.println("Contraseña incorrecta, intentos restantes: " + (3 - intentos));
                    if (intentos == 3) {
                        usActual.setActivo(false);
                        System.out.println("\nSu cuenta ha sido bloqueada.");
                    }
                }
            } else {
                usActual = null;
                break;
            }
        }
        return usActual;
    }
}

class Usuario {
    private String id, pass;
    private boolean activo = true;
    private final int maxCitas = 3;
    private ArrayList<Cita> citas = new ArrayList<Cita>();

    public Usuario() {
    }

    public Usuario(String id, String pass) {
        this.id = id;
        this.pass = pass;
        this.activo = true;
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ArrayList<Cita> getCita() {
        return citas;
    }

    public void addCita(Cita cita) {
        citas.add(cita);
    }
}

class Cita {
    private String hora, especialidad, medico;
    Usuario usuario = new Usuario();

    public Cita() {
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
}