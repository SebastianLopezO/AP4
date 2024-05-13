package logic;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import bean.Docente;
import bean.Menu;
import bean.Set;
import utility.Clr;
import utility.FileManager;
public class Menus extends Menu {

    private final ArrayList<Docente> fullTime;
    private final ArrayList<Docente> Adjunct;
    private final ArrayList<Docente> Occasional;


    // OPERACIONES
    private final Set<Docente> Set_UNION = (s1, s2) -> {
        java.util.Set<Docente> union = new HashSet<>(s1);
        union.addAll(new HashSet<>(s2));
        return union;
    };

    private final Set<Docente> Set_INTERSECION = (s1, s2) -> {
        java.util.Set<Docente> inte = new HashSet<>(s1);
        inte.retainAll(new HashSet<>(s2));
        return inte;
    };

    private final Set<Docente> Set_DIFERENCIA = (s1, s2) -> {
        java.util.Set<Docente> dif = new HashSet<>(s1);
        dif.removeAll(new HashSet<>(s2));
        return dif;
    };

    public Menus(String title) {
        super(title);
        fullTime = new ArrayList<>();
        Adjunct = new ArrayList<>();
        Occasional = new ArrayList<>();
    }

    @Override
    public void menu() {
        chargeData();
        while (true) {
            String option;
            String[] Options = {
                    "Docentes solo de tiempo completo",
                    "Docentes solo de catedra",
                    "Docentes solo ocasionales",
                    "Total de Docentes",
                    "Docentes de tiempo completo y de catedra a la vez",
                    "Docentes ocasionales y de catedra a la vez",
                    "Docentes de Catedra, tiempo completo y ocasional a la vez",
                    "Docentes por Genero y tipo de Contrato",
                    "Docentes por Facultad",
                    "Docentes segmentados",
                    "Ingresar docente",
                    "Salir"
            };
            option = (String) InputSelect("Selecciona una opción","Sistemad de Docentes", Options);

            switch (option) {
                case "Docentes solo de tiempo completo":
                    ArrayList<Docente> OnlyTeachers = diferencia(diferencia(fullTime, Adjunct), Occasional);
                    Msg("Hay " + OnlyTeachers.size() + " Docentes que son solo de tiempo completo: ");
                    msgScroll(Show(OnlyTeachers));
                    break;

                case "Docentes solo de catedra":
                    OnlyTeachers = diferencia(diferencia(Adjunct, fullTime), Occasional);
                    Msg("Hay " + OnlyTeachers.size() + " Docentes que son solo de catedra: ");
                    msgScroll(Show(OnlyTeachers));

                    break;

                case "Docentes solo ocasionales":
                    OnlyTeachers = diferencia(diferencia(Occasional, fullTime), Adjunct);
                    Msg("Hay " + OnlyTeachers.size() + " Docnetes que son solo ocacionales: ");
                    msgScroll(Show(OnlyTeachers));
                    break;

                case "Total de Docentes":
                    OnlyTeachers = union(union(fullTime, Occasional), Adjunct);
                    Msg("Hay " + OnlyTeachers.size() + " Docentes en total: ");
                    msgScroll(Show(OnlyTeachers));
                    break;

                case "Docentes de tiempo completo y de catedra a la vez":
                    OnlyTeachers = intersecion(fullTime, Adjunct);
                    Msg("Hay " + OnlyTeachers.size()
                            + " Docentes que son de tiempo completo y a la vez de catedra: ");
                    msgScroll(Show(OnlyTeachers));
                    break;

                case "Docentes ocasionales y de catedra a la vez":
                    OnlyTeachers = intersecion(Occasional, Adjunct);
                    Msg("Hay " + OnlyTeachers.size()
                            + " Docentes que son ocasionales y a la vez de catedra: ");
                    msgScroll(Show(OnlyTeachers));
                    break;

                case "Docentes de Catedra, tiempo completo y ocasional a la vez":
                    OnlyTeachers = intersecion(intersecion(fullTime, Occasional), Adjunct);
                    Msg("Hay " + OnlyTeachers.size()
                            + " Docentes que son ocasionales y a la vez de catedra: ");
                    msgScroll(Show(OnlyTeachers));
                    break;

                case "Docentes por Genero y tipo de Contrato":
                    int[] F = new int[3], M = new int[3];
                    for (Docente docente : fullTime) {
                        if ((docente.getGender() + "").contains("F")) {
                            F[0]++;
                        } else
                            M[0]++;
                    }
                    for (Docente docente : Occasional) {
                        if ((docente.getGender() + "").contains("F")) {
                            F[1]++;
                        } else
                            M[1]++;
                    }
                    for (Docente docente : Adjunct) {
                        if ((docente.getGender() + "").contains("F")) {
                            F[2]++;
                        } else
                            M[2]++;
                    }
                    Msg("Tiempo Completo: \n    Hombres: " + M[0] + "\n    Mujeres: " + F[0]
                            + "\nOcacional: \n    Hombres: " + M[1] + "\n    Mujeres: " + F[1]
                            + "\nCatedra: \n    Hombres: " + M[2] + "\n    Mujeres: " + F[2]);
                    break;

                case "Docentes por Facultad":
                    int[] Facultades = new int[6];
                    OnlyTeachers = union(union(fullTime, Occasional), Adjunct);
                    for (Docente docente : OnlyTeachers) {
                        switch (docente.getFaculty()) {
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
                                Msg("Opción no valida");
                                break;
                        }
                    }
                    Msg("Ingenieria: " + Facultades[0] + "\n Deportes: " + Facultades[1] + "\nComunicación: " + Facultades[2] + "\nAdministracion: " + Facultades[3] + "\nIdiomas: " + Facultades[4] + "\nCiencias Basicas: " + Facultades[5]);
                    break;

                case "Docentes segmentados":

                    String[] opts = {"Union", "Intersecion", "Diferencia"};
                    String selec = (String) InputSelect("¿Qué operacion desea realizar?", "Operacion?", opts);

                    Set<Docente> Set_SELECIONADA;
                    if (selec.equals(opts[0])) {
                        Set_SELECIONADA = Set_UNION;
                    } else if (selec.equals(opts[1])) {
                        Set_SELECIONADA = Set_INTERSECION;
                    } else if (selec.equals(opts[2])) {
                        Set_SELECIONADA = Set_DIFERENCIA;
                    } else {
                        System.out.println(Clr.R + "[!] Esta opción no es valida:" + selec + Clr.RT);
                        continue;
                    }

                    ArrayList<String> conjuntos = new ArrayList<>(Arrays.asList("Profesores de tiempo completo", "Profesores de catedra", "Profesores ocacionales"));

                    String c1 = (String) InputSelect("Seleccione el primer conjunto: ", "Conjunto 1", conjuntos.toArray(new String[conjuntos.size()]));
                    ArrayList<Docente> conjunto1 = null;
                    if (conjuntos.get(0).equals(c1)) {
                        conjunto1 = fullTime;
                    } else if (conjuntos.get(1).equals(c1)) {
                        conjunto1 = Adjunct;
                    } else if (conjuntos.get(2).equals(c1)) {
                        conjunto1 = Occasional;
                    } else System.out.println("xd?");
                    conjuntos.remove(c1);

                    // Seleccionar segundo conjunto
                    String c2 = (String) InputSelect("Seleccione el segundo conjunto: ", "Conjunto 2", conjuntos.toArray(new String[conjuntos.size()]));
                    ArrayList<Docente> conjunto2 = null;
                    if (c2.contains("completo")) {
                        conjunto2 = fullTime;
                    } else if (c2.contains("catedra")) {
                        conjunto2 = Adjunct;
                    } else if (c2.contains("ocacionales")) {
                        conjunto2 = Occasional;
                    } else System.out.println("Tipo de docente no permitido");


                    ArrayList<String> atrs = new ArrayList<>(Arrays.asList("Titulo", "Facultad", "Sexo", "Horas que dicta", "Asignaturas que dicta", "Año de nacimiento"));
                    String atr = (String) InputSelect("Selecione atributo por el cual se va a filtrar", "Atributo", atrs.toArray(new String[atrs.size()]));

                    Predicate<Docente> condicion_final = docente -> {return true; };
                    boolean numeric = false;

                    if (atrs.get(0).equals(atr)) { // Titulo
                        String titulo = RecordDegree();
                        condicion_final = docente -> docente.getDegree().equals(titulo);

                    } else if (atrs.get(1).equals(atr)) { // Facultad
                        String facultad = RecordDepartament();
                        condicion_final = docente -> docente.getDegree().equals(facultad);

                    } else if (atrs.get(2).equals(atr)) { // Sexo
                        String[] sexos = {"M", "F"};
                        Character sexo = (Character) InputSelect("Selecione el sexo (Masculino, Femenino)", "Sexo", sexos);
                        condicion_final = docente -> docente.getGender() == sexo;
                    }

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
                            num = Input("Escriba el año [yyyy]: ");
                        } while (!m.matcher(num).matches());
                        numero = Integer.parseInt(num);
                        atributo = "getAnoNacimiento";
                        numeric = true;

                    } else {
                        numero = 0;
                        atributo = "";
                    }

                    if (numeric) {
                        String comparacion = ChangeCondition();
                        condicion_final = docente -> {
                            try {
                                Method getter = Docente.class.getMethod(atributo);
                                int valorAtributo = (int) getter.invoke(docente);
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
                    } else {
                        ArrayList<String> oprs = new ArrayList<>(Arrays.asList("Igual", "Diferente"));
                        String opr = (String) InputSelect("Bajo que condicion quiere operar?", "Condicion", oprs.toArray(new String[oprs.size()]));
                        if (oprs.get(1).equals(opr)) condicion_final = condicion_final.negate();
                    }

                    msgScroll(Show(funcionAdaptativa(conjunto1, conjunto2, condicion_final, Set_SELECIONADA)));
                    break;
                case "Ingresar docente":
                    String cedula = ValidateID();
                    String Nombre = ValidateRegex("[a-zA-Z\\s]+", "Ingrese el nombre completo");
                    char sexo = ValidateGen();
                    String[] opts2 = { "Artes","Humanidades","Ciencias","Ingenieria","Derecho","Medicina","Negocios","Agricultura","Arquitectura","Administracion","Deportes","Idiomas","Comunicacion"};
                    String Facultad = (String) InputSelect("Seleccione el genero", "genero", opts2);
                    String[] opts3 = { "Pregrado", "Especializacion", "Maestria", "Doctorado" };
                    String titulo = (String) InputSelect("Seleccione el genero", "Genero", opts3);
                    int CantAsiganturas = Integer
                            .parseInt(ValidateRegex("[1-9]|10", "Ingrese la cantidad de asignaturas que dicta"));
                    int CantHoras = Integer
                            .parseInt(ValidateRegex("[1-9]|1[0-9]|20",
                                    "Ingrese la cantidad de horas de clase dictadas por semana"));
                    Date FechaNacimineto = InputDate();
                    Docente docenteNuevo = new Docente(cedula, Nombre, Facultad, titulo, sexo, CantAsiganturas,
                            CantHoras, FechaNacimineto);
                    TypeTeacher(docenteNuevo, "Tiempo Completo");
                    TypeTeacher(docenteNuevo, "Catedra");
                    TypeTeacher(docenteNuevo, "Ocasional");
                    break;

                case "Salir":
                    return;

                default:
                    Msg("Opcion invalida.");
                    break;
            }
        }
    }

