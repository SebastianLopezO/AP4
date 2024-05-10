package logic;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import bean.Menu;
import bean.Profesor;
import bean.Set;
import utility.Clr;
import utility.FileManager;
public class Menus extends Menu {

    private final ArrayList<Profesor> tiempoCompleto;
    private final ArrayList<Profesor> catedra;
    private final ArrayList<Profesor> ocacional;


    // OPERACIONES
    private final Set<Profesor> Set_UNION = (s1, s2) -> {
        java.util.Set<Profesor> union = new HashSet<>(s1);
        union.addAll(new HashSet<>(s2));
        return union;
    };

    private final Set<Profesor> Set_INTERSECION = (s1, s2) -> {
        java.util.Set<Profesor> inte = new HashSet<>(s1);
        inte.retainAll(new HashSet<>(s2));
        return inte;
    };

    private final Set<Profesor> Set_DIFERENCIA = (s1, s2) -> {
        java.util.Set<Profesor> dif = new HashSet<>(s1);
        dif.removeAll(new HashSet<>(s2));
        return dif;
    };

    public Menus(String title) {
        super(title);
        tiempoCompleto = new ArrayList<>();
        catedra = new ArrayList<>();
        ocacional = new ArrayList<>();
    }

    @Override
    public void menu() {
        chargeData();
        while (true) {
            int opt;
            try {
                opt = Integer.parseInt(
                        input("""
                                1- Listar y contar los profesores que son de tiempo completo solamente.
                                2- Listar y contar los profesores que son de catedra solamente.
                                3- Listar y contar los profesores que son ocasionales solamente.
                                4- Lista y contar el total de profesores.
                                5- Listar y contar de profesores de tiempo completo y a la vez que sean de catedra.
                                6- Listar y contar los profesores que son ocasionales y a la vez de catedra.
                                7- Listar y contar profesores que tengan las 3 condiciones (Catedra, completo y ocasional).
                                8- Cantidad de hombre y mujeres por cada tipo de contrato.
                                9- Listar y contar profesores por cada facultad.
                                10- Operaciones entre conjuntos.
                                11- Ingresar profesor.
                                0- Salir.
                                """));
            } catch (Exception e) {
                e.printStackTrace();
                // control de la excepcion.
                if (e.toString().contains("Cannot parse null string"))
                    return;
                msg("Se necesita una opcion valida.");
                continue;
            }

            switch (opt) {
                case 0: {
                    return;
                }

                case 1: // Listar y contar los profesores que son de tiempo completo solamente
                    /*
                     * OPERACION:
                     * (TIEMPO_COMPLETO - CATEDRA) - OCACIONALES
                     *
                     */

                    ArrayList<Profesor> soloProfesores = diferencia(diferencia(tiempoCompleto, catedra), ocacional);
                    msg("Hay " + soloProfesores.size() + " profesores que son solo de tiempo completo, y estos son...");
                    msgScroll(Show(soloProfesores));
                    break;

                case 2: // Listar y contar los profesores que son de catedra solamente
                    soloProfesores = diferencia(diferencia(catedra, tiempoCompleto), ocacional);
                    msg("Hay " + soloProfesores.size() + " profesores que son solo de catedra, y estos son...");
                    msgScroll(Show(soloProfesores));

                    break;

                case 3: // Listar y contar los profesores que son ocasionales solamente
                    soloProfesores = diferencia(diferencia(ocacional, tiempoCompleto), catedra);
                    msg("Hay " + soloProfesores.size() + " profesores que son solo ocacionales, y estos son...");
                    msgScroll(Show(soloProfesores));
                    break;

                case 4: // Lista y contar el total de profesores
                    soloProfesores = union(union(tiempoCompleto, ocacional), catedra);
                    msg("Hay " + soloProfesores.size() + " profesores en total, y estos son...");
                    msgScroll(Show(soloProfesores));
                    break;

                case 5: // Listar y contar de profesores de tiempo completo y a la vez que sean de
                    // catedra
                    soloProfesores = intersecion(tiempoCompleto, catedra);
                    msg("Hay " + soloProfesores.size()
                            + " profesores que son de tiempo completo y a la vez de catedra, y estos son...");
                    msgScroll(Show(soloProfesores));
                    break;

                case 6: // Listar y contar los profesores que son ocasionales y a la vez de catedra
                    soloProfesores = intersecion(ocacional, catedra);
                    msg("Hay " + soloProfesores.size()
                            + " profesores que son ocasionales y a la vez de catedra, y estos son...");
                    msgScroll(Show(soloProfesores));
                    break;

                case 7: // Listar y contar profesores que tengan las 3 condiciones (Catedra, completo y
                    // ocasional)
                    soloProfesores = intersecion(intersecion(tiempoCompleto, ocacional), catedra);
                    msg("Hay " + soloProfesores.size()
                            + " profesores que son ocasionales y a la vez de catedra, y estos son...");
                    msgScroll(Show(soloProfesores));
                    break;

                case 8: // Cantidad de hombre y mujeres por cada tipo de contrato
                    int[] Mujeres = new int[3], Hombres = new int[3];
                    for (Profesor profesor : tiempoCompleto) {
                        if ((profesor.getSexo() + "").contains("F")) {
                            Mujeres[0]++;
                        } else
                            Hombres[0]++;
                    }
                    for (Profesor profesor : ocacional) {
                        if ((profesor.getSexo() + "").contains("F")) {
                            Mujeres[1]++;
                        } else
                            Hombres[1]++;
                    }
                    for (Profesor profesor : catedra) {
                        if ((profesor.getSexo() + "").contains("F")) {
                            Mujeres[2]++;
                        } else
                            Hombres[2]++;
                    }
                    msg("Tiempo Completo: \n    Hombres: " + Hombres[0] + "\n    Mujeres: " + Mujeres[0]
                            + "\nOcacional: \n    Hombres: " + Hombres[1] + "\n    Mujeres: " + Mujeres[1]
                            + "\nCatedra: \n    Hombres: " + Hombres[2] + "\n    Mujeres: " + Mujeres[2]);
                    break;

                case 9: // Listar y contar profesores por cada facultad
                    int[] Facultades = new int[6];
                    soloProfesores = union(union(tiempoCompleto, ocacional), catedra);
                    for (Profesor profesor : soloProfesores) {
                        switch (profesor.getFacultad()) {
                            case "Ingenieria":
                                Facultades[0]++;
                                break;
                            case "Deportes":
                                Facultades[1]++;
                                break;
                            case "Comunicacion":
                                Facultades[2]++;
                                break;
                            case "Administracion":
                                Facultades[3]++;
                                break;
                            case "Idiomas":
                                Facultades[4]++;
                                break;
                            case "CienciasBasicas":
                                Facultades[5]++;
                                break;
                            default:
                                msg("que a pasao tio");
                                break;
                        }
                    }
                    msg("Ingenieria: " + Facultades[0] + "\n Deportes: " + Facultades[1] + "\nComunicación: " + Facultades[2] + "\nAdministracion: " + Facultades[3] + "\nIdiomas: " + Facultades[4] + "\nCiencias Basicas: " + Facultades[5]);
                    break;

                case 10:
                    /*
                     * Esta función permite realizar una operación entre dos conjuntos de profesores
                     * basada en un atributo seleccionado y una condición específica.
                     *
                     * Pedir datos:
                     *   - Conjuntos de profesores
                     *   - Operación a realizar (Unión, Intersección, Diferencia)
                     *
                     * Crear Condicional:
                     *   - Aplicar una operación diferente dependiendo de la selección del usuario (igual ==, diferente != , mayor > , menor <).
                     *
                     * Invocar función adaptativa que realiza la operación deseada.
                     *
                     * @return mensaje que indica el resultado de la operación entre los conjuntos de profesores.
                     */

                    // <- SELECIONAR OPERACION ->

                    String[] opts = {"Union", "Intersecion", "Diferencia"};
                    String selec = (String) inputSelect("Que operacion desea realizar?", "Operacion?", opts);

                    Set<Profesor> Set_SELECIONADA;
                    if (selec.equals(opts[0])) { // union
                        Set_SELECIONADA = Set_UNION;
                    } else if (selec.equals(opts[1])) { // intersecion
                        Set_SELECIONADA = Set_INTERSECION;
                    } else if (selec.equals(opts[2])) { // diferencia
                        Set_SELECIONADA = Set_DIFERENCIA;
                    } else {
                        System.out.println("[!] De donde chota se colo la opcion" + selec);
                        continue;
                    }

                    // <- SELECIONAR CONJUNTOS ->

                    ArrayList<String> conjuntos = new ArrayList<>(Arrays.asList("Profesores de tiempo completo", "Profesores de catedra", "Profesores ocacionales"));

                    // Selecionar primer conjunto
                    String c1 = (String) inputSelect("Seleccione el primer conjunto: ", "Conjunto 1", conjuntos.toArray(new String[conjuntos.size()]));
                    ArrayList<Profesor> conjunto1 = null;
                    if (conjuntos.get(0).equals(c1)) {
                        conjunto1 = tiempoCompleto;
                    } else if (conjuntos.get(1).equals(c1)) {
                        conjunto1 = catedra;
                    } else if (conjuntos.get(2).equals(c1)) {
                        conjunto1 = ocacional;
                    } else System.out.println("xd?");
                    conjuntos.remove(c1);

                    // Seleccionar segundo conjunto
                    String c2 = (String) inputSelect("Seleccione el segundo conjunto: ", "Conjunto 2", conjuntos.toArray(new String[conjuntos.size()]));
                    ArrayList<Profesor> conjunto2 = null;
                    if (c2.contains("completo")) {
                        conjunto2 = tiempoCompleto;
                    } else if (c2.contains("catedra")) {
                        conjunto2 = catedra;
                    } else if (c2.contains("ocacionales")) {
                        conjunto2 = ocacional;
                    } else System.out.println("xd?");

                    // <- CREAR CONDICIONAL ->

                    ArrayList<String> atrs = new ArrayList<>(Arrays.asList("Titulo", "Facultad", "Sexo", "Horas que dicta", "Asignaturas que dicta", "Año de nacimiento"));
                    String atr = (String) inputSelect("Selecione atributo por el cual se va a filtrar", "Atributo", atrs.toArray(new String[atrs.size()]));

                    Predicate<Profesor> condicion_final = profesor -> {return true; };
                    boolean numeric = false;

                    /*
                        CONDICONAL SIMPLE PARA ATRIBUTOS STRING
                            - Se captura el valor del atributo
                            - Se crea la condicion para el atributo
                    */
                    if (atrs.get(0).equals(atr)) { // Titulo
                        String titulo = RecordDegree();
                        condicion_final = profesor -> profesor.getTitulo().equals(titulo);

                    } else if (atrs.get(1).equals(atr)) { // Facultad
                        String facultad = RecordDepartament();
                        condicion_final = profesor -> profesor.getTitulo().equals(facultad);

                    } else if (atrs.get(2).equals(atr)) { // Sexo
                        String[] sexos = {"M", "F"};
                        Character sexo = (Character) inputSelect("Selecione el sexo (Masculino, Femenino)", "Sexo", sexos);
                        condicion_final = profesor -> profesor.getSexo() == sexo;
                    }

                    /*
                        CONDICIONAL PARA ATRIBUTOS NUMERICOS
                            - Se registra el numero que se va a utilizar en los condicionales
                            - Se describe el nombre de la funcion del getter
                            - La flag numeric se vuelve true
                     */
                    int numero;
                    String atributo;
                    if (atrs.get(3).equals(atr)) { // Horas que dicta
                        numero = RecordDictationHours();
                        atributo = "getHorasDictadas";
                        numeric = true;

                    } else if (atrs.get(4).equals(atr)) { // Asignaturas
                        numero = RecordDictationCourses();
                        atributo = "getAsignaturasDicta";
                        numeric = true;

                    } else if (atrs.get(5).equals(atr)) { // Año de nacimento
                        Pattern m = Pattern.compile("\\d{4}");
                        String num;
                        do {
                            num = input("Escriba el año [yyyy]: ");
                        } while (!m.matcher(num).matches());
                        numero = Integer.parseInt(num);
                        atributo = "getAnoNacimiento";
                        numeric = true;

                    } else {
                        numero = 0;
                        atributo = "";
                    }

                    /*
                        CAMBIOS EN CONDICIONES
                            - Datos numericos
                                - La condicion verifica que operacion se escogio y la implementa.

                            - Datos no numericos
                                - Se verifica si la condicion sera de igualdad o no

                    */

                    if (numeric) { // Datos numericos
                        String comparacion = ChangeCondition();
                        condicion_final = profesor -> {
                            try {
                                Method getter = Profesor.class.getMethod(atributo);
                                int valorAtributo = (int) getter.invoke(profesor);
                                switch (comparacion) {
                                    case "<":
                                        return valorAtributo < numero;
                                    case ">":
                                        return valorAtributo > numero;
                                    case "==":
                                        return valorAtributo == (numero);
                                    case "!=":
                                        return valorAtributo != (numero);
                                    default:
                                        System.out.println("Operación no soportada: " + comparacion);
                                        return false;
                                }
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                                return false;
                            }
                        };
                    } else { // Datos no numericos
                        ArrayList<String> oprs = new ArrayList<>(Arrays.asList("Igual", "Diferente"));
                        String opr = (String) inputSelect("Bajo que condicion quiere operar?", "Condicion", oprs.toArray(new String[oprs.size()]));
                        if (oprs.get(1).equals(opr)) condicion_final = condicion_final.negate();
                    }

                    // <- INVOCACION DE LA FUNCION ADAPTABLE + MOSTRAR ->
                    msgScroll(Show(funcionAdaptativa(conjunto1, conjunto2, condicion_final, Set_SELECIONADA)));
                    break;
                case 11:
                    String cedula = ValidateID();
                    String Nombre = ValidateRegex("[a-zA-Z\\s]+", "Ingrese el nombre completo");
                    char sexo = ValidateGen();
                    String[] opts2 = { "Artes","Humanidades","Ciencias","Ingenieria","Derecho","Medicina","Negocios","Agricultura","Arquitectura","Administracion","Deportes","Idiomas","Comunicacion"};
                    String Facultad = (String) inputSelect("Seleccione el genero", "genero", opts2);
                    String[] opts3 = { "Pregrado", "Especializacion", "Maestria", "Doctorado" };
                    String titulo = (String) inputSelect("Seleccione el genero", "Genero", opts3);
                    int CantidadAsiganturas = Integer
                            .parseInt(ValidateRegex("[1-9]|10", "Ingrese la cantidad de asignaturas que dicta"));
                    int CantidadHoras = Integer
                            .parseInt(ValidateRegex("[1-9]|1[0-9]|20",
                                    "Ingrese la cantidad de horas de clase dictadas por semana"));
                    Date FechaNacimineto = inputDate();
                    Profesor ProfesorNuevo = new Profesor(cedula, Nombre, Facultad, titulo, sexo, CantidadAsiganturas,
                            CantidadHoras, FechaNacimineto);
                    TypeTeacher(ProfesorNuevo, "Tiempo Completo");
                    TypeTeacher(ProfesorNuevo, "Catedra");
                    TypeTeacher(ProfesorNuevo, "Ocasional");
                    break;

                default:
                    msg("Opcion invalida.");
                    break;
            }
        }
    }

