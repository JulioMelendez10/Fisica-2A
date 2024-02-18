import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversorInglesInternacional extends JFrame {

    private JComboBox<String> medidaOrigenComboBox;
    private JTextField cantidadTextField;
    private JTextArea resultadoTextArea;

    public ConversorInglesInternacional() {
        super("Conversor Inglés a Internacional");
        
        setSize(400, 250);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblMedidaOrigen = new JLabel("Seleccione la medida de origen:");
        lblMedidaOrigen.setBounds(20, 20, 200, 30);
        panel.add(lblMedidaOrigen);

        medidaOrigenComboBox = new JComboBox<>(getMedidasIngles());
        medidaOrigenComboBox.setBounds(220, 20, 150, 30);
        panel.add(medidaOrigenComboBox);

        JLabel lblCantidad = new JLabel("Ingrese la cantidad:");
        lblCantidad.setBounds(20, 60, 150, 30);
        panel.add(lblCantidad);

        cantidadTextField = new JTextField();
        cantidadTextField.setBounds(220, 60, 150, 30);
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
            String medidaOrigen = (String) medidaOrigenComboBox.getSelectedItem();
            double cantidad = Double.parseDouble(cantidadTextField.getText());

            resultadoTextArea.setText("Resultado en Sistema Internacional:\n");

            switch (medidaOrigen) {
                case "Pulgadas":
                    appendResultado("Centímetros", cantidad * 2.54);
                    appendResultado("Metros", cantidad * 0.0254);
                    break;
                case "Pies":
                    appendResultado("Metros", cantidad * 0.3048);
                    break;
                case "Millas":
                    appendResultado("Kilómetros", cantidad * 1.60934);
                    break;
                case "Onzas líquidas":
                    appendResultado("Mililitros", cantidad * 29.5735);
                    break;
                case "Onzas":
                    appendResultado("Gramos", cantidad * 28.3495);
                    break;
                case "Libras":
                    appendResultado("Gramos", cantidad * 453.592);
                    break;
                case "Galones":
                    appendResultado("Litros", cantidad * 3.78541);
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

    private String[] getMedidasIngles() {
        return new String[]{"Pulgadas", "Pies", "Millas", "Onzas líquidas", "Onzas", "Libras", "Galones"};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConversorInglesInternacional());
    }
}