    public ArrayList<Docente> funcionAdaptativa(ArrayList<Docente> c1, ArrayList<Docente> c2,
                                                Predicate<Docente> condicion, Set<Docente> set) {

        System.out.println("[ FUNCION ADAPTABLE ]");
        java.util.Set<Docente> filtro1 = new HashSet<>();
        for (Docente p : c1) {
            if (condicion.test(p)) {
                filtro1.add(p);
                System.out.println("[!] Filtro 2, coincidencia encontrada" + p.toString());
            } else System.out.println("[!] Invalido: " + p.getId());

        }

        java.util.Set<Docente> filtro2 = new HashSet<>();
        for (Docente p : c2) {
            if (condicion.test(p)) {
                filtro2.add(p);
                System.out.println("[!] Filtro 2, coincidencia encontrada" + p.toString());
            } else System.out.println("[!] Invalido: " + p.getId());

        }

        return new ArrayList<>(set.Do(filtro1, filtro2));
    }

    // OPERACIONES
    public static ArrayList<Docente> union(ArrayList<Docente> lists1, ArrayList<Docente> lista2) {
        java.util.Set<Docente> set = new HashSet<>(lists1);
        set.addAll(lista2);
        return new ArrayList<>(set);
    }

    public static ArrayList<Docente> intersecion(ArrayList<Docente> lists1, ArrayList<Docente> lista2) {
        java.util.Set<Docente> set1 = new HashSet<>(lists1);
        java.util.Set<Docente> set2 = new HashSet<>(lista2);
        set1.retainAll(set2);
        return new ArrayList<>(set1);
    }