    public ArrayList<Profesor> funcionAdaptativa(ArrayList<Profesor> c1, ArrayList<Profesor> c2,
                                                 Predicate<Profesor> condicion, Set<Profesor> set) {

        System.out.println("[ FUNCION ADAPTABLE ]");
        java.util.Set<Profesor> filtro1 = new HashSet<>();
        for (Profesor p : c1) {
            if (condicion.test(p)) {
                filtro1.add(p);
                System.out.println("[!] Filtro 2, coincidencia encontrada" + p.toString());
            } else System.out.println("[!] Invalido: " + p.getCC());

        }

        java.util.Set<Profesor> filtro2 = new HashSet<>();
        for (Profesor p : c2) {
            if (condicion.test(p)) {
                filtro2.add(p);
                System.out.println("[!] Filtro 2, coincidencia encontrada" + p.toString());
            } else System.out.println("[!] Invalido: " + p.getCC());

        }

        return new ArrayList<>(set.realizar(filtro1, filtro2));
    }

    // OPERACIONES
    public static ArrayList<Profesor> union(ArrayList<Profesor> lists1, ArrayList<Profesor> lista2) {
        java.util.Set<Profesor> set = new HashSet<>(lists1);
        set.addAll(lista2);
        return new ArrayList<>(set);
    }

