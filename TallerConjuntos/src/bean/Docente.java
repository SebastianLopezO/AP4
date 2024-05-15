package bean;

import java.util.Date;

public class Docente {

    private String Id, Name, Faculty, Degree;
    private char Gender;
    private int CoursesTaught, HoursTaught;
    private Date DateBirth;

    public Docente(String Id, String Name, String Faculty, String Degree, char Gender, int CoursesTaught, int HoursTaught, Date DateBirth) {
        this.Id = Id;
        this.Name = Name;
        this.Faculty = Faculty;
        this.Degree = Degree;
        this.Gender = Gender;
        this.CoursesTaught = CoursesTaught;
        this.HoursTaught = HoursTaught;
        this.DateBirth = DateBirth;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        this.Faculty = faculty;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        this.Degree = degree;
    }

    public char getGender() {
        return Gender;
    }

    public void setGender(char gender) {
        this.Gender = gender;
    }

    public int getCoursesTaught() {
        return CoursesTaught;
    }

    public void setCoursesTaught(int coursesTaught) {
        this.CoursesTaught = coursesTaught;
    }

    public int getHoursTaught() {
        return HoursTaught;
    }

    public void setHoursTaught(int hoursTaught) {
        this.HoursTaught = hoursTaught;
    }

    public Date getDateBirth() {
        return DateBirth;
    }

    public int getAnoNacimiento() {
        return Integer.parseInt(DateBirth.toLocaleString().split(",")[0].split("/")[2]);
    }

    public void setDateBirth(Date dateBirth) {
        this.DateBirth = dateBirth;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + "\n" +
                "Nombre: " + getName() + "\n" +
                "Facultad: " + getFaculty() + "\n" +
                "Titulo: " + getDegree() + "\n" +
                "Genero: " + getGender() + "\n" +
                "Asignaturas Dictadas: " + getCoursesTaught() + "\n" +
                "Horas Dictadas: " + getHoursTaught() + "\n" +
                "Fecha Nacimiento: " + (getDateBirth().toString().split(",")[0]) + "\n\n";
    }
}