    public static ArrayList<Docente> diferencia(ArrayList<Docente> lists1, ArrayList<Docente> lista2) {
        java.util.Set<Docente> set1 = new HashSet<>(lists1);
        java.util.Set<Docente> set2 = new HashSet<>(lista2);
        set1.removeAll(set2);
        return new ArrayList<>(set1);
    }

    public static boolean subconjuntoDe(ArrayList<Docente> lists1, ArrayList<Docente> lista2) {
        java.util.Set<Docente> set1 = new HashSet<>(lists1);
        java.util.Set<Docente> set2 = new HashSet<>(lista2);
        return set1.containsAll(set2);
    }


    private String RecordDepartament() {
        String[] facultades = {"Artes","Humanidades","Ciencias","Ingenieria","Derecho","Medicina","Negocios","Agricultura","Arquitectura","Administracion","Deportes","Idiomas","Comunicacion"};
        return (String) InputSelect("Seleccione la Facultad.", "Facultad", facultades);
    }

    private String RecordDegree() {
        String[] titulos = {"Pregrado", "Especializacion", "Maestria", "Doctorado"};
        return (String) InputSelect("Seleccione el Titulo", "Titulo", titulos);
    }

    private int RecordDictationCourses() {
        Pattern m = Pattern.compile("0*10|0*\\d");
        String num;
        do {
            num = Input("Escriba el numero de asignaturas que dicta [1-10]: ");
        } while (!m.matcher(num).matches());
        return Integer.parseInt(num);
    }

