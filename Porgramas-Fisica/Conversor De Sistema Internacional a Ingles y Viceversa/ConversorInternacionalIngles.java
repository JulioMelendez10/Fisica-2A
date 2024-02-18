import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversorInternacionalIngles extends JFrame {

    private JComboBox<String> medidaComboBox;
    private JTextField cantidadTextField;
    private JTextArea resultadoTextArea;

    public ConversorInternacionalIngles() {
        super("Conversor Internacional a Inglés");
        
        setSize(400, 250);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblMedida = new JLabel("Seleccione la medida:");
        lblMedida.setBounds(20, 20, 150, 30);
        panel.add(lblMedida);

        medidaComboBox = new JComboBox<>(getMedidasInternacionales());
        medidaComboBox.setBounds(180, 20, 150, 30);
        panel.add(medidaComboBox);

        JLabel lblCantidad = new JLabel("Ingrese la cantidad:");
        lblCantidad.setBounds(20, 60, 150, 30);
        panel.add(lblCantidad);

        cantidadTextField = new JTextField();
        cantidadTextField.setBounds(180, 60, 150, 30);
        panel.add(cantidadTextField);

        JButton btnConvertir = new JButton("Convertir");
        btnConvertir.setBounds(150, 100, 100, 30);
        panel.add(btnConvertir);

        resultadoTextArea = new JTextArea();
        resultadoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoTextArea);
        scrollPane.setBounds(20, 140, 350, 60);
        panel.add(scrollPane);

        btnConvertir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                convertir();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void convertir() {
        try {
            String medida = (String) medidaComboBox.getSelectedItem();
            double cantidad = Double.parseDouble(cantidadTextField.getText());

            resultadoTextArea.setText("Resultado en Sistema Inglés:\n");

            switch (medida) {
                case "Centímetros":
                    appendResultado("Pulgadas", cantidad / 2.54);
                    appendResultado("Pies", cantidad / 30.48);
                    break;
                case "Metros":
                    appendResultado("Pies", cantidad * 3.28084);
                    appendResultado("Pulgadas", cantidad * 39.3701);
                    break;
                case "Kilómetros":
                    appendResultado("Millas", cantidad / 1.60934);
                    break;
                case "Mililitros":
                    appendResultado("Onzas líquidas", cantidad / 29.5735);
                    break;
                case "Gramos":
                    appendResultado("Onzas", cantidad / 28.3495);
                    break;
                case "Litros":
                    appendResultado("Galones", cantidad / 3.78541);
                    break;
                default:
                    resultadoTextArea.setText("Conversión no compatible.");
            }
        } catch (NumberFormatException ex) {
            resultadoTextArea.setText("Entrada no válida. Intente nuevamente.");
        }
    }

    private void appendResultado(String medidaDestino, double cantidadConvertida) {
        resultadoTextArea.append(String.format("%s: %.2f\n", medidaDestino, cantidadConvertida));
    }

    private String[] getMedidasInternacionales() {
        return new String[]{"Centímetros", "Metros", "Kilómetros", "Mililitros", "Gramos", "Litros"};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConversorInternacionalIngles());
    }
}
