package ventanas;

import report.Reporte;
import clases.*;
import conexion.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import report.GenerarReporteExcel;

public class PlataformaUser extends javax.swing.JFrame {

    ProductoJDBC productoJDBC = new ProductoJDBC();
    EmpleadoJDBC empleadoJDBC = new EmpleadoJDBC();
    ClienteJDBC clienteJDBC = new ClienteJDBC();
    VentaJDBC ventaJDBC = new VentaJDBC();
    UsuarioJDBC usuarioJDBC = new UsuarioJDBC();

    DefaultTableModel modeloProdcutos = new DefaultTableModel();
    DefaultTableModel modeloClientes = new DefaultTableModel();
    DefaultTableModel modeloEmpleados = new DefaultTableModel();
    DefaultTableModel modeloVentas = new DefaultTableModel();
    DefaultTableModel modeloDetalles = new DefaultTableModel();
    DefaultTableModel modeloUsuarios = new DefaultTableModel();

    ArrayList<Producto> productos = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Empleado> empleados = new ArrayList<>();
    ArrayList<Venta> ventas = new ArrayList<>();
    ArrayList<DetalleVenta> detalles = new ArrayList<>(); //detalles es el carrito
    ArrayList<Usuario> usuarios = new ArrayList<>();

    //variables auxiliares para actualizar registros
    int idProducto;
    int idEmpleado;
    int idCliente;
    int idDetalle;
    int idUsuario;
    int idVenta;

    //id usuario activo
    int idEmpleadoActual;

    Color colorEntrar = new Color(34, 117, 26);
    Color colorSalir = new Color(45, 156, 35);

    Pass password = new Pass();
    GenerarReporteExcel rpteExcel = new GenerarReporteExcel();

    public PlataformaUser(int id_empleado) {
        //Trea las tablas de la base de datos
        empleados = empleadoJDBC.select();
        clientes = clienteJDBC.select();
        productos = productoJDBC.select();

        initComponents();

        iniciarTablas();

        rbtCnatural.setSelected(true);

        actualizarEmpleadoActual(id_empleado);

        formatoCabeceraTabla(tblClientes);
        formatoCabeceraTabla(tblDetalles);
        formatoCabeceraTabla(tblVentas);
        formatoCabeceraTabla(tblClientesDialog);
        formatoCabeceraTabla(tblProductosDialog);
        formatoCabeceraTabla(tblDetallesDialog);
        formatoCabeceraTabla(tblVentasReporte);
        formatoCabeceraTabla(tblEmpleadoDialog);
    }