    public static ArrayList<Profesor> intersecion(ArrayList<Profesor> lists1, ArrayList<Profesor> lista2) {
        java.util.Set<Profesor> set1 = new HashSet<>(lists1);
        java.util.Set<Profesor> set2 = new HashSet<>(lista2);
        set1.retainAll(set2);
        return new ArrayList<>(set1);
    }

    public static ArrayList<Profesor> diferencia(ArrayList<Profesor> lists1, ArrayList<Profesor> lista2) {
        java.util.Set<Profesor> set1 = new HashSet<>(lists1);
        java.util.Set<Profesor> set2 = new HashSet<>(lista2);
        set1.removeAll(set2);
        return new ArrayList<>(set1);
    }

    public static boolean subconjuntoDe(ArrayList<Profesor> lists1, ArrayList<Profesor> lista2) {
        java.util.Set<Profesor> set1 = new HashSet<>(lists1);
        java.util.Set<Profesor> set2 = new HashSet<>(lista2);
        return set1.containsAll(set2);
    }

    // UTILIDAD

    // captura de datos

    private String RecordDepartament() {
        String[] facultades = {"Artes","Humanidades","Ciencias","Ingenieria","Derecho","Medicina","Negocios","Agricultura","Arquitectura","Administracion","Deportes","Idiomas","Comunicacion"};
        return (String) inputSelect("Seleccione la Facultad.", "Facultad", facultades);
    }

