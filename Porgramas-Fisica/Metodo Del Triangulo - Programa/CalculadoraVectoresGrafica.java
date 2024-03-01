import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraVectoresGrafica extends JFrame implements ActionListener {
    private JLabel lblCantidad1, lblCantidad2, lblResultado;
    private JTextField txtCantidad1, txtCantidad2;
    private JButton btnCalcular;
    private JComboBox<String> cbUnidad;
    private JPanel panelGrafico;

    public CalculadoraVectoresGrafica() {
        setTitle("Calculadora de Vectores con Graficos");
        setSize(800, 600); // Tamaño más grande
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes de la interfaz
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        lblCantidad1 = new JLabel("VALOR DE X:");
        txtCantidad1 = new JTextField();
        panel.add(lblCantidad1);
        panel.add(txtCantidad1);

        lblCantidad2 = new JLabel("VALOR DE Y:");
        txtCantidad2 = new JTextField();
        panel.add(lblCantidad2);
        panel.add(txtCantidad2);

        String[] unidades = {"Milimetro", "Centimetro", "Metro", "Kilometro", "Milla", "Yarda"};
        cbUnidad = new JComboBox<>(unidades);
        panel.add(new JLabel("Unidad:"));
        panel.add(cbUnidad);

        btnCalcular = new JButton("CALCULAR");
        btnCalcular.addActionListener(this);
        panel.add(btnCalcular);

        lblResultado = new JLabel("RESULTADOS:");
        panel.add(lblResultado);

        // Panel para dibujar gráfico
        panelGrafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarPlanoCartesiano(g);
            }
        };
        panelGrafico.setBackground(Color.white);

        // Agregar el panel al centro del BorderLayout
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(panelGrafico, BorderLayout.CENTER); // Agregado al centro

        // Centrar la ventana
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) {
            // Obtener cantidades y unidad
            double cantidad1 = Double.parseDouble(txtCantidad1.getText());
            double cantidad2 = Double.parseDouble(txtCantidad2.getText());
            String unidad = (String) cbUnidad.getSelectedItem();

            // Convertir a metros
            double cantidad1Metros = convertirAMetros(cantidad1, unidad);
            double cantidad2Metros = convertirAMetros(cantidad2, unidad);

            // Calcular resultado
            double resultado = Math.sqrt(Math.pow(cantidad1Metros, 2) + Math.pow(cantidad2Metros, 2));

            // Mostrar resultado
            String resultadoMetros = String.format("%.2f", resultado) + " metros";
            String resultadoCentimetros = convertirDeMetros(resultado, "Centimetro");
            String resultadoMilimetros = convertirDeMetros(resultado, "Milimetro");
            String resultadoKilometros = convertirDeMetros(resultado, "Kilometro");
            String resultadoMillas = convertirDeMetros(resultado, "Milla");
            String resultadoYardas = convertirDeMetros(resultado, "Yarda");

            lblResultado.setText("<html>Resultado: " + resultadoMetros + "<br>" +
                    "Centimetros: " + resultadoCentimetros + "<br>" +
                    "Milimetros: " + resultadoMilimetros + "<br>" +
                    "Kilometros: " + resultadoKilometros + "<br>" +
                    "Millas: " + resultadoMillas + "<br>" +
                    "Yardas: " + resultadoYardas + "</html>");

            // Dibujar el triángulo en el plano cartesiano
            Graphics g = panelGrafico.getGraphics();
            dibujarTriangulo(g, cantidad1Metros, cantidad2Metros);
        }
    }

    private double convertirAMetros(double cantidad, String unidad) {
        switch (unidad) {
            case "Milimetro":
                return cantidad / 1000.0;
            case "Centimetro":
                return cantidad / 100.0;
            case "Metro":
                return cantidad;
            case "Kilometro":
                return cantidad * 1000.0;
            case "Milla":
                return cantidad * 1609.34;
            case "Yarda":
                return cantidad * 0.9144;
            default:
                return 0.0;
        }
    }

    private String convertirDeMetros(double cantidadMetros, String unidad) {
        switch (unidad) {
            case "Milimetro":
                return String.format("%.2f", cantidadMetros * 1000.0);
            case "Centimetro":
                return String.format("%.2f", cantidadMetros * 100.0);
            case "Metro":
                return String.format("%.2f", cantidadMetros);
            case "Kilometro":
                return String.format("%.2f", cantidadMetros / 1000.0);
            case "Milla":
                return String.format("%.2f", cantidadMetros / 1609.34);
            case "Yarda":
                return String.format("%.2f", cantidadMetros / 0.9144);
            default:
                return "Unidad no válida";
        }
    }

    private void dibujarPlanoCartesiano(Graphics g) {
        int width = panelGrafico.getWidth();
        int height = panelGrafico.getHeight();

        // Dibujar ejes X e Y
        g.setColor(Color.black);
        g.drawLine(0, height / 2, width, height / 2); // Eje X
        g.drawLine(width / 2, 0, width / 2, height); // Eje Y
    }

    private void dibujarTriangulo(Graphics g, double x, double y) {
        int width = panelGrafico.getWidth();
        int height = panelGrafico.getHeight();

        // Escalar para que quepa en el plano
        int escala = 10;

        // Dibujar el vector
        g.setColor(Color.blue);
        g.drawLine(width / 2, height / 2, width / 2 + (int)(x * escala), height / 2 - (int)(y * escala)); // Dibujar vector

        // Dibujar los vértices
        g.setColor(Color.red);
        g.fillOval(width / 2 - 3, height / 2 - 3, 6, 6); // Origen
        g.fillOval(width / 2 + (int)(x * escala) - 3, height / 2 - (int)(y * escala) - 3, 6, 6); // Extremo
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraVectoresGrafica calc = new CalculadoraVectoresGrafica();
            calc.setVisible(true);
        });
    }
}
