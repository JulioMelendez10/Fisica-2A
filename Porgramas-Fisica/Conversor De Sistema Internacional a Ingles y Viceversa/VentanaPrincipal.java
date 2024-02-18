import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        super("Conversor de medidas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnVentana1 = new JButton("Sistema Ingles");
        btnVentana1.setBounds(50, 30, 200, 30);
        panel.add(btnVentana1);

        JButton btnVentana2 = new JButton("Sistema Internacional");
        btnVentana2.setBounds(50, 70, 200, 30);
        panel.add(btnVentana2);

        btnVentana1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new ConversorInglesInternacional());
            }
        });

        btnVentana2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new ConversorInternacionalIngles());
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
