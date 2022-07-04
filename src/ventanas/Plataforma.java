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
import java.util.ArrayList;
import javax.swing.JOptionPane;
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

        actualizarEmpleadoActual(id_empleado);

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
    }

    private void actualizarTablaProducto() {
        int nRow = modeloProdcutos.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloProdcutos.removeRow(i);
        }

        for (int i = 0; i < productos.size(); i++) {
            modeloProdcutos.addRow(new Object[]{
                productos.get(i).getIdProducto(),
                productos.get(i).getPresentacion(),
                productos.get(i).getNombreProducto(),
                productos.get(i).getConcentracion(),
                productos.get(i).getStock(),
                productos.get(i).getPrecioVenta(),
                productos.get(i).getFechaVencimiento()
            });
        }
    }

    private void actualizarTablaEmpleado() {
        int nRow = modeloEmpleados.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloEmpleados.removeRow(i);
        }

        for (int i = 0; i < empleados.size(); i++) {
            modeloEmpleados.addRow(new Object[]{
                empleados.get(i).getidEmpleado(),
                empleados.get(i).getNombre(),
                empleados.get(i).getApellido(),
                empleados.get(i).getDni(),
                empleados.get(i).getTipoEmpleado(),
                empleados.get(i).getSueldo(),
                empleados.get(i).getCargo()
            });
        }
    }

    private void actualizarTablaCliente() {
        int nRow = modeloClientes.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloClientes.removeRow(i);
        }

        for (int i = 0; i < clientes.size(); i++) {
            modeloClientes.addRow(new Object[]{
                clientes.get(i).getIdCliente(),
                clientes.get(i).getTipoPersona(),
                clientes.get(i).getNombre(),
                clientes.get(i).getApellido(),
                clientes.get(i).getDni(),
                clientes.get(i).getRuc(),
                clientes.get(i).getRazonSocial(),});
        }
    }

    private void actualizarTablaVenta() {
        int nRow = modeloVentas.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloVentas.removeRow(i);
        }

        for (int i = 0; i < ventas.size(); i++) {
            modeloVentas.addRow(new Object[]{
                ventas.get(i).getIdVenta(),
                ventas.get(i).getCliente().getIdCliente(),
                ventas.get(i).getMetodoPago(),
                ventas.get(i).getImporteTotal(),
                ventas.get(i).getFecha(),});
        }
    }

    private void actualizarEmpleadoActual(int id_empleado) {
        txtUsuarioActivo.setText("" + empleadoJDBC.getEmpleadoActual(id_empleado));
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
        jLabel32 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbPpresentacion = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPconcentracion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPfechaVenc = new javax.swing.JTextField();
        txtPnombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnPregistrar = new javax.swing.JButton();
        txtPbuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
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
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtVmonto = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtVcliente = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        txtVproducto = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCventas = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
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
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtUsuarioActivo = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

        jDialogClientes.setLocationByPlatform(true);
        jDialogClientes.setModal(true);
        jDialogClientes.setPreferredSize(new java.awt.Dimension(600, 300));
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LianFarma");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/conectado.png"))); // NOI18N
        jLabel32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 50, 40));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/x.png"))); // NOI18N
        jLabel31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 40, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLabel8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel8MouseDragged(evt);
            }
        });
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 80));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(716, 755));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Presentación");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        cmbPpresentacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Comprimido", "Pildora" }));
        jPanel2.add(cmbPpresentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 120, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Precio");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, -1));
        jPanel2.add(txtPconcentracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 108, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REGITRAR PRODUCTO");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 30, 710, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Stock");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Fecha vencimiento");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));
        jPanel2.add(txtPfechaVenc, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 108, -1));
        jPanel2.add(txtPnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 120, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("BUSCAR PRODUCTOS");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 591, -1));

        btnPregistrar.setBackground(new java.awt.Color(204, 204, 255));
        btnPregistrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPregistrar.setForeground(new java.awt.Color(51, 51, 51));
        btnPregistrar.setText("Registrar");
        btnPregistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnPregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPregistrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnPregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 130, 30));
        jPanel2.add(txtPbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 210, -1));

        jScrollPane1.setBorder(null);

        tblProductos.setAutoCreateRowSorter(true);
        tblProductos.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        tblProductos.setForeground(new java.awt.Color(102, 102, 102));
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
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 590, 160));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 102, 102));
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 115, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Buscar nombre de producto:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        jLabel25.setForeground(new java.awt.Color(153, 153, 255));
        jLabel25.setText("_______________________________________________________________________________________________________________________________________");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 680, 20));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Nombre");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        txtPstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPstockKeyTyped(evt);
            }
        });
        jPanel2.add(txtPstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 120, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setText("Concetración");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, -1));

        txtPprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPprecioKeyTyped(evt);
            }
        });
        jPanel2.add(txtPprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 110, -1));

        btnPmostrar.setText("Mostrar");
        btnPmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPmostrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnPmostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, -1, -1));

        btnPactualizar.setText("Actualizar");
        btnPactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPactualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnPactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));

        btnPeliminar.setText("Eliminar");
        btnPeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnPeliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jTabbedPane1.addTab("Productos", jPanel2);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setText("Producto:");

        jLabel20.setText("Cantidad:");

        jLabel22.setText("Metodo de pago:");

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
        jScrollPane2.setViewportView(tblDetalles);

        cmbVmetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("PROCESAR VENTA");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel26.setText("Importe Total");

        jLabel39.setText("Cliente");

        txtVcliente.setEditable(false);

        jButton4.setText("Seleccionar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtVproducto.setEditable(false);

        jButton6.setText("Seleccionar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtVproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel39)
                        .addGap(18, 18, 18)
                        .addComponent(txtVcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(157, 157, 157))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbVmetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtVcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(556, 556, 556))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addGap(199, 199, 199)
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(txtVmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(102, 102, 102))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel39)
                    .addComponent(txtVcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(txtVproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtVcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cmbVmetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jLabel26)
                    .addComponent(txtVmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("VENTAS", jPanel9);

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
        tblCventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCventasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCventas);

        jButton7.setText("Actualizar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("COMPRABANTES", jPanel5);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("REPORTES", jPanel13);

        jLabel10.setText("Nombres");

        jLabel11.setText("Apellidos");

        jLabel21.setText("DNI");

        jLabel28.setText("Tipo de empleado");

        txtEdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdniKeyTyped(evt);
            }
        });

        cmbEtipoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Usuario" }));

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

        btnEregistrar.setText("Agregar");
        btnEregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEregistrarActionPerformed(evt);
            }
        });

        btnEactualizar.setText("Actualizar");
        btnEactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEactualizarActionPerformed(evt);
            }
        });

        btnEeliminar.setText("Eliminar");
        btnEeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEeliminarActionPerformed(evt);
            }
        });

        btnEmostrar.setText("Mostrar");
        btnEmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmostrarActionPerformed(evt);
            }
        });

        jLabel29.setText("Sueldo");

        txtEsueldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEsueldoKeyTyped(evt);
            }
        });

        jLabel30.setText("Cargo");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtEnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel21))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEdni, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnEregistrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEactualizar)))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(btnEeliminar)
                                .addGap(48, 48, 48)
                                .addComponent(btnEmostrar))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel30))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbEtipoEmpleado, 0, 150, Short.MAX_VALUE)
                                    .addComponent(txtEsueldo)
                                    .addComponent(txtEcargo))))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel28)
                    .addComponent(txtEnombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEtipoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtEapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(txtEsueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtEdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(txtEcargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEregistrar)
                    .addComponent(btnEactualizar)
                    .addComponent(btnEeliminar)
                    .addComponent(btnEmostrar))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("EMPLEADOS", jPanel14);

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

        jLabel33.setText("Nombres");

        jLabel34.setText("Apellidos");

        jLabel35.setText("DNI");

        buttonGroup1.add(rbtCnatural);
        rbtCnatural.setText("Natural");
        rbtCnatural.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCnaturalActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtCjuridico);
        rbtCjuridico.setText("Jurídica");
        rbtCjuridico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCjuridicoActionPerformed(evt);
            }
        });

        jLabel36.setText("RUC");

        jLabel37.setText("Razon Social");

        jLabel38.setText("Tipo de Persona");

        btnCregistrar.setText("Agregar");
        btnCregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCregistrarActionPerformed(evt);
            }
        });

        btnCactualizar.setText("Actualizar");
        btnCactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCactualizarActionPerformed(evt);
            }
        });

        btnCeliminar.setText("Eliminar");
        btnCeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCeliminarActionPerformed(evt);
            }
        });

        btnCmostrar.setText("Mostrar");
        btnCmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCmostrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnCregistrar)
                        .addGap(87, 87, 87)
                        .addComponent(btnCactualizar))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCdni, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtCnatural)
                                    .addComponent(rbtCjuridico))
                                .addGap(163, 163, 163))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel37))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCruc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCrazon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(86, 86, 86))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(btnCeliminar)
                        .addGap(86, 86, 86)
                        .addComponent(btnCmostrar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txtCnombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtCnatural)
                            .addComponent(jLabel38))))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(rbtCjuridico))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCrazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCregistrar)
                    .addComponent(btnCactualizar)
                    .addComponent(btnCeliminar)
                    .addComponent(btnCmostrar))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("CLIENTES", jPanel15);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("USUARIOS", jPanel16);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 710, 570));

        jPanel7.setBackground(new java.awt.Color(225, 238, 230));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario.png"))); // NOI18N
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 80, 90));

        txtUsuarioActivo.setForeground(new java.awt.Color(102, 102, 102));
        txtUsuarioActivo.setText("EDDY OLIVO AYALA");
        jPanel7.add(txtUsuarioActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 120, 20));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 190, 140));

        jPanel10.setBackground(new java.awt.Color(225, 238, 230));

        jLabel12.setBackground(new java.awt.Color(225, 238, 230));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cs.png"))); // NOI18N
        jLabel12.setText("CERRAR SESION");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel12.setOpaque(true);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(225, 238, 230));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jLabel13.setText("PRODUCTOS");
        jLabel13.setToolTipText("");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel13.setOpaque(true);
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(225, 238, 230));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/papel.png"))); // NOI18N
        jLabel14.setText("REGISTRAR VENTA");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel14.setOpaque(true);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(225, 238, 230));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reporte.png"))); // NOI18N
        jLabel15.setText("COMPROBANTES");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel15.setOpaque(true);
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(225, 238, 230));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bolsa.png"))); // NOI18N
        jLabel16.setText("???");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel16.setOpaque(true);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(225, 238, 230));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sorporte (1).png"))); // NOI18N
        jLabel17.setText("EMPLEADOS");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel17.setOpaque(true);
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(225, 238, 230));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sorporte (1).png"))); // NOI18N
        jLabel23.setText("CLIENTES");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel23.setOpaque(true);
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel23MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel23MouseExited(evt);
            }
        });

        jLabel24.setBackground(new java.awt.Color(225, 238, 230));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sorporte (1).png"))); // NOI18N
        jLabel24.setText("USUARIOS");
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel24.setOpaque(true);
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel24MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel24MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 190, 400));

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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }

    }//GEN-LAST:event_btnPregistrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int idProductoo = Integer.parseInt(txtVproducto.getText());
        int cantidad = Integer.parseInt(txtVcantidad.getText());
        int posPro = 0;

        

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getIdProducto() == idProductoo) {
                posPro = i;
                break;
            }
        }

        

        float suma = 0;

        detalles.add(new DetalleVenta(productos.get(posPro), cantidad));

        int nRow = modeloDetalles.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloDetalles.removeRow(i);
        }

        for (int i = 0; i < detalles.size(); i++) {
            modeloDetalles.addRow(new Object[]{
                detalles.get(i).getProducto().getNombreProducto(),
                detalles.get(i).getProducto().getPrecioVenta(),
                detalles.get(i).getCantidad(),
                detalles.get(i).getImporte(),});
            suma += detalles.get(i).getImporte();
        }

        txtVmonto.setText("" + suma);


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

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
            if (empleados.get(i).getidEmpleado()== idEmpleado) {
                posEmp = i;
                break;
            }
        }
        
        String metodoPago = (String) cmbVmetodoPago.getSelectedItem();
        
        ventaJDBC.insert(new Venta(empleados.get(posEmp),
                clientes.get(posCli), detalles, metodoPago));
        
        detalles = new ArrayList<>();
        JOptionPane.showMessageDialog(this, "Venta procesada");


    }//GEN-LAST:event_jButton5ActionPerformed

    private void tblCventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCventasMouseClicked
        int x = tblCventas.getSelectedRow();
        String karma = "";
        for (int i = 0; i < ventas.get(x).getDetalles().size(); i++) {
            karma += "Producto = " + ventas.get(x).getDetalles().get(i).getProducto().getNombreProducto()
                    + " - Precio = " + ventas.get(x).getDetalles().get(i).getProducto().getPrecioVenta()
                    + " - Cantidad = " + ventas.get(x).getDetalles().get(i).getCantidad()
                    + " - Importe = " + ventas.get(x).getDetalles().get(i).getImporte() + "\n";
        }

        JOptionPane.showMessageDialog(this, karma);
    }//GEN-LAST:event_tblCventasMouseClicked

    private void btnPmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPmostrarActionPerformed
        productos = productoJDBC.select();
        actualizarTablaProducto();

        btnPregistrar.setEnabled(true);
        btnPactualizar.setEnabled(false);
        btnPeliminar.setEnabled(false);

    }//GEN-LAST:event_btnPmostrarActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }

    }//GEN-LAST:event_btnPactualizarActionPerformed

    private void btnPeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeliminarActionPerformed
        String nombre = txtPnombre.getText();
        String presentacion = (String) cmbPpresentacion.getSelectedItem();
        int stock = Integer.parseInt(txtPstock.getText());
        float precio = Float.parseFloat(txtPprecio.getText());
        String concentracion = txtPconcentracion.getText();
        String fechaVecn = txtPfechaVenc.getText();

        productoJDBC.delete(new Producto(idProducto, presentacion, nombre, concentracion, stock, precio, fechaVecn));

        limpiarRegistrosProductos();
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
    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jLabel8MousePressed

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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }


    }//GEN-LAST:event_btnEactualizarActionPerformed

    private void btnEeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEeliminarActionPerformed

        empleadoJDBC.delete(new Empleado(idEmpleado));
        limpiarRegistrosEmpleados();
    }//GEN-LAST:event_btnEeliminarActionPerformed

    private void jLabel8MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jLabel8MouseDragged

    private void txtEdniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdniKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo numeros");
        }
    }//GEN-LAST:event_txtEdniKeyTyped

    private void txtEsueldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEsueldoKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo numeros");
        }
    }//GEN-LAST:event_txtEsueldoKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        int op = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Salir", 0);
        if (op == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        jLabel13.setBackground(new Color(190, 202, 195));
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
        jLabel13.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jLabel13MouseExited

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        jLabel14.setBackground(new Color(190, 202, 195));
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        jLabel14.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        jLabel15.setBackground(new Color(190, 202, 195));
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        jLabel15.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jLabel15MouseExited

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        jLabel16.setBackground(new Color(190, 202, 195));
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        jLabel16.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jLabel16MouseExited

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        jLabel17.setBackground(new Color(190, 202, 195));
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        jLabel17.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jLabel17MouseExited

    private void jLabel23MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseEntered
        jLabel23.setBackground(new Color(190, 202, 195));
    }//GEN-LAST:event_jLabel23MouseEntered

    private void jLabel23MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseExited
        jLabel23.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jLabel23MouseExited

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        jTabbedPane1.setSelectedIndex(6);
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseEntered
        jLabel24.setBackground(new Color(190, 202, 195));
    }//GEN-LAST:event_jLabel24MouseEntered

    private void jLabel24MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseExited
        jLabel24.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jLabel24MouseExited

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        jLabel12.setBackground(new Color(190, 202, 195));
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        jLabel12.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_jLabel32MouseClicked

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
    }//GEN-LAST:event_btnCmostrarActionPerformed

    private void btnCregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCregistrarActionPerformed
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
    }//GEN-LAST:event_btnCregistrarActionPerformed

    private void btnCactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCactualizarActionPerformed
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
    }//GEN-LAST:event_btnCactualizarActionPerformed

    private void btnCeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCeliminarActionPerformed
        clienteJDBC.delete(new Cliente(idCliente));
    }//GEN-LAST:event_btnCeliminarActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
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
    }//GEN-LAST:event_tblClientesMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clientes = clienteJDBC.select();
        actualizarTablaCliente();
        jDialogClientes.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblClientesDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesDialogMouseClicked
        int x = tblClientesDialog.getSelectedRow();
        idCliente = clientes.get(x).getIdCliente();

        txtVcliente.setText("" + idCliente);
        jDialogClientes.dispose();
    }//GEN-LAST:event_tblClientesDialogMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        productos = productoJDBC.select();
        actualizarTablaProducto();
        jDialogproducto.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tblProductosDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosDialogMouseClicked
        int x = tblProductosDialog.getSelectedRow();
        idProducto = productos.get(x).getIdProducto();

        txtVproducto.setText("" + idProducto);
        jDialogproducto.dispose();
    }//GEN-LAST:event_tblProductosDialogMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ventas = ventaJDBC.select(empleados, clientes, productos);
        actualizarTablaVenta();
    }//GEN-LAST:event_jButton7ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCactualizar;
    private javax.swing.JButton btnCeliminar;
    private javax.swing.JButton btnCmostrar;
    private javax.swing.JButton btnCregistrar;
    private javax.swing.JButton btnEactualizar;
    private javax.swing.JButton btnEeliminar;
    private javax.swing.JButton btnEmostrar;
    private javax.swing.JButton btnEregistrar;
    private javax.swing.JButton btnPactualizar;
    private javax.swing.JButton btnPeliminar;
    private javax.swing.JButton btnPmostrar;
    public static javax.swing.JButton btnPregistrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbEtipoEmpleado;
    private javax.swing.JComboBox<String> cmbPpresentacion;
    private javax.swing.JComboBox<String> cmbVmetodoPago;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JDialog jDialogClientes;
    private javax.swing.JDialog jDialogproducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rbtCjuridico;
    private javax.swing.JRadioButton rbtCnatural;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblClientesDialog;
    private javax.swing.JTable tblCventas;
    private javax.swing.JTable tblDetalles;
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
    private javax.swing.JLabel txtUsuarioActivo;
    private javax.swing.JTextField txtVcantidad;
    private javax.swing.JTextField txtVcliente;
    private javax.swing.JTextField txtVmonto;
    private javax.swing.JTextField txtVproducto;
    // End of variables declaration//GEN-END:variables
}
