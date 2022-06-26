package ventanas;

import clases.Cliente;
import clases.DetalleVenta;
import clases.Empleado;
import clases.Producto;
import clases.Venta;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Plataforma extends javax.swing.JFrame {

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

    public Plataforma() {
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        String tituloP[] = {"Código", "Categoría", "Nombre", "Precio"};
        modeloProdcutos.setColumnIdentifiers(tituloP);
        String tituloD[] = {"Nombre", "Precio", "Cantidad", "Importe"};
        modeloDetalles.setColumnIdentifiers(tituloD);
        String tituloV[] = {"Combrobante", "Tipo de Pago", "Importe Total", "Fecha"};
        modeloVentas.setColumnIdentifiers(tituloV);
        tbl_productos.setModel(modeloProdcutos);
        tblDetalles.setModel(modeloDetalles);
        tblCventas.setModel(modeloVentas);

        //Provicional
        productos.add(new Producto(1, "Prod01", 5));
        productos.add(new Producto(2, "Prod02", 10));
        productos.add(new Producto(3, "Prod03", 15));

        actualizarTablaProducto();

        ArrayList<DetalleVenta> temporal = new ArrayList<>();
        temporal.add(new DetalleVenta(productos.get(0), 5));
        temporal.add(new DetalleVenta(productos.get(1), 10));
        temporal.add(new DetalleVenta(productos.get(2), 15));
        ventas.add(new Venta("Comprobante-01",
                temporal,
                "Efectivo",
                new Date()));
        
        actualizarTablaVenta();
    }

    void actualizarTablaProducto() {
        int nRow = modeloProdcutos.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloProdcutos.removeRow(i);
        }

        for (int i = 0; i < productos.size(); i++) {
            modeloProdcutos.addRow(new Object[]{
                productos.get(i).getIdProducto(),
                cmbPcategoria.getSelectedItem(),
                productos.get(i).getNombreProducto(),
                productos.get(i).getPrecioVenta()
            });
        }
    }

    void actualizarTablaVenta() {
        int nRow = modeloVentas.getRowCount();
        for (int i = nRow - 1; i >= 0; i--) {
            modeloVentas.removeRow(i);
        }

        for (int i = 0; i < ventas.size(); i++) {
            modeloVentas.addRow(new Object[]{
                ventas.get(i).getIdVenta(),
                ventas.get(i).getMetodoPago(),
                ventas.get(i).getMonto(),
                ventas.get(i).getFecha(),});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbPcategoria = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPprecio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPnombre = new javax.swing.JTextField();
        txtPid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_registrar = new javax.swing.JButton();
        txtPcampoBusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_productos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cmbVunidad = new javax.swing.JComboBox<>();
        txtVnombreProducto = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtVcantidad = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalles = new javax.swing.JTable();
        cmbVmetodoPago = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtVidcomprobante = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtVmonto = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCventas = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 80));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(716, 755));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Categoría");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        cmbPcategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aminoacidos", "Antibioticos", "Analgesicos", " " }));
        jPanel2.add(cmbPcategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 120, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Precio:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, -1, -1));
        jPanel2.add(txtPprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 108, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REGITRAR PRODUCTO");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 30, 710, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Id del producto:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Nombre:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));
        jPanel2.add(txtPnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 108, -1));
        jPanel2.add(txtPid, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 120, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("BUSCAR PRODUCTOS");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 591, -1));

        btn_registrar.setBackground(new java.awt.Color(204, 204, 255));
        btn_registrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_registrar.setForeground(new java.awt.Color(51, 51, 51));
        btn_registrar.setText("Registrar");
        btn_registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 130, 30));
        jPanel2.add(txtPcampoBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 210, -1));

        jScrollPane1.setBorder(null);

        tbl_productos.setAutoCreateRowSorter(true);
        tbl_productos.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        tbl_productos.setForeground(new java.awt.Color(102, 102, 102));
        tbl_productos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_productos);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 602, 194));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 102, 102));
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 115, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Buscar nombre de producto:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jLabel25.setForeground(new java.awt.Color(153, 153, 255));
        jLabel25.setText("_______________________________________________________________________________________________________________________________________");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 680, 20));

        jTabbedPane1.addTab("Productos", jPanel2);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setText("Producto:");

        jLabel20.setText("Cantidad:");

        jLabel22.setText("Metodo de pago:");

        jLabel23.setText("Unidad de medida:");

        cmbVunidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tableta", "Blister", "Caja", " " }));
        cmbVunidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVunidadActionPerformed(evt);
            }
        });

        txtVnombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVnombreProductoActionPerformed(evt);
            }
        });

        jLabel24.setText("N° Comprobante");

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

        jButton4.setText("IMPRIMIR COMPROBANTE");

        jButton5.setText("PROCESAR VENTA");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtVidcomprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVidcomprobanteActionPerformed(evt);
            }
        });

        jLabel26.setText("Importe Total");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbVmetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtVnombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtVidcomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(txtVcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel23))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbVunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(txtVmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(98, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtVnombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txtVidcomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel23)
                    .addComponent(cmbVunidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cmbVmetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtVmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(74, 74, 74))
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("COMPRABANTES", jPanel5);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("REPORTES", jPanel13);

        jLabel21.setText("jLabel21");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel21)
                .addContainerGap(568, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel21)
                .addContainerGap(507, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SOPORTE TECNICO", jPanel14);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 680, 610));

        jPanel7.setBackground(new java.awt.Color(225, 238, 230));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario.png"))); // NOI18N
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 80, 90));

        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("Quimico Farmaceutico");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 110, -1));

        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("EDDY OLIVO AYALA");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 120, 20));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 190, 150));

        jPanel10.setBackground(new java.awt.Color(225, 238, 230));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(225, 238, 230));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel1MouseExited(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jLabel14.setText("PRODUCTOS");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 30));

        jPanel10.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 180, 40));

        jPanel3.setBackground(new java.awt.Color(225, 238, 230));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel3MouseMoved(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setBackground(new java.awt.Color(255, 102, 0));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bolsa.png"))); // NOI18N
        jLabel17.setText("  REGISTRAR VENTA");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, -1));

        jPanel10.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 190, 40));

        jPanel8.setBackground(new java.awt.Color(225, 238, 230));
        jPanel8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel8MouseMoved(evt);
            }
        });
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/papel.png"))); // NOI18N
        jLabel15.setText("COMPROBANTES");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 190, -1));

        jPanel6.setBackground(new java.awt.Color(225, 238, 230));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reporte.png"))); // NOI18N
        jLabel16.setText("REPORTE DE VENTA");
        jLabel16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel16MouseMoved(evt);
            }
        });
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 176, 190, 40));

        jPanel11.setBackground(new java.awt.Color(225, 238, 230));
        jPanel11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel11MouseMoved(evt);
            }
        });
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel11MouseExited(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sorporte (1).png"))); // NOI18N
        jLabel12.setText("SOPORTE TECNICO");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 190, -1));

        jPanel12.setBackground(new java.awt.Color(225, 238, 230));
        jPanel12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel12MouseMoved(evt);
            }
        });
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel12MouseExited(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cs.png"))); // NOI18N
        jLabel13.setText("CERRAR SESIÓN");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 190, -1));

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 190, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
        jPanel1.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_jPanel1MouseMoved

    private void jPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseExited
        jPanel1.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jPanel1MouseExited

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseMoved
        jPanel3.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_jPanel3MouseMoved

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
        jPanel3.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jPanel3MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String nombre = txtPcampoBusqueda.getText();
        boolean flag = false;
        for (int i = 0; i < productos.size(); i++) {
            if (nombre.equalsIgnoreCase(productos.get(i).getNombreProducto())) {
                flag = true;
                break;
            }
        }
        if (flag) {
            JOptionPane.showMessageDialog(this, "El producto si se encuentra");
        } else {
            JOptionPane.showMessageDialog(this, "El producto no se encuentra");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        int idProducto = Integer.parseInt(txtPid.getText());
        String nombre = txtPnombre.getText();
        float precio = Float.parseFloat(txtPprecio.getText());

        productos.add(new Producto(idProducto, nombre, precio));

        actualizarTablaProducto();

    }//GEN-LAST:event_btn_registrarActionPerformed

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel8MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseMoved
        jPanel8.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_jPanel8MouseMoved

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        jPanel8.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jPanel8MouseExited

    private void cmbVunidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVunidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVunidadActionPerformed

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel16MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseMoved
        jPanel6.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_jLabel16MouseMoved

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        jPanel6.setBackground(new Color(225, 238, 230));       // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseExited

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        jTabbedPane1.setSelectedIndex(4);

    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel11MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseMoved
        jPanel11.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_jPanel11MouseMoved

    private void jPanel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseExited
        jPanel11.setBackground(new Color(225, 238, 230));
    }//GEN-LAST:event_jPanel11MouseExited

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        JOptionPane.showConfirmDialog(null, "Realmente desea cerrar sesión?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jPanel12MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseMoved
        jPanel12.setBackground(new Color(255, 130, 130));
    }//GEN-LAST:event_jPanel12MouseMoved

    private void jPanel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseExited
        jPanel12.setBackground(new Color(225, 238, 230));     // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12MouseExited

    private void txtVidcomprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVidcomprobanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVidcomprobanteActionPerformed

    private void txtVnombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVnombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVnombreProductoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nombre = txtVnombreProducto.getText();
        int cantidad = Integer.parseInt(txtVcantidad.getText());
        int posicion = 0;
        boolean flag = false;
        float suma = 0;

        for (int i = 0; i < productos.size(); i++) {
            if (nombre.equalsIgnoreCase(productos.get(i).getNombreProducto())) {
                posicion = i;
                flag = true;
                break;
            }
        }
        if (flag) {
            detalles.add(new DetalleVenta(productos.get(posicion), cantidad));

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

        } else {
            JOptionPane.showMessageDialog(this, "EL producto no se encuentra");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String idComprobante = txtVidcomprobante.getText();
        String metodoPago = (String) cmbVmetodoPago.getSelectedItem();
        ventas.add(new Venta(idComprobante, detalles, metodoPago, new Date()));
        detalles = new ArrayList<>();
        JOptionPane.showMessageDialog(this, "Venta procesada");

        actualizarTablaVenta();

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
                new Plataforma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_registrar;
    private javax.swing.JComboBox<String> cmbPcategoria;
    private javax.swing.JComboBox<String> cmbVmetodoPago;
    private javax.swing.JComboBox<String> cmbVunidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblCventas;
    private javax.swing.JTable tblDetalles;
    private javax.swing.JTable tbl_productos;
    public static javax.swing.JTextField txtPcampoBusqueda;
    public static javax.swing.JTextField txtPid;
    private javax.swing.JTextField txtPnombre;
    private javax.swing.JTextField txtPprecio;
    private javax.swing.JTextField txtVcantidad;
    private javax.swing.JTextField txtVidcomprobante;
    private javax.swing.JTextField txtVmonto;
    private javax.swing.JTextField txtVnombreProducto;
    // End of variables declaration//GEN-END:variables
}