    //Carga los datos del usuario activo
    private void actualizarEmpleadoActual(int id_empleado) {
        idEmpleadoActual = id_empleado;

        int posEmpleado = 0;
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getidEmpleado() == id_empleado) {
                posEmpleado = i;
                break;
            }
        }

        txtUsuarioActivo.setText(empleados.get(posEmpleado).getNombre()
                + " " + empleados.get(posEmpleado).getApellido());
        txtRolActivo.setText(empleados.get(posEmpleado).getTipoEmpleado());
    }

    private void formatoCabeceraTabla(JTable tabla) {
        tabla.getTableHeader().setFont(new Font("Segue UI", Font.BOLD, 14));
        tabla.getTableHeader().setForeground(Color.BLACK);
        tabla.setRowHeight(25);
    }

    private void iniciarTablas() {
        String tituloP[] = {"Id", "Presentacion", "Nombre", "Concentracion", "Stock", "Precio", "Fecha Vencimiento"};
        modeloProdcutos.setColumnIdentifiers(tituloP);
        String tituloE[] = {"id Empleado", "Nombres", "Apellidos", "DNI", "Tipo empleado", "Sueldo", "Cargo"};
        modeloEmpleados.setColumnIdentifiers(tituloE);
        String tituloC[] = {"id Cliente", "Tipo", "Nombres", "Apellidos", "DNI", "RUC", "Razon Social"};
        modeloClientes.setColumnIdentifiers(tituloC);
        String tituloD[] = {"Nombre", "Precio", "Cantidad", "Importe"};
        modeloDetalles.setColumnIdentifiers(tituloD);
        String tituloV[] = {"id Venta", "Cliente", "Tipo de Pago", "Importe Total", "Fecha"};
        modeloVentas.setColumnIdentifiers(tituloV);
        String tituloU[] = {"id Usuario", "id Empleado", "Usuario", "Contraseña"};
        modeloUsuarios.setColumnIdentifiers(tituloU);
        tblClientes.setModel(modeloClientes);
        tblDetalles.setModel(modeloDetalles);
        tblVentas.setModel(modeloVentas);
        tblClientesDialog.setModel(modeloClientes);
        tblProductosDialog.setModel(modeloProdcutos);
        tblDetallesDialog.setModel(modeloDetalles);
        tblVentasReporte.setModel(modeloVentas);
        tblEmpleadoDialog.setModel(modeloEmpleados);
    }

    //Elimina todas la filas del DefaultTableModel enviada por parámetro
    private void eliminarTodasLasFilas(DefaultTableModel modelo) {
        int nRow = modelo.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    //Agrega fila por fila los datos del Arraylist productos
    private void actualizarTablaProducto() {
        eliminarTodasLasFilas(modeloProdcutos);

        productos.forEach(producto -> {
            modeloProdcutos.addRow(new Object[]{
                producto.getIdProducto(),
                producto.getPresentacion(),
                producto.getNombreProducto(),
                producto.getConcentracion(),
                producto.getStock(),
                producto.getPrecioVenta(),
                producto.getFechaVencimiento()
            });
        });
    }

    private void actualizarTablaEmpleado() {
        eliminarTodasLasFilas(modeloEmpleados);

        empleados.forEach(empleado -> {
            modeloEmpleados.addRow(new Object[]{
                empleado.getidEmpleado(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDni(),
                empleado.getTipoEmpleado(),
                empleado.getSueldo(),
                empleado.getCargo()
            });
        });
    }

    private void actualizarTablaCliente() {
        eliminarTodasLasFilas(modeloClientes);

        clientes.forEach(cliente -> {
            modeloClientes.addRow(new Object[]{
                cliente.getIdCliente(),
                cliente.getTipoPersona(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDni(),
                cliente.getRuc(),
                cliente.getRazonSocial()
            });
        });
    }

    private void actualizarTablaUsuario() {
        eliminarTodasLasFilas(modeloUsuarios);

        usuarios.forEach(usuario -> {
            modeloUsuarios.addRow(new Object[]{
                usuario.getIdUsuario(),
                usuario.getEmpleado().getidEmpleado(),
                usuario.getUser(),
                usuario.getPassword()
            });
        });
    }

    private void actualizarTablaVenta() {
        eliminarTodasLasFilas(modeloVentas);

        ventas.forEach(venta -> {
            modeloVentas.addRow(new Object[]{
                venta.getIdVenta(),
                venta.getCliente().getIdCliente(),
                venta.getMetodoPago(),
                venta.getImporteTotal(),
                venta.getFecha()});
        });
    }

    private void actualizarTablaDetalle(int x) {
        eliminarTodasLasFilas(modeloDetalles);

        for (int i = 0; i < ventas.get(x).getDetalles().size(); i++) {
            modeloDetalles.addRow(new Object[]{
                ventas.get(x).getDetalles().get(i).getProducto().getNombreProducto(),
                ventas.get(x).getDetalles().get(i).getProducto().getPrecioVenta(),
                ventas.get(x).getDetalles().get(i).getCantidad(),
                ventas.get(x).getDetalles().get(i).getImporte(),});
        }
    }

    private void actualizarTablaDetalle() {
        eliminarTodasLasFilas(modeloDetalles);

        float suma = 0;

        for (int i = 0; i < detalles.size(); i++) {
            modeloDetalles.addRow(new Object[]{
                detalles.get(i).getProducto().getNombreProducto(),
                detalles.get(i).getProducto().getPrecioVenta(),
                detalles.get(i).getCantidad(),
                detalles.get(i).getImporte()
            });
            suma += detalles.get(i).getImporte();
        }

        txtVmonto.setText("" + suma);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogClientes = new javax.swing.JDialog();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblClientesDialog = new javax.swing.JTable();
        jSeparator25 = new javax.swing.JSeparator();
        txtBuscarCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialogproducto = new javax.swing.JDialog();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblProductosDialog = new javax.swing.JTable();
        jSeparator23 = new javax.swing.JSeparator();
        txtBuscarProducto = new javax.swing.JTextField();
        btnBuscarProducto = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jDialogDetalles = new javax.swing.JDialog();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblDetallesDialog = new javax.swing.JTable();
        jDialogEmpleado = new javax.swing.JDialog();
        jSeparator26 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblEmpleadoDialog = new javax.swing.JTable();
        txtBuscarEmpleado = new javax.swing.JTextField();
        btnBuscarEmpleado = new javax.swing.JButton();
        btnSalir = new javax.swing.JLabel();
        panelInfoUsuario = new javax.swing.JPanel();
        foto = new javax.swing.JLabel();
        txtRolActivo = new javax.swing.JLabel();
        txtUsuarioActivo = new javax.swing.JLabel();
        panelNav = new javax.swing.JPanel();
        sec02 = new javax.swing.JLabel();
        sec03 = new javax.swing.JLabel();
        sec04 = new javax.swing.JLabel();
        sec06 = new javax.swing.JLabel();
        sec08 = new javax.swing.JLabel();
        cabecera = new javax.swing.JLabel();
        paneles = new javax.swing.JTabbedPane();
        panelCarrito = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtVcantidad = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalles = new javax.swing.JTable();
        cmbVmetodoPago = new javax.swing.JComboBox<>();
        btnAgregarCarrito = new javax.swing.JButton();
        btnEliminarElementoCarrito = new javax.swing.JButton();
        btnProcesarVenta = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtVmonto = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtVcliente = new javax.swing.JTextField();
        btnSelecCliente = new javax.swing.JButton();
        txtVproducto = new javax.swing.JTextField();
        btnSelectProducto = new javax.swing.JButton();
        panelVentas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnVactualizar = new javax.swing.JButton();
        btnVfiltrarXdia = new javax.swing.JButton();
        btnVfiltrarXSemana = new javax.swing.JButton();
        btnVfiltrarXquincena = new javax.swing.JButton();
        btnVfiltrarXmes = new javax.swing.JButton();
        jSeparator27 = new javax.swing.JSeparator();
        txtBuscarIdCliente = new javax.swing.JTextField();
        btnBuscarIdCliente = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        panelReportes = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblVentasReporte = new javax.swing.JTable();
        btnRactualizar = new javax.swing.JButton();
        btnRcrearComprobante = new javax.swing.JButton();
        panelClientes = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtCnombres = new javax.swing.JTextField();
        txtCapellidos = new javax.swing.JTextField();
        txtCdni = new javax.swing.JTextField();
        rbtCnatural = new javax.swing.JRadioButton();
        rbtCjuridico = new javax.swing.JRadioButton();
        txtCrazon = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtCruc = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        btnCregistrar = new javax.swing.JButton();
        btnCmostrar = new javax.swing.JButton();

        jDialogClientes.setTitle("Seleccionar un cliente");
        jDialogClientes.setLocationByPlatform(true);
        jDialogClientes.setModal(true);
        jDialogClientes.setResizable(false);
        jDialogClientes.setSize(new java.awt.Dimension(700, 350));
        jDialogClientes.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 131, 42), 1, true));

        tblClientesDialog.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        tblClientesDialog.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblClientesDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientesDialog.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblClientesDialog.setFocusable(false);
        tblClientesDialog.setGridColor(new java.awt.Color(51, 51, 51));
        tblClientesDialog.setRowHeight(25);
        tblClientesDialog.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblClientesDialog.getTableHeader().setResizingAllowed(false);
        tblClientesDialog.getTableHeader().setReorderingAllowed(false);
        tblClientesDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesDialogMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblClientesDialog);

        jDialogClientes.getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 59, 690, 280));

        jSeparator25.setForeground(new java.awt.Color(0, 0, 0));
        jDialogClientes.getContentPane().add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 260, 10));

        txtBuscarCliente.setBorder(null);
        jDialogClientes.getContentPane().add(txtBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 260, 25));

        btnBuscarCliente.setBackground(new java.awt.Color(34, 73, 24));
        btnBuscarCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-search-thin-32.png"))); // NOI18N
        btnBuscarCliente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnBuscarCliente.setBorderPainted(false);
        btnBuscarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCliente.setFocusPainted(false);
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        jDialogClientes.getContentPane().add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 70, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Buscar por DNI:");
        jDialogClientes.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        jDialogproducto.setTitle("Seleccionar un producto");
        jDialogproducto.setLocationByPlatform(true);
        jDialogproducto.setModal(true);
        jDialogproducto.setResizable(false);
        jDialogproducto.setSize(new java.awt.Dimension(700, 350));
        jDialogproducto.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProductosDialog.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblProductosDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProductosDialog.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblProductosDialog.setFocusable(false);
        tblProductosDialog.setGridColor(new java.awt.Color(51, 51, 51));
        tblProductosDialog.setRowHeight(25);
        tblProductosDialog.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblProductosDialog.getTableHeader().setResizingAllowed(false);
        tblProductosDialog.getTableHeader().setReorderingAllowed(false);
        tblProductosDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosDialogMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblProductosDialog);

        jDialogproducto.getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 690, 260));

        jSeparator23.setForeground(new java.awt.Color(0, 0, 0));
        jDialogproducto.getContentPane().add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 260, 10));

        txtBuscarProducto.setBorder(null);
        jDialogproducto.getContentPane().add(txtBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 260, 25));

        btnBuscarProducto.setBackground(new java.awt.Color(34, 73, 24));
        btnBuscarProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-search-thin-32.png"))); // NOI18N
        btnBuscarProducto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnBuscarProducto.setBorderPainted(false);
        btnBuscarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarProducto.setFocusPainted(false);
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });
        jDialogproducto.getContentPane().add(btnBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 70, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Buscar nombre de producto:");
        jDialogproducto.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jDialogDetalles.setTitle("Detalle de venta");
        jDialogDetalles.setLocationByPlatform(true);
        jDialogDetalles.setModal(true);
        jDialogDetalles.setResizable(false);
        jDialogDetalles.setSize(new java.awt.Dimension(700, 350));

        jScrollPane9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 131, 42), 1, true));

        tblDetallesDialog.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        tblDetallesDialog.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDetallesDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDetallesDialog.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDetallesDialog.setFocusable(false);
        tblDetallesDialog.setGridColor(new java.awt.Color(51, 51, 51));
        tblDetallesDialog.setRowHeight(25);
        tblDetallesDialog.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblDetallesDialog.getTableHeader().setResizingAllowed(false);
        tblDetallesDialog.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(tblDetallesDialog);

        javax.swing.GroupLayout jDialogDetallesLayout = new javax.swing.GroupLayout(jDialogDetalles.getContentPane());
        jDialogDetalles.getContentPane().setLayout(jDialogDetallesLayout);
        jDialogDetallesLayout.setHorizontalGroup(
            jDialogDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogDetallesLayout.setVerticalGroup(
            jDialogDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDialogEmpleado.setLocationByPlatform(true);
        jDialogEmpleado.setModal(true);
        jDialogEmpleado.setSize(new java.awt.Dimension(700, 350));
        jDialogEmpleado.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator26.setForeground(new java.awt.Color(0, 0, 0));
        jDialogEmpleado.getContentPane().add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 260, 10));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Buscar por DNI:");
        jDialogEmpleado.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        jScrollPane11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 131, 42), 1, true));

        tblEmpleadoDialog.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        tblEmpleadoDialog.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblEmpleadoDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEmpleadoDialog.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblEmpleadoDialog.setFocusable(false);
        tblEmpleadoDialog.setGridColor(new java.awt.Color(51, 51, 51));
        tblEmpleadoDialog.setRowHeight(25);
        tblEmpleadoDialog.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblEmpleadoDialog.getTableHeader().setResizingAllowed(false);
        tblEmpleadoDialog.getTableHeader().setReorderingAllowed(false);
        tblEmpleadoDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadoDialogMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblEmpleadoDialog);

        jDialogEmpleado.getContentPane().add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 690, 270));

        txtBuscarEmpleado.setBorder(null);
        jDialogEmpleado.getContentPane().add(txtBuscarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 260, 25));

        btnBuscarEmpleado.setBackground(new java.awt.Color(34, 73, 24));
        btnBuscarEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-search-thin-32.png"))); // NOI18N
        btnBuscarEmpleado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnBuscarEmpleado.setBorderPainted(false);
        btnBuscarEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarEmpleado.setFocusPainted(false);
        btnBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpleadoActionPerformed(evt);
            }
        });
        jDialogEmpleado.getContentPane().add(btnBuscarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 70, 40));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LianFarma");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir.setBackground(new java.awt.Color(45, 156, 35));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSalir.setText("X");
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.setOpaque(true);
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalirMouseExited(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 40, 40));

        panelInfoUsuario.setBackground(new java.awt.Color(45, 156, 35));
        panelInfoUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario.png"))); // NOI18N
        panelInfoUsuario.add(foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 190, 100));

        txtRolActivo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtRolActivo.setForeground(new java.awt.Color(255, 255, 255));
        txtRolActivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRolActivo.setText("ADMINISTRADOR");
        panelInfoUsuario.add(txtRolActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 190, 20));

        txtUsuarioActivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtUsuarioActivo.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuarioActivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtUsuarioActivo.setText("EDDY OLIVO AYALA");
        panelInfoUsuario.add(txtUsuarioActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 190, 20));

        getContentPane().add(panelInfoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 220));

        panelNav.setBackground(new java.awt.Color(45, 156, 35));
        panelNav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sec02.setBackground(new java.awt.Color(45, 156, 35));
        sec02.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec02.setForeground(new java.awt.Color(255, 255, 255));
        sec02.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec02.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-shopping-cart-26-32.png"))); // NOI18N
        sec02.setText("  Carrito         ");
        sec02.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sec02.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        sec02.setOpaque(true);
        sec02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sec02MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sec02MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sec02MouseExited(evt);
            }
        });
        panelNav.add(sec02, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 50));

        sec03.setBackground(new java.awt.Color(45, 156, 35));
        sec03.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec03.setForeground(new java.awt.Color(255, 255, 255));
        sec03.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec03.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-marketing-2-32.png"))); // NOI18N
        sec03.setText("  Ventas         ");
        sec03.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sec03.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        sec03.setOpaque(true);
        sec03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sec03MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sec03MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sec03MouseExited(evt);
            }
        });
        panelNav.add(sec03, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 50));

        sec04.setBackground(new java.awt.Color(45, 156, 35));
        sec04.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec04.setForeground(new java.awt.Color(255, 255, 255));
        sec04.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec04.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-task-list-square-lined-32.png"))); // NOI18N
        sec04.setText("  Reportes      ");
        sec04.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sec04.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        sec04.setOpaque(true);
        sec04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sec04MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sec04MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sec04MouseExited(evt);
            }
        });
        panelNav.add(sec04, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 190, 50));

        sec06.setBackground(new java.awt.Color(45, 156, 35));
        sec06.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec06.setForeground(new java.awt.Color(255, 255, 255));
        sec06.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec06.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-customer-12-32.png"))); // NOI18N
        sec06.setText("  Clientes        ");
        sec06.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sec06.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        sec06.setOpaque(true);
        sec06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sec06MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sec06MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sec06MouseExited(evt);
            }
        });
        panelNav.add(sec06, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 190, 50));

        sec08.setBackground(new java.awt.Color(45, 156, 35));
        sec08.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec08.setForeground(new java.awt.Color(255, 255, 255));
        sec08.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec08.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-log-out-12-32.png"))); // NOI18N
        sec08.setText("  Cerrar Sesión");
        sec08.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sec08.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        sec08.setOpaque(true);
        sec08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sec08MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sec08MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sec08MouseExited(evt);
            }
        });
        panelNav.add(sec08, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 190, 50));

        getContentPane().add(panelNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 190, 480));

        cabecera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.png"))); // NOI18N
        cabecera.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        cabecera.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                cabeceraMouseDragged(evt);
            }
        });
        cabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cabeceraMousePressed(evt);
            }
        });
        getContentPane().add(cabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 80));

        panelCarrito.setBackground(new java.awt.Color(255, 255, 255));
        panelCarrito.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        panelCarrito.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 580, 200, 10));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        panelCarrito.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 200, 10));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        panelCarrito.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 200, 10));

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        panelCarrito.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 200, 10));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Producto:");
        panelCarrito.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 74, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Cantidad:");
        panelCarrito.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Metodo de pago:");
        panelCarrito.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        txtVcantidad.setBorder(null);
        txtVcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVcantidadKeyTyped(evt);
            }
        });
        panelCarrito.add(txtVcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 200, 25));

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 131, 42), 1, true));

        tblDetalles.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        tblDetalles.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDetalles.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDetalles.setFocusable(false);
        tblDetalles.setGridColor(new java.awt.Color(51, 51, 51));
        tblDetalles.setRowHeight(25);
        tblDetalles.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblDetalles.getTableHeader().setResizingAllowed(false);
        tblDetalles.getTableHeader().setReorderingAllowed(false);
        tblDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetallesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDetalles);

        panelCarrito.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 810, 260));

        cmbVmetodoPago.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbVmetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));
        cmbVmetodoPago.setBorder(null);
        panelCarrito.add(cmbVmetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 200, 30));

        btnAgregarCarrito.setBackground(new java.awt.Color(34, 73, 24));
        btnAgregarCarrito.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAgregarCarrito.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-text-plus-lined-32.png"))); // NOI18N
        btnAgregarCarrito.setText("Agregar");
        btnAgregarCarrito.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnAgregarCarrito.setBorderPainted(false);
        btnAgregarCarrito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarCarrito.setFocusPainted(false);
        btnAgregarCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCarritoActionPerformed(evt);
            }
        });
        panelCarrito.add(btnAgregarCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 140, 40));

        btnEliminarElementoCarrito.setBackground(new java.awt.Color(34, 73, 24));
        btnEliminarElementoCarrito.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEliminarElementoCarrito.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarElementoCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-text-minus-lined-32.png"))); // NOI18N
        btnEliminarElementoCarrito.setText("Quitar");
        btnEliminarElementoCarrito.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnEliminarElementoCarrito.setBorderPainted(false);
        btnEliminarElementoCarrito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarElementoCarrito.setEnabled(false);
        btnEliminarElementoCarrito.setFocusPainted(false);
        btnEliminarElementoCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarElementoCarritoActionPerformed(evt);
            }
        });
        panelCarrito.add(btnEliminarElementoCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, 140, 40));

        btnProcesarVenta.setBackground(new java.awt.Color(34, 73, 24));
        btnProcesarVenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnProcesarVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnProcesarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-marketing-2-32.png"))); // NOI18N
        btnProcesarVenta.setText("Procesar");
        btnProcesarVenta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnProcesarVenta.setBorderPainted(false);
        btnProcesarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProcesarVenta.setFocusPainted(false);
        btnProcesarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarVentaActionPerformed(evt);
            }
        });
        panelCarrito.add(btnProcesarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 140, 40));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Importe Total");
        panelCarrito.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 560, -1, -1));

        txtVmonto.setEditable(false);
        txtVmonto.setBorder(null);
        panelCarrito.add(txtVmonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 560, 200, 25));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Cliente");
        panelCarrito.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, -1));

        txtVcliente.setEditable(false);
        txtVcliente.setBorder(null);
        panelCarrito.add(txtVcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 200, 25));

        btnSelecCliente.setBackground(new java.awt.Color(34, 73, 24));
        btnSelecCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSelecCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnSelecCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-search-thin-32.png"))); // NOI18N
        btnSelecCliente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnSelecCliente.setBorderPainted(false);
        btnSelecCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSelecCliente.setFocusPainted(false);
        btnSelecCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecClienteActionPerformed(evt);
            }
        });
        panelCarrito.add(btnSelecCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, 70, 40));

        txtVproducto.setEditable(false);
        txtVproducto.setBorder(null);
        panelCarrito.add(txtVproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 200, 25));

        btnSelectProducto.setBackground(new java.awt.Color(34, 73, 24));
        btnSelectProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSelectProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-search-thin-32.png"))); // NOI18N
        btnSelectProducto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnSelectProducto.setBorderPainted(false);
        btnSelectProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSelectProducto.setFocusPainted(false);
        btnSelectProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectProductoActionPerformed(evt);
            }
        });
        panelCarrito.add(btnSelectProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 70, 40));

        paneles.addTab("VENTAS", panelCarrito);

        panelVentas.setBackground(new java.awt.Color(255, 255, 255));
        panelVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 131, 42), 1, true));

        tblVentas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblVentas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVentas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblVentas.setFocusable(false);
        tblVentas.setGridColor(new java.awt.Color(51, 51, 51));
        tblVentas.setRowHeight(25);
        tblVentas.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblVentas.getTableHeader().setResizingAllowed(false);
        tblVentas.getTableHeader().setReorderingAllowed(false);
        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblVentas);

        panelVentas.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 820, 370));

        btnVactualizar.setBackground(new java.awt.Color(34, 73, 24));
        btnVactualizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVactualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnVactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-refresh-2-32.png"))); // NOI18N
        btnVactualizar.setText("Actualizar");
        btnVactualizar.setToolTipText("");
        btnVactualizar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnVactualizar.setBorderPainted(false);
        btnVactualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVactualizar.setFocusPainted(false);
        btnVactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVactualizarActionPerformed(evt);
            }
        });
        panelVentas.add(btnVactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 480, 180, 40));

        btnVfiltrarXdia.setBackground(new java.awt.Color(34, 73, 24));
        btnVfiltrarXdia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVfiltrarXdia.setForeground(new java.awt.Color(255, 255, 255));
        btnVfiltrarXdia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-refresh-2-32.png"))); // NOI18N
        btnVfiltrarXdia.setText("Filtrar por día");
        btnVfiltrarXdia.setToolTipText("");
        btnVfiltrarXdia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnVfiltrarXdia.setBorderPainted(false);
        btnVfiltrarXdia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVfiltrarXdia.setFocusPainted(false);
        btnVfiltrarXdia.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnVfiltrarXdia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVfiltrarXdiaActionPerformed(evt);
            }
        });
        panelVentas.add(btnVfiltrarXdia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 210, 40));

        btnVfiltrarXSemana.setBackground(new java.awt.Color(34, 73, 24));
        btnVfiltrarXSemana.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVfiltrarXSemana.setForeground(new java.awt.Color(255, 255, 255));
        btnVfiltrarXSemana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-refresh-2-32.png"))); // NOI18N
        btnVfiltrarXSemana.setText("Filtrar por semana");
        btnVfiltrarXSemana.setToolTipText("");
        btnVfiltrarXSemana.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnVfiltrarXSemana.setBorderPainted(false);
        btnVfiltrarXSemana.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVfiltrarXSemana.setFocusPainted(false);
        btnVfiltrarXSemana.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnVfiltrarXSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVfiltrarXSemanaActionPerformed(evt);
            }
        });
        panelVentas.add(btnVfiltrarXSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 200, 40));

        btnVfiltrarXquincena.setBackground(new java.awt.Color(34, 73, 24));
        btnVfiltrarXquincena.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVfiltrarXquincena.setForeground(new java.awt.Color(255, 255, 255));
        btnVfiltrarXquincena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-refresh-2-32.png"))); // NOI18N
        btnVfiltrarXquincena.setText("Filtrar por quincena");
        btnVfiltrarXquincena.setToolTipText("");
        btnVfiltrarXquincena.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnVfiltrarXquincena.setBorderPainted(false);
        btnVfiltrarXquincena.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVfiltrarXquincena.setFocusPainted(false);
        btnVfiltrarXquincena.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnVfiltrarXquincena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVfiltrarXquincenaActionPerformed(evt);
            }
        });
        panelVentas.add(btnVfiltrarXquincena, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 210, 40));

        btnVfiltrarXmes.setBackground(new java.awt.Color(34, 73, 24));
        btnVfiltrarXmes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVfiltrarXmes.setForeground(new java.awt.Color(255, 255, 255));
        btnVfiltrarXmes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-refresh-2-32.png"))); // NOI18N
        btnVfiltrarXmes.setText("Filtrar por mes");
        btnVfiltrarXmes.setToolTipText("");
        btnVfiltrarXmes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnVfiltrarXmes.setBorderPainted(false);
        btnVfiltrarXmes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVfiltrarXmes.setFocusPainted(false);
        btnVfiltrarXmes.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnVfiltrarXmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVfiltrarXmesActionPerformed(evt);
            }
        });
        panelVentas.add(btnVfiltrarXmes, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 550, 200, 40));

        jSeparator27.setForeground(new java.awt.Color(0, 0, 0));
        panelVentas.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 260, 10));

        txtBuscarIdCliente.setBorder(null);
        panelVentas.add(txtBuscarIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 260, 25));

        btnBuscarIdCliente.setBackground(new java.awt.Color(34, 73, 24));
        btnBuscarIdCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscarIdCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarIdCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-search-thin-32.png"))); // NOI18N
        btnBuscarIdCliente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnBuscarIdCliente.setBorderPainted(false);
        btnBuscarIdCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarIdCliente.setFocusPainted(false);
        btnBuscarIdCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarIdClienteActionPerformed(evt);
            }
        });
        panelVentas.add(btnBuscarIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 70, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Buscar por ID cliente:");
        panelVentas.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        paneles.addTab("COMPRABANTES", panelVentas);

        panelReportes.setBackground(new java.awt.Color(255, 255, 255));
        panelReportes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 131, 42), 1, true));

        tblVentasReporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblVentasReporte.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVentasReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVentasReporte.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblVentasReporte.setFocusable(false);
        tblVentasReporte.setGridColor(new java.awt.Color(51, 51, 51));
        tblVentasReporte.setRowHeight(25);
        tblVentasReporte.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblVentasReporte.getTableHeader().setResizingAllowed(false);
        tblVentasReporte.getTableHeader().setReorderingAllowed(false);
        tblVentasReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasReporteMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblVentasReporte);

        panelReportes.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 820, 370));

        btnRactualizar.setBackground(new java.awt.Color(34, 73, 24));
        btnRactualizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRactualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnRactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-refresh-2-32.png"))); // NOI18N
        btnRactualizar.setText("Actualizar");
        btnRactualizar.setToolTipText("");
        btnRactualizar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnRactualizar.setBorderPainted(false);
        btnRactualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRactualizar.setFocusPainted(false);
        btnRactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRactualizarActionPerformed(evt);
            }
        });
        panelReportes.add(btnRactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, 180, 40));

        btnRcrearComprobante.setBackground(new java.awt.Color(34, 73, 24));
        btnRcrearComprobante.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRcrearComprobante.setForeground(new java.awt.Color(255, 255, 255));
        btnRcrearComprobante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-refresh-2-32.png"))); // NOI18N
        btnRcrearComprobante.setText("Generar comprobante");
        btnRcrearComprobante.setToolTipText("");
        btnRcrearComprobante.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnRcrearComprobante.setBorderPainted(false);
        btnRcrearComprobante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRcrearComprobante.setEnabled(false);
        btnRcrearComprobante.setFocusPainted(false);
        btnRcrearComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRcrearComprobanteActionPerformed(evt);
            }
        });
        panelReportes.add(btnRcrearComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, 240, 40));

        paneles.addTab("REPORTES", panelReportes);

        panelClientes.setBackground(new java.awt.Color(255, 255, 255));
        panelClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 131, 42), 1, true));

        tblClientes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        tblClientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblClientes.setFocusable(false);
        tblClientes.setGridColor(new java.awt.Color(51, 51, 51));
        tblClientes.setRowHeight(25);
        tblClientes.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblClientes.getTableHeader().setResizingAllowed(false);
        tblClientes.getTableHeader().setReorderingAllowed(false);
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblClientes);

        panelClientes.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 830, 280));

        jSeparator16.setForeground(new java.awt.Color(0, 0, 0));
        panelClientes.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 220, 200, 10));

        jSeparator17.setForeground(new java.awt.Color(0, 0, 0));
        panelClientes.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 200, 10));

        jSeparator18.setForeground(new java.awt.Color(0, 0, 0));
        panelClientes.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 200, 10));

        jSeparator19.setForeground(new java.awt.Color(0, 0, 0));
        panelClientes.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 200, 10));

        jSeparator20.setForeground(new java.awt.Color(0, 0, 0));
        panelClientes.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 180, 200, 10));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setText("Nombres");
        panelClientes.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setText("Apellidos");
        panelClientes.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setText("DNI");
        panelClientes.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        txtCnombres.setBorder(null);
        txtCnombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCnombresFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCnombresFocusLost(evt);
            }
        });
        txtCnombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCnombresKeyTyped(evt);
            }
        });
        panelClientes.add(txtCnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 200, 25));

        txtCapellidos.setBorder(null);
        txtCapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapellidosKeyTyped(evt);
            }
        });
        panelClientes.add(txtCapellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 200, 25));

        txtCdni.setBorder(null);
        txtCdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCdniKeyTyped(evt);
            }
        });
        panelClientes.add(txtCdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 200, 25));

        buttonGroup1.add(rbtCnatural);
        rbtCnatural.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbtCnatural.setText("Natural");
        rbtCnatural.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCnaturalActionPerformed(evt);
            }
        });
        panelClientes.add(rbtCnatural, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, -1, -1));

        buttonGroup1.add(rbtCjuridico);
        rbtCjuridico.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbtCjuridico.setText("Jurídica");
        rbtCjuridico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCjuridicoActionPerformed(evt);
            }
        });
        panelClientes.add(rbtCjuridico, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, -1, -1));

        txtCrazon.setBorder(null);
        txtCrazon.setEnabled(false);
        panelClientes.add(txtCrazon, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, 200, 25));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("RUC");
        panelClientes.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, -1, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setText("Razon Social");
        panelClientes.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, -1, -1));

        txtCruc.setBorder(null);
        txtCruc.setEnabled(false);
        txtCruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCrucKeyTyped(evt);
            }
        });
        panelClientes.add(txtCruc, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 200, 25));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setText("Tipo de Persona");
        panelClientes.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, -1));

        btnCregistrar.setBackground(new java.awt.Color(62, 131, 42));
        btnCregistrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCregistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCregistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-text-plus-lined-32.png"))); // NOI18N
        btnCregistrar.setText("Registrar");
        btnCregistrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnCregistrar.setBorderPainted(false);
        btnCregistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCregistrar.setFocusPainted(false);
        btnCregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCregistrarActionPerformed(evt);
            }
        });
        panelClientes.add(btnCregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 140, 40));

        btnCmostrar.setBackground(new java.awt.Color(62, 131, 42));
        btnCmostrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCmostrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCmostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-list-lined-32.png"))); // NOI18N
        btnCmostrar.setText("Mostrar");
        btnCmostrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnCmostrar.setBorderPainted(false);
        btnCmostrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCmostrar.setFocusPainted(false);
        btnCmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCmostrarActionPerformed(evt);
            }
        });
        panelClientes.add(btnCmostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, 140, 40));

        paneles.addTab("CLIENTES", panelClientes);

        getContentPane().add(paneles, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCarritoActionPerformed
        try {
            int idProductoo = Integer.parseInt(txtVproducto.getText());
            int cantidad = Integer.parseInt(txtVcantidad.getText());
            int posPro = 0;

            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getIdProducto() == idProductoo) {
                    posPro = i;
                    break;
                }
            }

            detalles.add(new DetalleVenta(productos.get(posPro), cantidad));

            actualizarTablaDetalle();
            txtVproducto.setText("");
            txtVcantidad.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Seleccionar el producto y agregar una cantidad.");
        }


    }//GEN-LAST:event_btnAgregarCarritoActionPerformed

    private void btnProcesarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarVentaActionPerformed
        if (detalles.size() > 0) {
            try {
                int idClientee = Integer.parseInt(txtVcliente.getText());
                int posCli = 0;

                for (int i = 0; i < clientes.size(); i++) {
                    if (clientes.get(i).getIdCliente() == idClientee) {
                        posCli = i;
                        break;
                    }
                }

                String metodoPago = (String) cmbVmetodoPago.getSelectedItem();

                ventaJDBC.insert(new Venta(empleados.get(idEmpleadoActual),
                        clientes.get(posCli), detalles, metodoPago));

                detalles = new ArrayList<>();

                eliminarTodasLasFilas(modeloDetalles);

                JOptionPane.showMessageDialog(this, "Venta procesada.");
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Seleccionar un cliente.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "El carrito está vacío.");
        }
    }//GEN-LAST:event_btnProcesarVentaActionPerformed

    private void tblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasMouseClicked
        int x = tblVentas.getSelectedRow();

        actualizarTablaDetalle(x);

        jDialogDetalles.setVisible(true);
    }//GEN-LAST:event_tblVentasMouseClicked
    int xx, xy;
    private void cabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cabeceraMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_cabeceraMousePressed

    private void cabeceraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cabeceraMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_cabeceraMouseDragged

    private void btnEliminarElementoCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarElementoCarritoActionPerformed
        detalles.remove(idDetalle);
        btnEliminarElementoCarrito.setEnabled(false);
        actualizarTablaDetalle();

    }//GEN-LAST:event_btnEliminarElementoCarritoActionPerformed

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnSalirMouseClicked

    private void rbtCnaturalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtCnaturalActionPerformed
        txtCruc.setEnabled(false);
        txtCrazon.setEnabled(false);
        txtCruc.setText("");
        txtCrazon.setText("");
    }//GEN-LAST:event_rbtCnaturalActionPerformed

    private void rbtCjuridicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtCjuridicoActionPerformed
        txtCruc.setEnabled(true);
        txtCrazon.setEnabled(true);

    }//GEN-LAST:event_rbtCjuridicoActionPerformed

    private void btnCmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCmostrarActionPerformed
        clientes = clienteJDBC.select();
        actualizarTablaCliente();
        btnCregistrar.setEnabled(true);
        limpiarRegistrosClientes();
    }//GEN-LAST:event_btnCmostrarActionPerformed

    private void btnCregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCregistrarActionPerformed
        if (txtCdni.getText().length() == 8) {
            try {
                String nombres = txtCnombres.getText();
                String apellidos = txtCapellidos.getText();
                String dni = txtCdni.getText();

                if (rbtCnatural.isSelected()) {
                    clienteJDBC.insert(new Cliente("Natural", nombres, apellidos, dni, "", ""));
                    if (clienteJDBC.getError()) {
                        JOptionPane.showMessageDialog(this, "El DNI ya se encontraba registrado");
                    } else {
                        JOptionPane.showMessageDialog(this, "Cliente Registrado");
                        limpiarRegistrosClientes();
                    }
                }

                if (rbtCjuridico.isSelected()) {
                    if (txtCruc.getText().length() == 11) {
                        String ruc = txtCruc.getText();
                        String razon = txtCrazon.getText();
                        clienteJDBC.insert(new Cliente("Jurídica", nombres, apellidos, dni, ruc, razon));
                        if (clienteJDBC.getError()) {
                            JOptionPane.showMessageDialog(this, "El DNI ya se encontraba registrado");
                        } else {
                            JOptionPane.showMessageDialog(this, "Cliente Registrado");
                            limpiarRegistrosClientes();
                        }
                    } else {
                        txtCruc.setText("");
                        JOptionPane.showMessageDialog(this, "RUC no válido");
                    }
                }
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
            }
        } else {
            txtCdni.setText("");
            JOptionPane.showMessageDialog(this, "DNI no válido");
        }

    }//GEN-LAST:event_btnCregistrarActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        try {
            int x = tblClientes.getSelectedRow();

            idCliente = clientes.get(x).getIdCliente();
            txtCnombres.setText("" + clientes.get(x).getNombre());
            txtCapellidos.setText("" + clientes.get(x).getApellido());
            txtCdni.setText("" + clientes.get(x).getDni());
            txtCruc.setText("" + clientes.get(x).getRuc());
            txtCrazon.setText("" + clientes.get(x).getRazonSocial());

            if (clientes.get(x).getTipoPersona().equals("Natural")) {
                rbtCnatural.setSelected(true);
                txtCruc.setEnabled(false);
                txtCrazon.setEnabled(false);
            } else {
                rbtCjuridico.setSelected(true);
                txtCruc.setEnabled(true);
                txtCrazon.setEnabled(true);
            }

            btnCregistrar.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dar solo click izquiero.");
        }
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnSelecClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecClienteActionPerformed
        clientes = clienteJDBC.select();
        actualizarTablaCliente();
        jDialogClientes.setVisible(true);
    }//GEN-LAST:event_btnSelecClienteActionPerformed

    private void tblClientesDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesDialogMouseClicked
        int x = tblClientesDialog.getSelectedRow();
        idCliente = clientes.get(x).getIdCliente();

        txtVcliente.setText("" + idCliente);
        jDialogClientes.dispose();
    }//GEN-LAST:event_tblClientesDialogMouseClicked

    private void btnSelectProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectProductoActionPerformed
        productos = productoJDBC.select();
        actualizarTablaProducto();
        jDialogproducto.setVisible(true);
    }//GEN-LAST:event_btnSelectProductoActionPerformed

    private void tblProductosDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosDialogMouseClicked
        int x = tblProductosDialog.getSelectedRow();
        idProducto = productos.get(x).getIdProducto();

        txtVproducto.setText("" + idProducto);
        jDialogproducto.dispose();
    }//GEN-LAST:event_tblProductosDialogMouseClicked

    private void btnVactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVactualizarActionPerformed
        ventas = ventaJDBC.select(empleados, clientes, productos);
        actualizarTablaVenta();
    }//GEN-LAST:event_btnVactualizarActionPerformed

    private void btnSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseEntered
        btnSalir.setBackground(Color.RED);
        btnSalir.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSalirMouseEntered

    private void btnSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseExited
        btnSalir.setBackground(new Color(45, 156, 35));
        btnSalir.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnSalirMouseExited

    private void tblDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetallesMouseClicked
        idDetalle = tblDetalles.getSelectedRow();
        btnEliminarElementoCarrito.setEnabled(true);
    }//GEN-LAST:event_tblDetallesMouseClicked

    private void sec02MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec02MouseEntered
        sec02.setBackground(colorEntrar);
    }//GEN-LAST:event_sec02MouseEntered

    private void sec02MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec02MouseExited
        sec02.setBackground(colorSalir);
    }//GEN-LAST:event_sec02MouseExited

    private void sec03MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec03MouseEntered
        sec03.setBackground(colorEntrar);
    }//GEN-LAST:event_sec03MouseEntered

    private void sec03MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec03MouseExited
        sec03.setBackground(colorSalir);
    }//GEN-LAST:event_sec03MouseExited

    private void sec08MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec08MouseEntered
        sec08.setBackground(colorEntrar);
    }//GEN-LAST:event_sec08MouseEntered

    private void sec08MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec08MouseExited
        sec08.setBackground(colorSalir);
    }//GEN-LAST:event_sec08MouseExited

    private void txtCnombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCnombresKeyTyped
        validarSoloLetras(evt);
    }//GEN-LAST:event_txtCnombresKeyTyped

    private void txtCapellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapellidosKeyTyped
        validarSoloLetras(evt);
    }//GEN-LAST:event_txtCapellidosKeyTyped

    private void txtCdniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCdniKeyTyped
        validarSoloNumeros(evt);
        limitarEntradaDatos(txtCdni, 8, evt);
    }//GEN-LAST:event_txtCdniKeyTyped

    private void txtCrucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCrucKeyTyped
        validarSoloNumeros(evt);
        limitarEntradaDatos(txtCruc, 11, evt);
    }//GEN-LAST:event_txtCrucKeyTyped

    private void sec02MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec02MouseClicked
        paneles.setSelectedIndex(0);
        detalles = new ArrayList<>();
    }//GEN-LAST:event_sec02MouseClicked

    private void sec03MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec03MouseClicked
        paneles.setSelectedIndex(1);
    }//GEN-LAST:event_sec03MouseClicked

    private void sec08MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec08MouseClicked
        int op = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Salir", 0);
        if (op == 0) {
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_sec08MouseClicked

    private void txtCnombresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCnombresFocusGained
        txtCnombres.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_txtCnombresFocusGained

    private void txtCnombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCnombresFocusLost
        txtCnombres.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtCnombresFocusLost

    private void tblEmpleadoDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadoDialogMouseClicked
        
    }//GEN-LAST:event_tblEmpleadoDialogMouseClicked

    private void txtVcantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVcantidadKeyTyped
        validarSoloNumeros(evt);
    }//GEN-LAST:event_txtVcantidadKeyTyped

    private void btnVfiltrarXdiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVfiltrarXdiaActionPerformed
        String fechaHoy = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ventas = ventaJDBC.selectVentaDia(empleados, clientes, productos, fechaHoy);

        actualizarTablaVenta();

    }//GEN-LAST:event_btnVfiltrarXdiaActionPerformed

    private void btnVfiltrarXSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVfiltrarXSemanaActionPerformed
        ventas = ventaJDBC.selectVentaPorDias(empleados, clientes, productos, 7);
        actualizarTablaVenta();

    }//GEN-LAST:event_btnVfiltrarXSemanaActionPerformed

    private void btnVfiltrarXquincenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVfiltrarXquincenaActionPerformed
        ventas = ventaJDBC.selectVentaPorDias(empleados, clientes, productos, 15);
        actualizarTablaVenta();

    }//GEN-LAST:event_btnVfiltrarXquincenaActionPerformed

    private void btnVfiltrarXmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVfiltrarXmesActionPerformed
        ventas = ventaJDBC.selectVentaUltimoMes(empleados, clientes, productos);
        actualizarTablaVenta();
    }//GEN-LAST:event_btnVfiltrarXmesActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        String buscar = txtBuscarProducto.getText();

        productos = productoJDBC.search(buscar);
        actualizarTablaProducto();
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        String buscar = txtBuscarCliente.getText();

        clientes = clienteJDBC.search(buscar);
        actualizarTablaCliente();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpleadoActionPerformed
        String buscar = txtBuscarEmpleado.getText();

        empleados = empleadoJDBC.search(buscar);
        actualizarTablaEmpleado();
    }//GEN-LAST:event_btnBuscarEmpleadoActionPerformed

    private void btnBuscarIdClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarIdClienteActionPerformed
        String buscar = txtBuscarIdCliente.getText();

        ventas = ventaJDBC.search(empleados, clientes, productos, buscar);
        actualizarTablaVenta();
    }//GEN-LAST:event_btnBuscarIdClienteActionPerformed

    private void sec06MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec06MouseExited
        sec06.setBackground(colorSalir);
    }//GEN-LAST:event_sec06MouseExited

    private void sec06MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec06MouseEntered
        sec06.setBackground(colorEntrar);
    }//GEN-LAST:event_sec06MouseEntered

    private void sec06MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec06MouseClicked
        paneles.setSelectedIndex(3);
    }//GEN-LAST:event_sec06MouseClicked

    private void btnRcrearComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRcrearComprobanteActionPerformed
        // Generar Reporte pruebas
        Reporte reporte = new Reporte(Reporte.COMPROBANTE);
        reporte.crearReporte(ventas, idVenta);
        reporte.abrirarchivo();
    }//GEN-LAST:event_btnRcrearComprobanteActionPerformed

    private void btnRactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRactualizarActionPerformed
        ventas = ventaJDBC.selectVentaPorDias(empleados, clientes, productos, 15);
        ventas = ventaJDBC.select(empleados, clientes, productos);
        actualizarTablaVenta();
    }//GEN-LAST:event_btnRactualizarActionPerformed

    private void tblVentasReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasReporteMouseClicked
        idVenta = tblVentasReporte.getSelectedRow();
        btnRcrearComprobante.setEnabled(true);
    }//GEN-LAST:event_tblVentasReporteMouseClicked

    private void sec04MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec04MouseExited
        sec04.setBackground(colorSalir);
    }//GEN-LAST:event_sec04MouseExited

    private void sec04MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec04MouseEntered
        sec04.setBackground(colorEntrar);
    }//GEN-LAST:event_sec04MouseEntered

    private void sec04MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec04MouseClicked
        paneles.setSelectedIndex(2);
    }//GEN-LAST:event_sec04MouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlataformaUser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlataformaUser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlataformaUser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlataformaUser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlataformaUser(0).setVisible(true);
            }
        });
    }

    private void validarSoloLetras(java.awt.event.KeyEvent evt) {
        char validar = evt.getKeyChar();
        if (Character.isDigit(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo letras");
        }
    }

    private void validarSoloNumeros(java.awt.event.KeyEvent evt) {
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo numeros");
        }
    }

    private void limitarEntradaDatos(JTextField jText, int limite, java.awt.event.KeyEvent evt) {
        if (jText.getText().length() >= limite) {
            evt.consume();
        }
    }

    private void limpiarRegistrosClientes() {
        txtCapellidos.setText("");
        txtCdni.setText("");
        txtCnombres.setText("");
        txtCrazon.setText("");
        txtCruc.setText("");
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCarrito;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarEmpleado;
    private javax.swing.JButton btnBuscarIdCliente;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnCmostrar;
    private javax.swing.JButton btnCregistrar;
    private javax.swing.JButton btnEliminarElementoCarrito;
    private javax.swing.JButton btnProcesarVenta;
    private javax.swing.JButton btnRactualizar;
    private javax.swing.JButton btnRcrearComprobante;
    private javax.swing.JLabel btnSalir;
    private javax.swing.JButton btnSelecCliente;
    private javax.swing.JButton btnSelectProducto;
    private javax.swing.JButton btnVactualizar;
    private javax.swing.JButton btnVfiltrarXSemana;
    private javax.swing.JButton btnVfiltrarXdia;
    private javax.swing.JButton btnVfiltrarXmes;
    private javax.swing.JButton btnVfiltrarXquincena;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cabecera;
    private javax.swing.JComboBox<String> cmbVmetodoPago;
    private javax.swing.JLabel foto;
    private javax.swing.JDialog jDialogClientes;
    private javax.swing.JDialog jDialogDetalles;
    private javax.swing.JDialog jDialogEmpleado;
    private javax.swing.JDialog jDialogproducto;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel panelCarrito;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelInfoUsuario;
    private javax.swing.JPanel panelNav;
    private javax.swing.JPanel panelReportes;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JTabbedPane paneles;
    private javax.swing.JRadioButton rbtCjuridico;
    private javax.swing.JRadioButton rbtCnatural;
    private javax.swing.JLabel sec02;
    private javax.swing.JLabel sec03;
    private javax.swing.JLabel sec04;
    private javax.swing.JLabel sec06;
    private javax.swing.JLabel sec08;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblClientesDialog;
    private javax.swing.JTable tblDetalles;
    private javax.swing.JTable tblDetallesDialog;
    private javax.swing.JTable tblEmpleadoDialog;
    private javax.swing.JTable tblProductosDialog;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTable tblVentasReporte;
    public static javax.swing.JTextField txtBuscarCliente;
    public static javax.swing.JTextField txtBuscarEmpleado;
    public static javax.swing.JTextField txtBuscarIdCliente;
    public static javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtCapellidos;
    private javax.swing.JTextField txtCdni;
    private javax.swing.JTextField txtCnombres;
    private javax.swing.JTextField txtCrazon;
    private javax.swing.JTextField txtCruc;
    private javax.swing.JLabel txtRolActivo;
    private javax.swing.JLabel txtUsuarioActivo;
    private javax.swing.JTextField txtVcantidad;
    private javax.swing.JTextField txtVcliente;
    private javax.swing.JTextField txtVmonto;
    private javax.swing.JTextField txtVproducto;
    // End of variables declaration//GEN-END:variables
}
