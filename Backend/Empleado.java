package Actividades_poo; // Asegúrate de que la ruta del paquete sea correcta
import java.util.Scanner;
import java.text.DecimalFormat;

public class Empleado {
    private String nombre;
    private int identificacion;
    private String cargo;
    private double salario;
    private int horasTrabajadas;
    private static final double TARIFA_POR_HORA = 10.0; // Constante para tarifa

    public Empleado(String nombre, int identificacion, String cargo, double salario, int horasTrabajadas) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.cargo = cargo;
        this.salario = salario;
        this.horasTrabajadas = horasTrabajadas;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }
    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
    public void registrarEmpleado() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el nombre del empleado: ");
        setNombre(sc.nextLine());
        System.out.println("Escribe la identificación del empleado: ");
        setIdentificacion(sc.nextInt());
        sc.nextLine(); // Limpiar buffer
        System.out.println("Escribe el cargo del empleado: ");
        setCargo(sc.nextLine());
    }

    public void asignarHorario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el número de horas trabajadas: ");
        setHorasTrabajadas(sc.nextInt());
    }

    public void calcularSalario() {
        DecimalFormat df = new DecimalFormat("###,###.000");
        setSalario(getHorasTrabajadas() * TARIFA_POR_HORA);
        System.out.println("El salario del empleado es: " + df.format(salario) + " pesos");
    }

    public static void main(String[] args) {
        Empleado empleado = new Empleado("", 0, "", 0.0, 0);
        empleado.registrarEmpleado();
        empleado.asignarHorario();
        empleado.calcularSalario();

        EmpleadoDAO dao = new EmpleadoDAO();
        dao.registrarEmpleado(empleado);
        dao.listarEmpleados(); // Para verificar el registro
    }
}
