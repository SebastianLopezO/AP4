package bean;

public class Docente {
    private String Id;
    private String Name;
    private String Type;
    private String Date;
    private int Attempts;

    public Docente(String Id, String Name, String Type, String Date, int Attempts) {
        this.Id = Id;
        this.Name = Name;
        this.Type = Type;
        this.Date = Date;
        this.Attempts = Attempts;
    }

    public Docente(Docente d) {
        this.Id = d.getId();
        this.Type = d.getType();
        this.Date = d.getDate();
        this.Attempts = d.getAttempts();
    }

    // Getters and Setters


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public int getAttempts() {
        return Attempts;
    }

    public void setAttempts(int attempts) {
        this.Attempts = attempts;
    }
}