    private String RecordDegree() {
        String[] titulos = {"Pregrado", "Especializacion", "Maestria", "Doctorado"};
        return (String) inputSelect("Seleccione el Titulo", "Titulo", titulos);
    }

    private int RecordDictationCourses() {
        Pattern m = Pattern.compile("0*10|0*\\d");
        String num;
        do {
            num = input("Escriba el numero de asignaturas que dicta [1-10]: ");
        } while (!m.matcher(num).matches());
        return Integer.parseInt(num);
    }

    private int RecordDictationHours() {
        Pattern m = Pattern.compile("0*20|0*1\\d|0*\\d");
        String num;
        do {
            num = input("Escriba las horas que ha dictadas [1-20]: ");
        } while (!m.matcher(num).matches());
        return Integer.parseInt(num);
    }

    // funcion a
    private String ChangeCondition() {
        ArrayList<String> oprs = new ArrayList<>(Arrays.asList("Igual", "Diferente", "Mayor", "Menor"));
        String opr = (String) inputSelect("Bajo que condicion quiere operar?", "Condicion", oprs.toArray(new String[oprs.size()]));
        if (oprs.get(0).equals(opr)) { // igual
            return "==";
        } else if (oprs.get(1).equals(opr)) { // Diferente
            return "!=";
        } else if (oprs.get(2).equals(opr)) { // Mayor
            return ">";
        } else if (oprs.get(3).equals(opr)) { // Menor
            return "<";
        }
        return "==";
    }

