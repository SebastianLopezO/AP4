package bean;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

public abstract class Menu  {

    private String title;

    public Menu(String title) {
        this.title = title;
    }

    abstract public void menu();

    public void Msg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "title", JOptionPane.INFORMATION_MESSAGE);
    }

    public void msgScroll(String msg) {
        JTextArea textArea = new JTextArea(msg);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public String Input(String msg) {
        return JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE) ;
    }

    public Object InputSelect(String msg, String titleSelect, String[] options) {
        return JOptionPane.showInputDialog(null,
                msg,
                titleSelect,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    public Date InputDate() {

        JFrame frame = new JFrame("Seleccionar fecha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configurar el cierre del programa cuando se cierra la ventana
        frame.setSize(300, 200); // Establecer el tamaño del marco

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        frame.add(mainPanel);

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel label = new JLabel("Seleccione una fecha:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(label, constraints);

        // Crear y agregar un JDateChooser (selector de fecha) al panel
        JDateChooser fecha = new JDateChooser();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(fecha, constraints);

        // Crear y agregar un botón al panel
        JButton button = new JButton("Guardar fecha");
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(button, constraints);

        // Agregar un listener al botón para manejar eventos de clic
        AtomicReference<Date> FinalDate = new AtomicReference<>();
        button.addActionListener(e -> {
            Date fechaselect = fecha.getDate(); // Obtener la fecha seleccionada del JDateChooser
            if (fechaselect != null) {
                System.out.println("Fecha seleccionada!");
                FinalDate.set(fechaselect);
                frame.dispose();
            } else {
                // Imprimir si no se seleccionó ninguna fecha
                System.out.println("No se seleccionó ninguna fecha");
            }
        });

        frame.setVisible(true);

        // Esperar que se capture la fecha para return
        while (FinalDate.get() == null) {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return FinalDate.get();
    }
    private String ValidateRegex;
    private String ValidateID;

    protected abstract String ValidateRegex(String patron, String msginput);

    protected abstract String ValidateID();
}