    private int RecordDictationHours() {
        Pattern m = Pattern.compile("0*20|0*1\\d|0*\\d");
        String num;
        do {
            num = Input("Escriba las horas que ha dictadas [1-20]: ");
        } while (!m.matcher(num).matches());
        return Integer.parseInt(num);
    }

    // funcion a
    private String ChangeCondition() {
        ArrayList<String> options = new ArrayList<>(Arrays.asList("Igual", "Diferente", "Mayor", "Menor"));
        String opr = (String) InputSelect("Bajo que condicion quiere operar?", "Condicion", options.toArray(new String[options.size()]));
        if (options.get(0).equals(opr)) { // igual
            return "==";
        } else if (options.get(1).equals(opr)) { // Diferente
            return "!=";
        } else if (options.get(2).equals(opr)) { // Mayor
            return ">";
        } else if (options.get(3).equals(opr)) { // Menor
            return "<";
        }
        return "==";
    }

    public String Show(ArrayList<Docente> d) {
        Iterator<Docente> i = d.iterator();
        StringBuilder s = new StringBuilder();
        if (!d.isEmpty()) {
            s.append("Mostrando " + d.size() + " Entradas.\n");
            while (i.hasNext()) {
                Docente p = i.next();
                s.append("CC: " + p.getId() + "\n");
                s.append("Nombre Completo: " + p.getName() + "\n");
                s.append("Facultad: " + p.getFaculty() + "\n");
                s.append("Titulo: " + p.getDegree() + "\n");
                s.append("Sexo: " + p.getGender() + "\n");
                s.append("Asignaturas Dictadas: " + p.getCoursesTaught() + "\n");
                s.append("Horas Dictadas: " + p.getHoursTaught() + "\n");
                s.append("Fecha Nacimiento: " + (p.getDateBirth().toLocaleString().split(",")[0]) + "\n\n");
            }
        }

        return s.toString();
    }