    // otras utilidades

    public String Show(ArrayList<Profesor> d) {
        Iterator<Profesor> i = d.iterator();
        StringBuilder s = new StringBuilder();
        if (!d.isEmpty()) {
            s.append("Mostrando " + d.size() + " Entradas.\n");
            while (i.hasNext()) {
                Profesor p = i.next();
                s.append("CC: " + p.getCC() + "\n");
                s.append("Nombre Completo: " + p.getNombreCompleto() + "\n");
                s.append("Facultad: " + p.getFacultad() + "\n");
                s.append("Titulo: " + p.getTitulo() + "\n");
                s.append("Sexo: " + p.getSexo() + "\n");
                s.append("Asignaturas Dictadas: " + p.getAsignaturasDicta() + "\n");
                s.append("Horas Dictadas: " + p.getHorasDictadas() + "\n");
                s.append("Fecha Nacimiento: " + (p.getFechaNacimiento().toLocaleString().split(",")[0]) + "\n\n");
            }
        }

        return s.toString();
    }


    public void chargeData() {
        try {
            String root = System.getProperty("user.dir");
            FileManager f = new FileManager(root + "/src/Data/Docentes.txt");
            System.out.println(Clr.B + "[ CARGA DE DATOS ]" + Clr.RT);

            ArrayList<String> lineas = f.readFileArrayList();
            Iterator<String> i = lineas.iterator();

            while (i.hasNext()) {
                String[] p = i.next().split(";");
                Profesor docente = new Profesor(
                        p[1], // String CC
                        p[2], // String nombreCompleto
                        p[4], // String facultad
                        p[5], // String titulo
                        p[3].charAt(0), // char sexo
                        Integer.parseInt(p[6]), // int asignaturasDicta
                        Integer.parseInt(p[7]), // int horasDictadas
                        new SimpleDateFormat("yyyy-MM-dd").parse(p[8]) // Date fechaNacimiento
                );

                if (p[0].contains("T")) {
                    tiempoCompleto.add(docente);
                }
                if (p[0].contains("C")) {
                    catedra.add(docente);
                }
                if (p[0].contains("O")) {
                    ocacional.add(docente);
                }
                System.out.println(Clr.G  + "[+] Añadiendo Docente: " + Clr.RT +(Arrays.stream(p).toList()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // Validaciones
    private String ValidateRegex(String patron, String msginput) {// metodo para realizar todas las valdiaciones con
        // expresiones regulares
        Pattern Patron = Pattern.compile(patron);
        String input;
        while (true) {
            input = input(msginput).trim();
            if (!Patron.matcher(input).matches()) { // validar el formato correcto
                msg("Formato invalido");
            } else
                return input;
        }
    }

    private String ValidateID() {
        String input;
        while (true) {
            input = ValidateRegex("\\d+", "Ingrese la cedula");
            boolean bandera = true;
            for (Profesor profesor : union(union(tiempoCompleto, ocacional), catedra)) {
                if (profesor.getCC().equals(input)) {
                    bandera = false;
                    msg("La cedula ya esta en uso");
                    break;
                }
            }
            if (bandera)
                return input;
        }
    }

    private char ValidateGen() {
        String[] opts = { "Masculino", "Femenino" };
        String input = (String) inputSelect("Seleccione el genero", "genero", opts);
        Pattern PatronM = Pattern.compile("M");
        if (PatronM.matcher(input).find()) {
            return 'M';
        } else
            return 'F';
    }

    private void TypeTeacher(Profesor profesornuevo, String tipo) {
        String[] sino = {"Si","No"};
        String SelectTipo = (String) inputSelect("El profesor es de "+tipo+"?", "tipo de profesor", sino);
        if(SelectTipo.equals(sino[0])){
            if(tipo.equals("Tiempo Completo")) tiempoCompleto.add(profesornuevo);
            else if(tipo.equals("Catedra")) catedra.add(profesornuevo);
            else ocacional.add(profesornuevo);
        }
    }

}