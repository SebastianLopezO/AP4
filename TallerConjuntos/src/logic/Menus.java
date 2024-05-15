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
                    "Docentes por Genero y Facultad",
                    "Docentes por Facultad y Tipo de Contrato",
                    "Ingresar docente",
                    "Salir"
            };
            option = (String) InputSelect("Selecciona una opción","Sistemad de Docentes", Options);
            ArrayList<Docente> OnlyTeachers = null;
            switch (option) {
                case "Docentes solo de tiempo completo" -> {
                    OnlyTeachers = diferencia(diferencia(fullTime, Adjunct), Occasional);
                    msgHtml(Show(OnlyTeachers, "Tiempo Completo"), 500, 500);
                }
                case "Docentes solo de catedra" -> {
                    OnlyTeachers = diferencia(diferencia(Adjunct, fullTime), Occasional);
                    msgHtml(Show(OnlyTeachers, "Catedra"), 500, 500);
                }
                case "Docentes solo ocasionales" -> {
                    OnlyTeachers = diferencia(diferencia(Occasional, fullTime), Adjunct);
                    msgHtml(Show(OnlyTeachers, "Ocasionales"), 500, 500);
                }
                case "Total de Docentes" -> {
                    OnlyTeachers = union(union(fullTime, Occasional), Adjunct);
                    msgHtml(Show(OnlyTeachers, "Total"), 500, 500);
                }
                case "Docentes de tiempo completo y de catedra a la vez" -> {
                    OnlyTeachers = intersecion(fullTime, Adjunct);
                    msgHtml(Show(OnlyTeachers, "Tiempo completo y Catedra"), 500, 500);
                }
                case "Docentes ocasionales y de catedra a la vez" -> {
                    OnlyTeachers = intersecion(Occasional, Adjunct);
                    msgHtml(Show(OnlyTeachers, "Ocasionales y Catedra"), 500, 500);
                }
                case "Docentes de Catedra, tiempo completo y ocasional a la vez" -> {
                    OnlyTeachers = intersecion(intersecion(fullTime, Occasional), Adjunct);
                    msgHtml(Show(OnlyTeachers, "Catedra, Tiempo completo y Ocasional"), 500, 500);
                }
                case "Docentes por Genero y tipo de Contrato" -> {
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

                    String htmlString = "<html><body>" +
                            "<h1>Docentes por Genero y Tipo de contrato</h1>" +
                            "<table border='1' style='width:50%; border-collapse: collapse;'>" +
                            "<tr style='background-color: #000000; color: #ffffff; font-weight: bold;'><th>Tipo de Contrato</th><th>Hombres</th><th>Mujeres</th></tr>" +
                            String.format("<tr><td>Tiempo Completo</td><td>%d</td><td>%d</td></tr>", M[0], F[0]) +
                            String.format("<tr><td>Ocasional</td><td>%d</td><td>%d</td></tr>", M[1], F[1]) +
                            String.format("<tr><td>Cátedra</td><td>%d</td><td>%d</td></tr>", M[2], F[2]) +
                            "</table>" +
                            "</body></html>";

                    msgHtml(htmlString,300,300);
                }
                case "Docentes por Facultad y Tipo de Contrato" -> {
                    OnlyTeachers = union(union(fullTime, Occasional), Adjunct);
                    String[] ListFaculty = ListFaculty(OnlyTeachers);
                    String StringListFaculty = "";
                    for(String faculty: ListFaculty){
                        StringListFaculty += "<th>"+faculty+"</th>";
                    }
                    Map<String, Integer> FacultyFullTime = new HashMap<>();
                    for (Docente docente : fullTime) {
                        String faculty = docente.getFaculty();
                        FacultyFullTime.put(faculty, FacultyFullTime.getOrDefault(faculty, 0) + 1);
                    }

                    Map<String, Integer> FacultyOccasional = new HashMap<>();
                    for (Docente docente : Occasional) {
                        String faculty = docente.getFaculty();
                        FacultyOccasional.put(faculty, FacultyOccasional.getOrDefault(faculty, 0) + 1);
                    }

                    Map<String, Integer> FacultyAdjunct = new HashMap<>();
                    for (Docente docente : Adjunct) {
                        String faculty = docente.getFaculty();
                        FacultyAdjunct.put(faculty, FacultyAdjunct.getOrDefault(faculty, 0) + 1);
                    }


                    StringBuilder htmlString = new StringBuilder();
                    htmlString.append("<html><body>");
                    htmlString.append("<h1>Docentes por Facultad y Tipo de contrato</h1>");
                    htmlString.append("<table border='1' style='width:100%; border-collapse: collapse;'>");
                    htmlString.append("<tr style='background-color: #000000; color: #ffffff; font-weight: bold;'>");
                    htmlString.append("<th>Tipo de Contrato</th>").append(StringListFaculty).append("</tr>");

                    htmlString.append("<tr><td>Tiempo Completo</td>");
                    for (Map.Entry<String, Integer> entry : FacultyFullTime.entrySet()) {
                        htmlString.append("<td>").append(entry.getValue()).append("</td>");
                    }
                    htmlString.append("<tr><td>Ocasional</td>");
                    for (Map.Entry<String, Integer> entry : FacultyOccasional.entrySet()) {
                        htmlString.append("<td>").append(entry.getValue()).append("</td>");
                    }
                    htmlString.append("<tr><td>Cátedra</td>");
                    for (Map.Entry<String, Integer> entry : FacultyAdjunct.entrySet()) {
                        htmlString.append("<td>").append(entry.getValue()).append("</td>");
                    }
                    htmlString.append("</table>");
                    htmlString.append("</body></html>");
                    msgHtml(htmlString.toString(),700,300);
                }
                case "Docentes por Facultad" -> {
                    Map<String, Integer> facultades = new HashMap<>();
                    OnlyTeachers = union(union(fullTime, Occasional), Adjunct);
                    for (Docente docente : OnlyTeachers) {
                        String faculty = docente.getFaculty();
                        facultades.put(faculty, facultades.getOrDefault(faculty, 0) + 1);
                    }
                    StringBuilder htmlString = new StringBuilder();
                    htmlString.append("<html><body>");
                    htmlString.append("<table border='1' style='width:100%; border-collapse: collapse;'>");
                    htmlString.append("<tr style='background-color: #000000; color: #ffffff; font-weight: bold;'>" +
                            "<th>Facultad</th><th>Cantidad</th>" +
                            "</tr>");
                    boolean isGrayDark = true;
                    String bgColor;
                    for (Map.Entry<String, Integer> entry : facultades.entrySet()) {
                        bgColor = isGrayDark ? "#404040" : "#737373";
                        htmlString.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>");
                        htmlString.append("<td>").append(entry.getKey()).append("</td>");
                        htmlString.append("<td>").append(entry.getValue()).append("</td>");
                        htmlString.append("</tr>");
                        isGrayDark = !isGrayDark;
                    }
                    htmlString.append("</table>");
                    htmlString.append("</body></html>");
                    msgHtml(htmlString.toString(), 500, 300);
                }
                case "Docentes por Genero y Facultad" ->{
                    Map<String, int[]> facultades = new HashMap<>();
                    OnlyTeachers = union(union(fullTime, Occasional), Adjunct);
                    for (Docente docente : OnlyTeachers) {
                        String faculty = docente.getFaculty();
                        char gender = docente.getGender();
                        int genderIndex = (gender == 'M' || gender == 'm') ? 0 : 1; // 0 para hombres, 1 para mujeres

                        facultades.putIfAbsent(faculty, new int[2]);
                        facultades.get(faculty)[genderIndex]++;
                    }

                    StringBuilder htmlString = new StringBuilder();
                    htmlString.append("<html><body>");
                    htmlString.append("<table border='1' style='width:100%; border-collapse: collapse;'>");
                    htmlString.append("<tr style='background-color: #000000; color: #ffffff; font-weight: bold;'>" +
                            "<th>Facultad</th><th>Hombres</th><th>Mujeres</th></tr>");

                    boolean isGrayDark = true;
                    String bgColor;
                    for (Map.Entry<String, int[]> entry : facultades.entrySet()) {
                        bgColor = isGrayDark ? "#404040" : "#737373";
                        htmlString.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>");
                        htmlString.append("<td>").append(entry.getKey()).append("</td>");
                        htmlString.append("<td>").append(entry.getValue()[0]).append("</td>"); // Hombres
                        htmlString.append("<td>").append(entry.getValue()[1]).append("</td>"); // Mujeres
                        htmlString.append("</tr>");
                        isGrayDark = !isGrayDark;
                    }

                    htmlString.append("</table>");
                    htmlString.append("</body></html>");
                    msgHtml(htmlString.toString(), 500, 300);
                }
                case "Ingresar docente" -> {
                    String id = ValidateID();
                    String name = ValidateRegex("[a-zA-Z\\s]+", "Ingrese el nombre completo");
                    char gender = ValidateGen();
                    OnlyTeachers = union(union(fullTime, Occasional), Adjunct);
                    String[] optFaculty = ListFaculty(OnlyTeachers);
                    String faculty = (String) InputSelect("Seleccione el genero", "genero", optFaculty);
                    String[] optDegree = {"Pregrado", "Especializacion", "Maestria", "Doctorado"};
                    String degree = (String) InputSelect("Seleccione el genero", "Genero", optDegree);
                    int CantCourses = Integer
                            .parseInt(ValidateRegex("[1-9]|10", "Ingrese la cantidad de asignaturas que dicta"));
                    int CantHours = Integer
                            .parseInt(ValidateRegex("[1-9]|1[0-9]|20",
                                    "Ingrese la cantidad de horas de clase dictadas por semana"));
                    Date FechaNacimineto = InputDate();
                    Docente docenteNuevo = new Docente(id, name, faculty, degree, gender, CantCourses,
                            CantHours, FechaNacimineto);
                    TypeTeacher(docenteNuevo, "Tiempo Completo");
                    TypeTeacher(docenteNuevo, "Catedra");
                    TypeTeacher(docenteNuevo, "Ocasional");
                }
                case "Salir" -> {
                    return;
                }
                default -> Msg("Opcion invalida.");
            }
        }
    }

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

    public static  String[] ListFaculty(ArrayList<Docente> OnlyTeachers){
        ArrayList<String> listFaculty = new ArrayList<String>();
        for (Docente docente : OnlyTeachers) {
            String faculty = docente.getFaculty();
            if(!listFaculty.contains(faculty)){
                listFaculty.add(faculty);
            }
        }

        // Convertir Set a String[]
        return listFaculty.toArray(new String[0]);
    }

    public String Show(ArrayList<Docente> d, String Type) {
        Iterator<Docente> i = d.iterator();
        StringBuilder s = new StringBuilder();
        boolean isGrayDark = true; // Alternar colores de las filas

        if (!d.isEmpty()) {
            s.append("<html><body>");
            s.append("<h3>" + d.size() + " Docentes de "+Type+" </h3>");
            s.append("<table border='1' style='width:100%; border-collapse: collapse;'>");
            s.append("<tr style='background-color: #000000; color: #ffffff; font-weight: bold;'>");
            s.append("<th>Id</th><th>Nombre</th><th>Facultad</th><th>Título</th><th>Género</th><th>Asignaturas Dictadas</th><th>Horas Dictadas</th><th>Fecha Nacimiento</th>");
            s.append("</tr>");

            while (i.hasNext()) {
                Docente p = i.next();
                String bgColor = isGrayDark ? "#404040" : "#737373"; // Colores alternos para las filas
                s.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>");
                s.append("<td>").append(p.getId()).append("</td>");
                s.append("<td>").append(p.getName()).append("</td>");
                s.append("<td>").append(p.getFaculty()).append("</td>");
                s.append("<td>").append(p.getDegree()).append("</td>");
                s.append("<td>").append(p.getGender()).append("</td>");
                s.append("<td>").append(p.getCoursesTaught()).append("</td>");
                s.append("<td>").append(p.getHoursTaught()).append("</td>");
                s.append("<td>").append(p.getDateBirth().toString().split(",")[0]).append("</td>");
                s.append("</tr>");
                isGrayDark = !isGrayDark; // Cambia el color para la siguiente fila
            }
            s.append("</table>");
            s.append("</body></html>");
        }

        return s.toString();
    }



    public void chargeData() {
        try {
            String root = System.getProperty("user.dir");
            FileManager f = new FileManager(root + "/src/Data/Docentes.txt");
            System.out.println(Clr.B + "[¡] Leyendo Base de Datos " + Clr.RT);

            ArrayList<String> lineas = f.readFileArrayList();
            Iterator<String> i = lineas.iterator();

            while (i.hasNext()) {
                String[] p = i.next().split(";");
                Docente docente = new Docente(
                        p[1], // Id
                        p[2], // Nombre
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