    public void chargeData() {
        try {
            String root = System.getProperty("user.dir");
            FileManager f = new FileManager(root + "/src/Data/Docentes.txt");
            System.out.println(Clr.B + "[!] Leyendo Base de Datos " + Clr.RT);

            ArrayList<String> lineas = f.readFileArrayList();
            Iterator<String> i = lineas.iterator();

            while (i.hasNext()) {
                String[] p = i.next().split(";");
                Docente docente = new Docente(
                        p[1], // CC
                        p[2], // Nombre Completo
                        p[4], // Facultad
                        p[5], // Titulo
                        p[3].charAt(0), // Sexo
                        Integer.parseInt(p[6]), // Asignaturas Dictadas
                        Integer.parseInt(p[7]), // Horas Dictadas
                        new SimpleDateFormat("yyyy-MM-dd").parse(p[8]) // Fecha de Nacimiento
                );

                if (p[0].contains("T")) {
                    fullTime.add(docente);
                }
                if (p[0].contains("C")) {
                    Adjunct.add(docente);
                }
                if (p[0].contains("O")) {
                    Occasional.add(docente);
                }
                System.out.println(Clr.G  + "[+] Añadiendo Docente: " + Clr.RT +(Arrays.stream(p).toList()));
            }
        } catch (IOException | ParseException e) {
            System.out.println(Clr.R  + "[-] Añadiendo Docente: " + e.getMessage()+ Clr.RT );
        }
    }

    @Override
    protected String ValidateRegex(String patron, String msginput) {
        Pattern Patron = Pattern.compile(patron);
        String input;
        while (true) {
            input = Input(msginput).trim();
            if (!Patron.matcher(input).matches()) {
                Msg("Formato invalido");
            } else
                return input;
        }
    }

    @Override
    protected String ValidateID() {
        String input;
        while (true) {
            input = ValidateRegex("\\d+", "Ingrese el ID");
            boolean flag = true;
            for (Docente docente : union(union(fullTime, Occasional), Adjunct)) {
                if (docente.getId().equals(input)) {
                    flag = false;
                    Msg("El ID ya esta en uso");
                    break;
                }
            }
            if (flag)
                return input;
        }
    }

    private char ValidateGen() {
        String[] options = { "Masculino", "Femenino" };
        String input = (String) InputSelect("Seleccione el genero", "genero", options);
        Pattern PatronGen = Pattern.compile("[M|F]");
        Pattern PatronM = Pattern.compile("M");
        if (PatronGen.matcher(input).find()) {
            if(PatronM.matcher(input).find()){
                return 'M';
            }
            return 'F';
        }
        return 'O';
    }

    private void TypeTeacher(Docente newTeacher, String Type) {
        String[] options = {"Si","No"};
        String SelectTipo = (String) InputSelect("El docente es de "+Type+"?", "Tipo de docente", options);
        if(SelectTipo.equals(options[0])){
            if(Type.equals("Tiempo Completo")) fullTime.add(newTeacher);
            else if(Type.equals("Catedra")) Adjunct.add(newTeacher);
            else if (Type.equals("Ocasional")) Occasional.add(newTeacher);
            else Msg("Ha seleccionado un tipo de docente incorrecto");
        }
    }

}