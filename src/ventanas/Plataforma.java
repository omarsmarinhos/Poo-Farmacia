package ventanas;

import clases.Cliente;
import clases.DetalleVenta;
import clases.Empleado;
import clases.Producto;
import clases.Venta;
import conexion.ClienteJDBC;
import conexion.EmpleadoJDBC;
import conexion.ProductoJDBC;
import conexion.VentaJDBC;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Plataforma extends javax.swing.JFrame {

    ProductoJDBC productoJDBC = new ProductoJDBC();
    EmpleadoJDBC empleadoJDBC = new EmpleadoJDBC();
    ClienteJDBC clienteJDBC = new ClienteJDBC();
    VentaJDBC ventaJDBC = new VentaJDBC();

    DefaultTableModel modeloProdcutos = new DefaultTableModel();
    DefaultTableModel modeloClientes = new DefaultTableModel();
    DefaultTableModel modeloEmpleados = new DefaultTableModel();
    DefaultTableModel modeloVentas = new DefaultTableModel();
    DefaultTableModel modeloDetalles = new DefaultTableModel();

    ArrayList<Producto> productos = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Empleado> empleados = new ArrayList<>();
    ArrayList<Venta> ventas = new ArrayList<>();
    ArrayList<DetalleVenta> detalles = new ArrayList<>();

    int idProducto;
    int idEmpleado;
    int idCliente;
    int idDetalle;
    Map atributeas;

    public Plataforma(int id_empleado) {
        initComponents();

        iniciarTablas();
        empleados = empleadoJDBC.select();
        clientes = clienteJDBC.select();
        productos = productoJDBC.select();

        btnPactualizar.setEnabled(false);
        btnPeliminar.setEnabled(false);

        btnEactualizar.setEnabled(false);
        btnEeliminar.setEnabled(false);

        btnCactualizar.setEnabled(false);
        btnCeliminar.setEnabled(false);

        actualizarEmpleadoActual(id_empleado);

        //Provisional
        sec01.setBackground(new Color(19, 66, 15));
        select01.setBackground(new Color(255, 255, 255));

        //Hacer esto a todas las tablas
        tblCventas.getTableHeader().setFont(new Font("Segue UI", Font.BOLD, 14));
        tblCventas.getTableHeader().setForeground(Color.BLACK);
        tblCventas.setRowHeight(25);
        
        tblProductos.getTableHeader().setFont(new Font("Segue UI", Font.BOLD, 14));
        tblProductos.getTableHeader().setForeground(Color.BLACK);
        tblProductos.setRowHeight(25);

        //
        atributeas = sec01.getFont().getAttributes();
        atributeas.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

    }

    private void actualizarEmpleadoActual(int id_empleado) {
        txtUsuarioActivo.setText("" + empleadoJDBC.getEmpleadoActual(id_empleado));
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
        tblProductos.setModel(modeloProdcutos);
        tblEmpleados.setModel(modeloEmpleados);
        tblClientes.setModel(modeloClientes);
        tblDetalles.setModel(modeloDetalles);
        tblCventas.setModel(modeloVentas);
        tblClientesDialog.setModel(modeloClientes);
        tblProductosDialog.setModel(modeloProdcutos);
        tblDetallesDialog.setModel(modeloDetalles);
    }

    private void actualizarTablaProducto() {
        int nRow = modeloProdcutos.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloProdcutos.removeRow(i);
        }

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
        int nRow = modeloEmpleados.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloEmpleados.removeRow(i);
        }

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
        int nRow = modeloClientes.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloClientes.removeRow(i);
        }

        clientes.forEach(cliente -> {
            modeloClientes.addRow(new Object[]{
                cliente.getIdCliente(),
                cliente.getTipoPersona(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDni(),
                cliente.getRuc(),
                cliente.getRazonSocial(),});
        });
    }

    private void actualizarTablaVenta() {
        int nRow = modeloVentas.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloVentas.removeRow(i);
        }

        ventas.forEach(venta -> {
            modeloVentas.addRow(new Object[]{
                venta.getIdVenta(),
                venta.getCliente().getIdCliente(),
                venta.getMetodoPago(),
                venta.getImporteTotal(),
                venta.getFecha(),});
        });
    }

    private void actualizarTablaDetalle(int x) {
        int nRow = modeloDetalles.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloDetalles.removeRow(i);
        }

        for (int i = 0; i < ventas.get(x).getDetalles().size(); i++) {
            modeloDetalles.addRow(new Object[]{
                ventas.get(x).getDetalles().get(i).getProducto().getNombreProducto(),
                ventas.get(x).getDetalles().get(i).getProducto().getPrecioVenta(),
                ventas.get(x).getDetalles().get(i).getCantidad(),
                ventas.get(x).getDetalles().get(i).getImporte(),});
        }
    }

    private void actualizarTablaDetalle() {
        int nRow = modeloDetalles.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloDetalles.removeRow(i);
        }
        float suma = 0;
        for (int i = 0; i < detalles.size(); i++) {
            modeloDetalles.addRow(new Object[]{
                detalles.get(i).getProducto().getNombreProducto(),
                detalles.get(i).getProducto().getPrecioVenta(),
                detalles.get(i).getCantidad(),
                detalles.get(i).getImporte(),});
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialogproducto = new javax.swing.JDialog();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblProductosDialog = new javax.swing.JTable();
        jDialogDetalles = new javax.swing.JDialog();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblDetallesDialog = new javax.swing.JTable();
        btnSalir = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        foto = new javax.swing.JLabel();
        txtRolActivo = new javax.swing.JLabel();
        txtUsuarioActivo = new javax.swing.JLabel();
        cabecera = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        cmbPpresentacion = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPconcentracion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPfechaVenc = new javax.swing.JTextField();
        txtPnombre = new javax.swing.JTextField();
        btnPregistrar = new javax.swing.JButton();
        txtPbuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtPstock = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtPprecio = new javax.swing.JTextField();
        btnPmostrar = new javax.swing.JButton();
        btnPactualizar = new javax.swing.JButton();
        btnPeliminar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCventas = new javax.swing.JTable();
        btnVactualizar = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtEnombres = new javax.swing.JTextField();
        txtEapellidos = new javax.swing.JTextField();
        txtEdni = new javax.swing.JTextField();
        cmbEtipoEmpleado = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        btnEregistrar = new javax.swing.JButton();
        btnEactualizar = new javax.swing.JButton();
        btnEeliminar = new javax.swing.JButton();
        btnEmostrar = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtEsueldo = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtEcargo = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
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
        btnCactualizar = new javax.swing.JButton();
        btnCeliminar = new javax.swing.JButton();
        btnCmostrar = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        sec01 = new javax.swing.JLabel();
        sec02 = new javax.swing.JLabel();
        sec03 = new javax.swing.JLabel();
        sec04 = new javax.swing.JLabel();
        sec05 = new javax.swing.JLabel();
        sec06 = new javax.swing.JLabel();
        sec07 = new javax.swing.JLabel();
        sec08 = new javax.swing.JLabel();
        select01 = new javax.swing.JLabel();
        select02 = new javax.swing.JLabel();
        select03 = new javax.swing.JLabel();
        select04 = new javax.swing.JLabel();
        select05 = new javax.swing.JLabel();
        select06 = new javax.swing.JLabel();
        select07 = new javax.swing.JLabel();
        select08 = new javax.swing.JLabel();

        jDialogClientes.setTitle("Seleccionar un cliente");
        jDialogClientes.setLocationByPlatform(true);
        jDialogClientes.setModal(true);
        jDialogClientes.setResizable(false);
        jDialogClientes.setSize(new java.awt.Dimension(600, 300));

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
        tblClientesDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesDialogMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblClientesDialog);

        javax.swing.GroupLayout jDialogClientesLayout = new javax.swing.GroupLayout(jDialogClientes.getContentPane());
        jDialogClientes.getContentPane().setLayout(jDialogClientesLayout);
        jDialogClientesLayout.setHorizontalGroup(
            jDialogClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogClientesLayout.setVerticalGroup(
            jDialogClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDialogproducto.setTitle("Seleccionar un producto");
        jDialogproducto.setLocationByPlatform(true);
        jDialogproducto.setModal(true);
        jDialogproducto.setSize(new java.awt.Dimension(600, 300));

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
        tblProductosDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosDialogMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblProductosDialog);

        javax.swing.GroupLayout jDialogproductoLayout = new javax.swing.GroupLayout(jDialogproducto.getContentPane());
        jDialogproducto.getContentPane().setLayout(jDialogproductoLayout);
        jDialogproductoLayout.setHorizontalGroup(
            jDialogproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogproductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogproductoLayout.setVerticalGroup(
            jDialogproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogproductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDialogDetalles.setTitle("Detalle de venta");
        jDialogDetalles.setLocationByPlatform(true);
        jDialogDetalles.setModal(true);
        jDialogDetalles.setResizable(false);
        jDialogDetalles.setSize(new java.awt.Dimension(600, 300));

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
        jScrollPane9.setViewportView(tblDetallesDialog);

        javax.swing.GroupLayout jDialogDetallesLayout = new javax.swing.GroupLayout(jDialogDetalles.getContentPane());
        jDialogDetalles.getContentPane().setLayout(jDialogDetallesLayout);
        jDialogDetallesLayout.setHorizontalGroup(
            jDialogDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        jDialogDetallesLayout.setVerticalGroup(
            jDialogDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

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

        jPanel7.setBackground(new java.awt.Color(13, 44, 10));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario.png"))); // NOI18N
        jPanel7.add(foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 190, 100));

        txtRolActivo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtRolActivo.setForeground(new java.awt.Color(255, 255, 255));
        txtRolActivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRolActivo.setText("ADMINISTRADOR");
        jPanel7.add(txtRolActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 190, 20));

        txtUsuarioActivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtUsuarioActivo.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuarioActivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtUsuarioActivo.setText("EDDY OLIVO AYALA");
        jPanel7.add(txtUsuarioActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 190, 20));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 220));

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(716, 755));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 260, 10));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 200, 10));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 200, 10));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 210, 10));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 210, 10));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, 210, 10));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Presentación:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        cmbPpresentacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Comprimido", "Pildora" }));
        jPanel2.add(cmbPpresentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 200, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Precio:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        txtPconcentracion.setBorder(null);
        jPanel2.add(txtPconcentracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 210, 25));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Stock:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Fecha vencimiento:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, -1));

        txtPfechaVenc.setBorder(null);
        jPanel2.add(txtPfechaVenc, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 210, 25));

        txtPnombre.setBorder(null);
        jPanel2.add(txtPnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 200, 25));

        btnPregistrar.setBackground(new java.awt.Color(62, 131, 42));
        btnPregistrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPregistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnPregistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-text-plus-lined-32.png"))); // NOI18N
        btnPregistrar.setText("Registrar");
        btnPregistrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnPregistrar.setBorderPainted(false);
        btnPregistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPregistrar.setFocusPainted(false);
        btnPregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPregistrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnPregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 140, 40));

        txtPbuscar.setBorder(null);
        jPanel2.add(txtPbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 260, 25));

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 131, 42), 1, true));

        tblProductos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        tblProductos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblProductos.setFocusable(false);
        tblProductos.setGridColor(new java.awt.Color(51, 51, 51));
        tblProductos.setRowHeight(25);
        tblProductos.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblProductos.getTableHeader().setResizingAllowed(false);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 820, 270));

        jButton2.setBackground(new java.awt.Color(62, 131, 42));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-search-thin-32.png"))); // NOI18N
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 70, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Buscar nombre de producto:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Nombre:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        txtPstock.setBorder(null);
        txtPstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPstockKeyTyped(evt);
            }
        });
        jPanel2.add(txtPstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 200, 25));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Concetración:");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, -1));

        txtPprecio.setBorder(null);
        txtPprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPprecioKeyTyped(evt);
            }
        });
        jPanel2.add(txtPprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 210, 25));

        btnPmostrar.setBackground(new java.awt.Color(62, 131, 42));
        btnPmostrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPmostrar.setForeground(new java.awt.Color(255, 255, 255));
        btnPmostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-list-lined-32.png"))); // NOI18N
        btnPmostrar.setText("Mostrar");
        btnPmostrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnPmostrar.setBorderPainted(false);
        btnPmostrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPmostrar.setFocusPainted(false);
        btnPmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPmostrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnPmostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, 140, 40));

        btnPactualizar.setBackground(new java.awt.Color(62, 131, 42));
        btnPactualizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPactualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnPactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-reload-thin-32.png"))); // NOI18N
        btnPactualizar.setText("Actualizar");
        btnPactualizar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnPactualizar.setBorderPainted(false);
        btnPactualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPactualizar.setFocusPainted(false);
        btnPactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPactualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnPactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 140, 40));

        btnPeliminar.setBackground(new java.awt.Color(62, 131, 42));
        btnPeliminar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPeliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnPeliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-text-minus-lined-32.png"))); // NOI18N
        btnPeliminar.setText("Eliminar");
        btnPeliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnPeliminar.setBorderPainted(false);
        btnPeliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPeliminar.setFocusPainted(false);
        btnPeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnPeliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 140, 40));

        jTabbedPane1.addTab("Productos", jPanel2);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setText("Producto:");
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 40, 74, -1));

        jLabel20.setText("Cantidad:");
        jPanel9.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 71, -1, -1));

        jLabel22.setText("Metodo de pago:");
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 105, -1, -1));
        jPanel9.add(txtVcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 68, 110, -1));

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
        tblDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetallesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDetalles);

        jPanel9.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 189, 515, 231));

        cmbVmetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));
        jPanel9.add(cmbVmetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 102, -1, -1));

        btnAgregarCarrito.setText("Agregar");
        btnAgregarCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCarritoActionPerformed(evt);
            }
        });
        jPanel9.add(btnAgregarCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 155, 110, -1));

        btnEliminarElementoCarrito.setText("Eliminar");
        btnEliminarElementoCarrito.setEnabled(false);
        btnEliminarElementoCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarElementoCarritoActionPerformed(evt);
            }
        });
        jPanel9.add(btnEliminarElementoCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(478, 155, 114, -1));

        btnProcesarVenta.setText("PROCESAR VENTA");
        btnProcesarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarVentaActionPerformed(evt);
            }
        });
        jPanel9.add(btnProcesarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 438, -1, -1));

        jLabel26.setText("Importe Total");
        jPanel9.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 441, -1, -1));
        jPanel9.add(txtVmonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 438, 100, -1));

        jLabel39.setText("Cliente");
        jPanel9.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 40, -1, -1));

        txtVcliente.setEditable(false);
        jPanel9.add(txtVcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 37, 95, -1));

        btnSelecCliente.setText("Seleccionar");
        btnSelecCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnSelecCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 37, -1, -1));

        txtVproducto.setEditable(false);
        jPanel9.add(txtVproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 37, 107, -1));

        btnSelectProducto.setText("Seleccionar");
        btnSelectProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectProductoActionPerformed(evt);
            }
        });
        jPanel9.add(btnSelectProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 37, -1, -1));

        jTabbedPane1.addTab("VENTAS", jPanel9);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 131, 42), 1, true));

        tblCventas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblCventas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblCventas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCventas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblCventas.setFocusable(false);
        tblCventas.setGridColor(new java.awt.Color(51, 51, 51));
        tblCventas.setRowHeight(25);
        tblCventas.setRowMargin(1);
        tblCventas.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblCventas.getTableHeader().setResizingAllowed(false);
        tblCventas.getTableHeader().setReorderingAllowed(false);
        tblCventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCventasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCventas);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 820, 370));

        btnVactualizar.setBackground(new java.awt.Color(62, 131, 42));
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
        jPanel5.add(btnVactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 440, 180, 40));

        jTabbedPane1.addTab("COMPRABANTES", jPanel5);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jTabbedPane1.addTab("REPORTES", jPanel13);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Nombres");
        jPanel14.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 51, -1, -1));

        jLabel11.setText("Apellidos");
        jPanel14.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 91, -1, -1));

        jLabel21.setText("DNI");
        jPanel14.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 131, -1, -1));

        jLabel28.setText("Tipo de empleado");
        jPanel14.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 51, -1, -1));

        txtEnombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEnombresKeyTyped(evt);
            }
        });
        jPanel14.add(txtEnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 48, 150, -1));

        txtEapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEapellidosKeyTyped(evt);
            }
        });
        jPanel14.add(txtEapellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 88, 150, -1));

        txtEdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdniKeyTyped(evt);
            }
        });
        jPanel14.add(txtEdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 128, 150, -1));

        cmbEtipoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Usuario" }));
        jPanel14.add(cmbEtipoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 48, 150, -1));

        tblEmpleados.setAutoCreateRowSorter(true);
        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblEmpleados);

        jPanel14.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 239, 592, 247));

        btnEregistrar.setText("Agregar");
        btnEregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEregistrarActionPerformed(evt);
            }
        });
        jPanel14.add(btnEregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 183, -1, -1));

        btnEactualizar.setText("Actualizar");
        btnEactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEactualizarActionPerformed(evt);
            }
        });
        jPanel14.add(btnEactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 183, -1, -1));

        btnEeliminar.setText("Eliminar");
        btnEeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEeliminarActionPerformed(evt);
            }
        });
        jPanel14.add(btnEeliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 183, -1, -1));

        btnEmostrar.setText("Mostrar");
        btnEmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmostrarActionPerformed(evt);
            }
        });
        jPanel14.add(btnEmostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 183, -1, -1));

        jLabel29.setText("Sueldo");
        jPanel14.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 91, -1, -1));

        txtEsueldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEsueldoKeyTyped(evt);
            }
        });
        jPanel14.add(txtEsueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 88, 150, -1));

        jLabel30.setText("Cargo");
        jPanel14.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 131, -1, -1));
        jPanel14.add(txtEcargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 128, 150, -1));

        jTabbedPane1.addTab("EMPLEADOS", jPanel14);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblClientes);

        jPanel15.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 309, 698, 222));

        jLabel33.setText("Nombres");
        jPanel15.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 30, -1, -1));

        jLabel34.setText("Apellidos");
        jPanel15.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 70, -1, -1));

        jLabel35.setText("DNI");
        jPanel15.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 110, -1, -1));

        txtCnombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCnombresKeyTyped(evt);
            }
        });
        jPanel15.add(txtCnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 27, 150, -1));

        txtCapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapellidosKeyTyped(evt);
            }
        });
        jPanel15.add(txtCapellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 67, 150, -1));

        txtCdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCdniKeyTyped(evt);
            }
        });
        jPanel15.add(txtCdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 107, 150, -1));

        buttonGroup1.add(rbtCnatural);
        rbtCnatural.setText("Natural");
        rbtCnatural.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCnaturalActionPerformed(evt);
            }
        });
        jPanel15.add(rbtCnatural, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 29, -1, -1));

        buttonGroup1.add(rbtCjuridico);
        rbtCjuridico.setText("Jurídica");
        rbtCjuridico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCjuridicoActionPerformed(evt);
            }
        });
        jPanel15.add(rbtCjuridico, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 61, -1, -1));
        jPanel15.add(txtCrazon, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 147, 150, -1));

        jLabel36.setText("RUC");
        jPanel15.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 110, -1, -1));

        jLabel37.setText("Razon Social");
        jPanel15.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 150, -1, -1));

        txtCruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCrucKeyTyped(evt);
            }
        });
        jPanel15.add(txtCruc, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 107, 150, -1));

        jLabel38.setText("Tipo de Persona");
        jPanel15.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 31, -1, -1));

        btnCregistrar.setText("REGISTRAR");
        btnCregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCregistrarActionPerformed(evt);
            }
        });
        jPanel15.add(btnCregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 244, -1, -1));

        btnCactualizar.setText("Actualizar");
        btnCactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCactualizarActionPerformed(evt);
            }
        });
        jPanel15.add(btnCactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 244, -1, -1));

        btnCeliminar.setText("Eliminar");
        btnCeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCeliminarActionPerformed(evt);
            }
        });
        jPanel15.add(btnCeliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(367, 244, -1, -1));

        btnCmostrar.setText("Mostrar");
        btnCmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCmostrarActionPerformed(evt);
            }
        });
        jPanel15.add(btnCmostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 244, -1, -1));

        jTabbedPane1.addTab("CLIENTES", jPanel15);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jTabbedPane1.addTab("USUARIOS", jPanel16);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 650));

        jPanel10.setBackground(new java.awt.Color(13, 44, 10));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sec01.setBackground(new java.awt.Color(13, 44, 10));
        sec01.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec01.setForeground(new java.awt.Color(255, 255, 255));
        sec01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec01.setText("Productos");
        sec01.setToolTipText("");
        sec01.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sec01.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        sec01.setOpaque(true);
        sec01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sec01MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sec01MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sec01MouseExited(evt);
            }
        });
        jPanel10.add(sec01, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 50));

        sec02.setBackground(new java.awt.Color(13, 44, 10));
        sec02.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec02.setForeground(new java.awt.Color(255, 255, 255));
        sec02.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec02.setText("Carrito");
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
        jPanel10.add(sec02, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, 50));

        sec03.setBackground(new java.awt.Color(13, 44, 10));
        sec03.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec03.setForeground(new java.awt.Color(255, 255, 255));
        sec03.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec03.setText("Ventas");
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
        jPanel10.add(sec03, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 180, 50));

        sec04.setBackground(new java.awt.Color(13, 44, 10));
        sec04.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec04.setForeground(new java.awt.Color(255, 255, 255));
        sec04.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec04.setText("Reportes");
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
        jPanel10.add(sec04, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 180, 50));

        sec05.setBackground(new java.awt.Color(13, 44, 10));
        sec05.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec05.setForeground(new java.awt.Color(255, 255, 255));
        sec05.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec05.setText("Empleados");
        sec05.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sec05.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        sec05.setOpaque(true);
        sec05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sec05MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sec05MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sec05MouseExited(evt);
            }
        });
        jPanel10.add(sec05, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 180, 50));

        sec06.setBackground(new java.awt.Color(13, 44, 10));
        sec06.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec06.setForeground(new java.awt.Color(255, 255, 255));
        sec06.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec06.setText("Clientes");
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
        jPanel10.add(sec06, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 180, 50));

        sec07.setBackground(new java.awt.Color(13, 44, 10));
        sec07.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec07.setForeground(new java.awt.Color(255, 255, 255));
        sec07.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec07.setText("Usuarios");
        sec07.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sec07.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        sec07.setOpaque(true);
        sec07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sec07MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sec07MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sec07MouseExited(evt);
            }
        });
        jPanel10.add(sec07, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 180, 50));

        sec08.setBackground(new java.awt.Color(13, 44, 10));
        sec08.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec08.setForeground(new java.awt.Color(255, 255, 255));
        sec08.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec08.setText("Cerrar Sesión");
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
        jPanel10.add(sec08, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 180, 50));

        select01.setBackground(new java.awt.Color(13, 44, 10));
        select01.setOpaque(true);
        jPanel10.add(select01, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, 50));

        select02.setBackground(new java.awt.Color(13, 44, 10));
        select02.setOpaque(true);
        jPanel10.add(select02, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 5, 50));

        select03.setBackground(new java.awt.Color(13, 44, 10));
        select03.setOpaque(true);
        jPanel10.add(select03, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 5, 50));

        select04.setBackground(new java.awt.Color(13, 44, 10));
        select04.setOpaque(true);
        jPanel10.add(select04, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 5, 50));

        select05.setBackground(new java.awt.Color(13, 44, 10));
        select05.setOpaque(true);
        jPanel10.add(select05, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 5, 50));

        select06.setBackground(new java.awt.Color(13, 44, 10));
        select06.setOpaque(true);
        jPanel10.add(select06, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 5, 50));

        select07.setBackground(new java.awt.Color(13, 44, 10));
        select07.setOpaque(true);
        jPanel10.add(select07, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 5, 50));

        select08.setBackground(new java.awt.Color(13, 44, 10));
        select08.setOpaque(true);
        jPanel10.add(select08, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 5, 50));

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 190, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String buscar = txtPbuscar.getText();

        productos = productoJDBC.search(buscar);
        actualizarTablaProducto();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void limpiarRegistrosProductos() {
        txtPnombre.setText("");
        txtPconcentracion.setText("");
        txtPfechaVenc.setText("");
        txtPprecio.setText("");
        txtPstock.setText("");
    }

    private void limpiarRegistrosEmpleados() {
        txtEnombres.setText("");
        txtEapellidos.setText("");
        txtEdni.setText("");
        txtEsueldo.setText("");
        txtEcargo.setText("");
    }

    private void limpiarRegistrosClientes() {
        txtCapellidos.setText("");
        txtCdni.setText("");
        txtCnombres.setText("");
        txtCrazon.setText("");
        txtCruc.setText("");
    }

    private void btnPregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPregistrarActionPerformed

        try {
            String nombre = txtPnombre.getText();
            String presentacion = (String) cmbPpresentacion.getSelectedItem();
            int stock = Integer.parseInt(txtPstock.getText());
            float precio = Float.parseFloat(txtPprecio.getText());
            String concentracion = txtPconcentracion.getText();
            String fechaVecn = txtPfechaVenc.getText();
            productoJDBC.insert(new Producto(presentacion, nombre, concentracion, stock, precio, fechaVecn));
            limpiarRegistrosProductos();
            JOptionPane.showMessageDialog(this, "Producto Registrado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }

    }//GEN-LAST:event_btnPregistrarActionPerformed

    private void btnAgregarCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCarritoActionPerformed
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

        int nRow = modeloDetalles.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloDetalles.removeRow(i);
        }

        float suma = 0;
        for (int i = 0; i < detalles.size(); i++) {
            modeloDetalles.addRow(new Object[]{
                detalles.get(i).getProducto().getNombreProducto(),
                detalles.get(i).getProducto().getPrecioVenta(),
                detalles.get(i).getCantidad(),
                detalles.get(i).getImporte(),});
            suma += detalles.get(i).getImporte();
        }

        txtVmonto.setText("" + suma);

    }//GEN-LAST:event_btnAgregarCarritoActionPerformed

    private void btnProcesarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarVentaActionPerformed

        int idClientee = Integer.parseInt(txtVcliente.getText());
        int posCli = 0;
        int posEmp = 0;

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente() == idClientee) {
                posCli = i;
                break;
            }
        }

        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getidEmpleado() == idEmpleado) {
                posEmp = i;
                break;
            }
        }

        String metodoPago = (String) cmbVmetodoPago.getSelectedItem();

        ventaJDBC.insert(new Venta(empleados.get(posEmp),
                clientes.get(posCli), detalles, metodoPago));

        detalles = new ArrayList<>();
        JOptionPane.showMessageDialog(this, "Venta procesada");

        int nRow = modeloDetalles.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloDetalles.removeRow(i);
        }

    }//GEN-LAST:event_btnProcesarVentaActionPerformed

    private void tblCventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCventasMouseClicked
        int x = tblCventas.getSelectedRow();

        actualizarTablaDetalle(x);

        jDialogDetalles.setVisible(true);
    }//GEN-LAST:event_tblCventasMouseClicked

    private void btnPmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPmostrarActionPerformed
        productos = productoJDBC.select();
        actualizarTablaProducto();

        btnPregistrar.setEnabled(true);
        btnPactualizar.setEnabled(false);
        btnPeliminar.setEnabled(false);

    }//GEN-LAST:event_btnPmostrarActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        try {
            int x = tblProductos.getSelectedRow();

            idProducto = productos.get(x).getIdProducto();
            txtPnombre.setText("" + productos.get(x).getNombreProducto());
            txtPfechaVenc.setText("" + productos.get(x).getFechaVencimiento());
            txtPconcentracion.setText("" + productos.get(x).getConcentracion());
            txtPstock.setText("" + productos.get(x).getStock());
            txtPprecio.setText("" + productos.get(x).getPrecioVenta());
            cmbPpresentacion.setSelectedItem("" + productos.get(x).getPresentacion());
            btnPregistrar.setEnabled(false);
            btnPactualizar.setEnabled(true);
            btnPeliminar.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dar solo click izquiero.");
        }

    }//GEN-LAST:event_tblProductosMouseClicked

    private void btnPactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPactualizarActionPerformed

        try {
            String nombre = txtPnombre.getText();
            String presentacion = (String) cmbPpresentacion.getSelectedItem();
            int stock = Integer.parseInt(txtPstock.getText());
            float precio = Float.parseFloat(txtPprecio.getText());
            String concentracion = txtPconcentracion.getText();
            String fechaVecn = txtPfechaVenc.getText();

            productoJDBC.update(new Producto(idProducto, presentacion, nombre, concentracion, stock, precio, fechaVecn));

            limpiarRegistrosProductos();
            JOptionPane.showMessageDialog(this, "Producto actualizado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }

    }//GEN-LAST:event_btnPactualizarActionPerformed

    private void btnPeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeliminarActionPerformed
        productoJDBC.delete(new Producto(idProducto));

        limpiarRegistrosProductos();
        JOptionPane.showMessageDialog(this, "Producto Eliminado");
    }//GEN-LAST:event_btnPeliminarActionPerformed

    private void txtPstockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPstockKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo numeros");
        }
    }//GEN-LAST:event_txtPstockKeyTyped

    private void txtPprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPprecioKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo numeros");
        }
    }//GEN-LAST:event_txtPprecioKeyTyped
    int xx, xy;
    private void cabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cabeceraMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_cabeceraMousePressed

    private void btnEregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEregistrarActionPerformed
        try {
            String nombres = txtEnombres.getText();
            String apellidos = txtEapellidos.getText();
            String dni = txtEdni.getText();
            String tipo = (String) cmbEtipoEmpleado.getSelectedItem();
            float sueldo = Float.parseFloat(txtEsueldo.getText());
            String cargo = txtEcargo.getText();

            empleadoJDBC.insert(new Empleado(nombres, apellidos, dni, tipo, sueldo, cargo));

            limpiarRegistrosEmpleados();
            JOptionPane.showMessageDialog(this, "Empleado Regisrado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }

    }//GEN-LAST:event_btnEregistrarActionPerformed

    private void btnEmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmostrarActionPerformed
        empleados = empleadoJDBC.select();
        actualizarTablaEmpleado();

        btnEregistrar.setEnabled(true);
        btnEactualizar.setEnabled(false);
        btnEeliminar.setEnabled(false);
    }//GEN-LAST:event_btnEmostrarActionPerformed

    private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
        try {
            int x = tblEmpleados.getSelectedRow();

            idEmpleado = empleados.get(x).getidEmpleado();
            txtEnombres.setText("" + empleados.get(x).getNombre());
            txtEapellidos.setText("" + empleados.get(x).getApellido());
            txtEdni.setText("" + empleados.get(x).getDni());
            cmbEtipoEmpleado.setSelectedItem("" + empleados.get(x).getTipoEmpleado());
            txtEsueldo.setText("" + empleados.get(x).getSueldo());
            txtEcargo.setText("" + empleados.get(x).getCargo());
            btnEregistrar.setEnabled(false);
            btnEactualizar.setEnabled(true);
            btnEeliminar.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dar solo click izquierdo.");
        }

    }//GEN-LAST:event_tblEmpleadosMouseClicked

    private void btnEactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEactualizarActionPerformed
        try {
            String nombre = txtEnombres.getText();
            String apellidos = txtEapellidos.getText();
            String dni = txtEdni.getText();
            String tipo = (String) cmbEtipoEmpleado.getSelectedItem();
            float sueldo = Float.parseFloat(txtEsueldo.getText());
            String cargo = txtEcargo.getText();

            empleadoJDBC.update(new Empleado(idEmpleado, nombre, apellidos, dni, tipo, sueldo, cargo));

            limpiarRegistrosEmpleados();
            JOptionPane.showMessageDialog(this, "Empleado Actualizado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }


    }//GEN-LAST:event_btnEactualizarActionPerformed

    private void btnEeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEeliminarActionPerformed

        empleadoJDBC.delete(new Empleado(idEmpleado));
        limpiarRegistrosEmpleados();
        JOptionPane.showMessageDialog(this, "Empleado Eliminado");
    }//GEN-LAST:event_btnEeliminarActionPerformed

    private void cabeceraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cabeceraMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_cabeceraMouseDragged

    private void txtEdniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdniKeyTyped
        validarSoloNumeros(evt);
    }//GEN-LAST:event_txtEdniKeyTyped

    private void txtEsueldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEsueldoKeyTyped
        validarSoloNumeros(evt);
    }//GEN-LAST:event_txtEsueldoKeyTyped

    private void btnEliminarElementoCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarElementoCarritoActionPerformed
        detalles.remove(idDetalle);
        btnEliminarElementoCarrito.setEnabled(false);
        actualizarTablaDetalle();

    }//GEN-LAST:event_btnEliminarElementoCarritoActionPerformed

    private void sec01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec01MouseClicked
        panelSeleccionado(sec01, select01);
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_sec01MouseClicked

    private void sec02MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec02MouseClicked
        panelSeleccionado(sec02, select02);
        jTabbedPane1.setSelectedIndex(1);
        int nRow = modeloDetalles.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloDetalles.removeRow(i);
        }
    }//GEN-LAST:event_sec02MouseClicked

    private void sec03MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec03MouseClicked
        panelSeleccionado(sec03, select03);
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_sec03MouseClicked

    private void sec04MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec04MouseClicked
        panelSeleccionado(sec04, select04);
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_sec04MouseClicked

    private void sec05MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec05MouseClicked
        panelSeleccionado(sec05, select05);
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_sec05MouseClicked

    private void sec06MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec06MouseClicked
        panelSeleccionado(sec06, select06);
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_sec06MouseClicked

    private void sec08MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec08MouseClicked

        int op = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Salir", 0);
        if (op == 0) {
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_sec08MouseClicked

    private void sec07MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec07MouseClicked
        panelSeleccionado(sec07, select07);
        jTabbedPane1.setSelectedIndex(6);
    }//GEN-LAST:event_sec07MouseClicked

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
        btnCactualizar.setEnabled(false);
        btnCeliminar.setEnabled(false);
    }//GEN-LAST:event_btnCmostrarActionPerformed

    private void btnCregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCregistrarActionPerformed
        try {
            String nombres = txtCnombres.getText();
            String apellidos = txtCapellidos.getText();
            String dni = txtCdni.getText();

            if (rbtCnatural.isSelected()) {
                clienteJDBC.insert(new Cliente("Natural", nombres, apellidos, dni, "", ""));
            }

            if (rbtCjuridico.isSelected()) {
                String ruc = txtCruc.getText();
                String razon = txtCrazon.getText();
                clienteJDBC.insert(new Cliente("Jurídica", nombres, apellidos, dni, ruc, razon));
            }
            JOptionPane.showMessageDialog(this, "Cliente Registrado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }

    }//GEN-LAST:event_btnCregistrarActionPerformed

    private void btnCactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCactualizarActionPerformed
        try {
            String nombres = txtCnombres.getText();
            String apellidos = txtCapellidos.getText();
            String dni = txtCdni.getText();

            if (rbtCnatural.isSelected()) {
                clienteJDBC.update(new Cliente(idCliente, "Natural", nombres, apellidos, dni, "", ""));
            }

            if (rbtCjuridico.isSelected()) {
                String ruc = txtCruc.getText();
                String razon = txtCrazon.getText();
                clienteJDBC.update(new Cliente(idCliente, "Jurídica", nombres, apellidos, dni, ruc, razon));
            }
            JOptionPane.showMessageDialog(this, "Cliente Actualizado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }

    }//GEN-LAST:event_btnCactualizarActionPerformed

    private void btnCeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCeliminarActionPerformed
        clienteJDBC.delete(new Cliente(idCliente));
        JOptionPane.showMessageDialog(this, "Cliente Eliminado");
    }//GEN-LAST:event_btnCeliminarActionPerformed

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
            btnCactualizar.setEnabled(true);
            btnCeliminar.setEnabled(true);
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

    private void sec01MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec01MouseEntered
        sec01.setFont(sec01.getFont().deriveFont(atributeas));
    }//GEN-LAST:event_sec01MouseEntered

    private void sec01MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec01MouseExited
        sec01.setFont(sec02.getFont());
    }//GEN-LAST:event_sec01MouseExited

    private void sec02MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec02MouseEntered
        sec02.setFont(sec01.getFont().deriveFont(atributeas));
    }//GEN-LAST:event_sec02MouseEntered

    private void sec02MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec02MouseExited
        sec02.setFont(sec01.getFont());
    }//GEN-LAST:event_sec02MouseExited

    private void sec03MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec03MouseEntered
        sec03.setFont(sec01.getFont().deriveFont(atributeas));
    }//GEN-LAST:event_sec03MouseEntered

    private void sec03MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec03MouseExited
        sec03.setFont(sec02.getFont());
    }//GEN-LAST:event_sec03MouseExited

    private void sec04MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec04MouseEntered
        sec04.setFont(sec01.getFont().deriveFont(atributeas));
    }//GEN-LAST:event_sec04MouseEntered

    private void sec04MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec04MouseExited
        sec04.setFont(sec02.getFont());
    }//GEN-LAST:event_sec04MouseExited

    private void sec05MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec05MouseEntered
        sec05.setFont(sec01.getFont().deriveFont(atributeas));
    }//GEN-LAST:event_sec05MouseEntered

    private void sec05MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec05MouseExited
        sec05.setFont(sec02.getFont());
    }//GEN-LAST:event_sec05MouseExited

    private void sec06MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec06MouseEntered
        sec06.setFont(sec01.getFont().deriveFont(atributeas));
    }//GEN-LAST:event_sec06MouseEntered

    private void sec06MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec06MouseExited
        sec06.setFont(sec02.getFont());
    }//GEN-LAST:event_sec06MouseExited

    private void sec07MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec07MouseEntered
        sec07.setFont(sec01.getFont().deriveFont(atributeas));
    }//GEN-LAST:event_sec07MouseEntered

    private void sec07MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec07MouseExited
        sec07.setFont(sec02.getFont());
    }//GEN-LAST:event_sec07MouseExited

    private void sec08MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec08MouseEntered
        sec08.setFont(sec01.getFont().deriveFont(atributeas));
    }//GEN-LAST:event_sec08MouseEntered

    private void sec08MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec08MouseExited
        sec08.setFont(sec02.getFont());
    }//GEN-LAST:event_sec08MouseExited

    private void txtEnombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnombresKeyTyped
        validarSoloLetras(evt);
    }//GEN-LAST:event_txtEnombresKeyTyped

    private void txtEapellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEapellidosKeyTyped
        validarSoloLetras(evt);
    }//GEN-LAST:event_txtEapellidosKeyTyped

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
            java.util.logging.Logger.getLogger(Plataforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Plataforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Plataforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Plataforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Plataforma(0).setVisible(true);
            }
        });
    }

    private void panelSeleccionado(JLabel sec, JLabel select) {
        sec01.setBackground(new Color(13, 44, 10));
        sec02.setBackground(new Color(13, 44, 10));
        sec03.setBackground(new Color(13, 44, 10));
        sec04.setBackground(new Color(13, 44, 10));
        sec05.setBackground(new Color(13, 44, 10));
        sec06.setBackground(new Color(13, 44, 10));
        sec07.setBackground(new Color(13, 44, 10));
        sec08.setBackground(new Color(13, 44, 10));
        select01.setBackground(new Color(13, 44, 10));
        select02.setBackground(new Color(13, 44, 10));
        select03.setBackground(new Color(13, 44, 10));
        select04.setBackground(new Color(13, 44, 10));
        select05.setBackground(new Color(13, 44, 10));
        select06.setBackground(new Color(13, 44, 10));
        select07.setBackground(new Color(13, 44, 10));
        select08.setBackground(new Color(13, 44, 10));
        sec.setBackground(new Color(19, 66, 15));
        select.setBackground(new Color(255, 255, 255));
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCarrito;
    private javax.swing.JButton btnCactualizar;
    private javax.swing.JButton btnCeliminar;
    private javax.swing.JButton btnCmostrar;
    private javax.swing.JButton btnCregistrar;
    private javax.swing.JButton btnEactualizar;
    private javax.swing.JButton btnEeliminar;
    private javax.swing.JButton btnEliminarElementoCarrito;
    private javax.swing.JButton btnEmostrar;
    private javax.swing.JButton btnEregistrar;
    private javax.swing.JButton btnPactualizar;
    private javax.swing.JButton btnPeliminar;
    private javax.swing.JButton btnPmostrar;
    public static javax.swing.JButton btnPregistrar;
    private javax.swing.JButton btnProcesarVenta;
    private javax.swing.JLabel btnSalir;
    private javax.swing.JButton btnSelecCliente;
    private javax.swing.JButton btnSelectProducto;
    private javax.swing.JButton btnVactualizar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cabecera;
    private javax.swing.JComboBox<String> cmbEtipoEmpleado;
    private javax.swing.JComboBox<String> cmbPpresentacion;
    private javax.swing.JComboBox<String> cmbVmetodoPago;
    private javax.swing.JLabel foto;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialogClientes;
    private javax.swing.JDialog jDialogDetalles;
    private javax.swing.JDialog jDialogproducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rbtCjuridico;
    private javax.swing.JRadioButton rbtCnatural;
    private javax.swing.JLabel sec01;
    private javax.swing.JLabel sec02;
    private javax.swing.JLabel sec03;
    private javax.swing.JLabel sec04;
    private javax.swing.JLabel sec05;
    private javax.swing.JLabel sec06;
    private javax.swing.JLabel sec07;
    private javax.swing.JLabel sec08;
    private javax.swing.JLabel select01;
    private javax.swing.JLabel select02;
    private javax.swing.JLabel select03;
    private javax.swing.JLabel select04;
    private javax.swing.JLabel select05;
    private javax.swing.JLabel select06;
    private javax.swing.JLabel select07;
    private javax.swing.JLabel select08;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblClientesDialog;
    private javax.swing.JTable tblCventas;
    private javax.swing.JTable tblDetalles;
    private javax.swing.JTable tblDetallesDialog;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProductosDialog;
    private javax.swing.JTextField txtCapellidos;
    private javax.swing.JTextField txtCdni;
    private javax.swing.JTextField txtCnombres;
    private javax.swing.JTextField txtCrazon;
    private javax.swing.JTextField txtCruc;
    private javax.swing.JTextField txtEapellidos;
    private javax.swing.JTextField txtEcargo;
    private javax.swing.JTextField txtEdni;
    private javax.swing.JTextField txtEnombres;
    private javax.swing.JTextField txtEsueldo;
    public static javax.swing.JTextField txtPbuscar;
    private javax.swing.JTextField txtPconcentracion;
    private javax.swing.JTextField txtPfechaVenc;
    public static javax.swing.JTextField txtPnombre;
    private javax.swing.JTextField txtPprecio;
    private javax.swing.JTextField txtPstock;
    private javax.swing.JLabel txtRolActivo;
    private javax.swing.JLabel txtUsuarioActivo;
    private javax.swing.JTextField txtVcantidad;
    private javax.swing.JTextField txtVcliente;
    private javax.swing.JTextField txtVmonto;
    private javax.swing.JTextField txtVproducto;
    // End of variables declaration//GEN-END:variables
}
