package ventanas;

import report.Reporte;
import clases.*;
import conexion.*;
import report.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Plataforma extends javax.swing.JFrame {

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

    //id usuario activo
    int idEmpleadoActual;

    Color colorEntrar = new Color(34, 117, 26);
    Color colorSalir = new Color(45, 156, 35);

    Pass password = new Pass();

    public Plataforma(int id_empleado) {
        //Trea las tablas de la base de datos
        empleados = empleadoJDBC.select();
        clientes = clienteJDBC.select();
        productos = productoJDBC.select();

        initComponents();

        iniciarTablas();

        btnPactualizar.setEnabled(false);
        btnPeliminar.setEnabled(false);

        btnEactualizar.setEnabled(false);
        btnEeliminar.setEnabled(false);

        btnCactualizar.setEnabled(false);
        btnCeliminar.setEnabled(false);

        btnUactualizar.setEnabled(false);
        btnUeliminar.setEnabled(false);

        rbtCnatural.setSelected(true);

        actualizarEmpleadoActual(id_empleado);

        formatoCabeceraTabla(tblProductos);
        formatoCabeceraTabla(tblEmpleados);
        formatoCabeceraTabla(tblClientes);
        formatoCabeceraTabla(tblDetalles);
        formatoCabeceraTabla(tblVentas);
        formatoCabeceraTabla(tblClientesDialog);
        formatoCabeceraTabla(tblProductosDialog);
        formatoCabeceraTabla(tblDetallesDialog);
        formatoCabeceraTabla(tblVentasReporte);
        formatoCabeceraTabla(tblUsuarios);
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
        tblProductos.setModel(modeloProdcutos);
        tblEmpleados.setModel(modeloEmpleados);
        tblClientes.setModel(modeloClientes);
        tblDetalles.setModel(modeloDetalles);
        tblVentas.setModel(modeloVentas);
        tblUsuarios.setModel(modeloUsuarios);
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialogproducto = new javax.swing.JDialog();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblProductosDialog = new javax.swing.JTable();
        jDialogDetalles = new javax.swing.JDialog();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblDetallesDialog = new javax.swing.JTable();
        jDialogEmpleado = new javax.swing.JDialog();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblEmpleadoDialog = new javax.swing.JTable();
        btnSalir = new javax.swing.JLabel();
        panelInfoUsuario = new javax.swing.JPanel();
        foto = new javax.swing.JLabel();
        txtRolActivo = new javax.swing.JLabel();
        txtUsuarioActivo = new javax.swing.JLabel();
        cabecera = new javax.swing.JLabel();
        paneles = new javax.swing.JTabbedPane();
        panelProductos = new javax.swing.JPanel();
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
        panelReportes = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblVentasReporte = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnVactualizarReporte = new javax.swing.JButton();
        btnVactualizarReporte1 = new javax.swing.JButton();
        panelEmpleados = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
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
        btnCactualizar = new javax.swing.JButton();
        btnCeliminar = new javax.swing.JButton();
        btnCmostrar = new javax.swing.JButton();
        panelUsuarios = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        btnUmostrar = new javax.swing.JButton();
        btnUregistrar = new javax.swing.JButton();
        btnUactualizar = new javax.swing.JButton();
        btnUeliminar = new javax.swing.JButton();
        jSeparator21 = new javax.swing.JSeparator();
        txtUuser = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        jLabel43 = new javax.swing.JLabel();
        txtUempleado = new javax.swing.JTextField();
        btnSelecEmpleado = new javax.swing.JButton();
        txtUpass = new javax.swing.JPasswordField();
        panelNav = new javax.swing.JPanel();
        sec01 = new javax.swing.JLabel();
        sec02 = new javax.swing.JLabel();
        sec03 = new javax.swing.JLabel();
        sec04 = new javax.swing.JLabel();
        sec05 = new javax.swing.JLabel();
        sec06 = new javax.swing.JLabel();
        sec07 = new javax.swing.JLabel();
        sec08 = new javax.swing.JLabel();

        jDialogClientes.setTitle("Seleccionar un cliente");
        jDialogClientes.setLocationByPlatform(true);
        jDialogClientes.setModal(true);
        jDialogClientes.setResizable(false);
        jDialogClientes.setSize(new java.awt.Dimension(700, 350));

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

        javax.swing.GroupLayout jDialogClientesLayout = new javax.swing.GroupLayout(jDialogClientes.getContentPane());
        jDialogClientes.getContentPane().setLayout(jDialogClientesLayout);
        jDialogClientesLayout.setHorizontalGroup(
            jDialogClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogClientesLayout.setVerticalGroup(
            jDialogClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDialogproducto.setTitle("Seleccionar un producto");
        jDialogproducto.setLocationByPlatform(true);
        jDialogproducto.setModal(true);
        jDialogproducto.setResizable(false);
        jDialogproducto.setSize(new java.awt.Dimension(700, 350));

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

        javax.swing.GroupLayout jDialogproductoLayout = new javax.swing.GroupLayout(jDialogproducto.getContentPane());
        jDialogproducto.getContentPane().setLayout(jDialogproductoLayout);
        jDialogproductoLayout.setHorizontalGroup(
            jDialogproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogproductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogproductoLayout.setVerticalGroup(
            jDialogproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogproductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        javax.swing.GroupLayout jDialogEmpleadoLayout = new javax.swing.GroupLayout(jDialogEmpleado.getContentPane());
        jDialogEmpleado.getContentPane().setLayout(jDialogEmpleadoLayout);
        jDialogEmpleadoLayout.setHorizontalGroup(
            jDialogEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogEmpleadoLayout.setVerticalGroup(
            jDialogEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
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

        panelProductos.setBackground(new java.awt.Color(255, 255, 255));
        panelProductos.setPreferredSize(new java.awt.Dimension(716, 755));
        panelProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        panelProductos.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 260, 10));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        panelProductos.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 200, 10));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        panelProductos.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 200, 10));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        panelProductos.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 210, 10));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        panelProductos.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 210, 10));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        panelProductos.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, 210, 10));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Presentación:");
        panelProductos.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        cmbPpresentacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbPpresentacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Comprimido", "Pildora" }));
        panelProductos.add(cmbPpresentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 200, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Precio:");
        panelProductos.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        txtPconcentracion.setBorder(null);
        panelProductos.add(txtPconcentracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 210, 25));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Stock:");
        panelProductos.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Fecha vencimiento:");
        panelProductos.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, -1));

        txtPfechaVenc.setBorder(null);
        panelProductos.add(txtPfechaVenc, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 210, 25));

        txtPnombre.setBorder(null);
        panelProductos.add(txtPnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 200, 25));

        btnPregistrar.setBackground(new java.awt.Color(34, 73, 24));
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
        panelProductos.add(btnPregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 140, 40));

        txtPbuscar.setBorder(null);
        panelProductos.add(txtPbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 260, 25));

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

        panelProductos.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 820, 270));

        jButton2.setBackground(new java.awt.Color(34, 73, 24));
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
        panelProductos.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 70, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Buscar nombre de producto:");
        panelProductos.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Nombre:");
        panelProductos.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        txtPstock.setBorder(null);
        txtPstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPstockKeyTyped(evt);
            }
        });
        panelProductos.add(txtPstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 200, 25));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Concetración:");
        panelProductos.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, -1));

        txtPprecio.setBorder(null);
        txtPprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPprecioKeyTyped(evt);
            }
        });
        panelProductos.add(txtPprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 210, 25));

        btnPmostrar.setBackground(new java.awt.Color(34, 73, 24));
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
        panelProductos.add(btnPmostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, 140, 40));

        btnPactualizar.setBackground(new java.awt.Color(34, 73, 24));
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
        panelProductos.add(btnPactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 140, 40));

        btnPeliminar.setBackground(new java.awt.Color(34, 73, 24));
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
        panelProductos.add(btnPeliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 140, 40));

        paneles.addTab("Productos", panelProductos);

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

        btnProcesarVenta.setBackground(new java.awt.Color(62, 131, 42));
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

        panelVentas.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 820, 370));

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
        panelVentas.add(btnVactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 440, 180, 40));

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

        jButton1.setText("Generar reporte de Quincena");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelReportes.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, -1, -1));

        jButton3.setText("Generar reporte de Dia");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelReportes.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, -1, -1));

        jButton4.setText("Generar reporte de Semana");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panelReportes.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, -1, -1));

        jButton5.setText("Generar reporte de Mes");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        panelReportes.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 470, -1, -1));

        btnVactualizarReporte.setBackground(new java.awt.Color(34, 73, 24));
        btnVactualizarReporte.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVactualizarReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnVactualizarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-refresh-2-32.png"))); // NOI18N
        btnVactualizarReporte.setText("Actualizar");
        btnVactualizarReporte.setToolTipText("");
        btnVactualizarReporte.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnVactualizarReporte.setBorderPainted(false);
        btnVactualizarReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVactualizarReporte.setFocusPainted(false);
        btnVactualizarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVactualizarReporteActionPerformed(evt);
            }
        });
        panelReportes.add(btnVactualizarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 180, 40));

        btnVactualizarReporte1.setBackground(new java.awt.Color(34, 73, 24));
        btnVactualizarReporte1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVactualizarReporte1.setForeground(new java.awt.Color(255, 255, 255));
        btnVactualizarReporte1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-refresh-2-32.png"))); // NOI18N
        btnVactualizarReporte1.setText("Generar reporte");
        btnVactualizarReporte1.setToolTipText("");
        btnVactualizarReporte1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnVactualizarReporte1.setBorderPainted(false);
        btnVactualizarReporte1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVactualizarReporte1.setFocusPainted(false);
        btnVactualizarReporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVactualizarReporte1ActionPerformed(evt);
            }
        });
        panelReportes.add(btnVactualizarReporte1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 520, 180, 40));

        paneles.addTab("REPORTES", panelReportes);

        panelEmpleados.setBackground(new java.awt.Color(255, 255, 255));
        panelEmpleados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Nombres");
        panelEmpleados.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Apellidos");
        panelEmpleados.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("DNI");
        panelEmpleados.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Tipo de empleado");
        panelEmpleados.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, -1));

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        panelEmpleados.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 200, 10));

        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));
        panelEmpleados.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 200, 10));

        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));
        panelEmpleados.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 200, 10));

        jSeparator14.setForeground(new java.awt.Color(0, 0, 0));
        panelEmpleados.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 200, 10));

        jSeparator15.setForeground(new java.awt.Color(0, 0, 0));
        panelEmpleados.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 200, 10));

        txtEnombres.setBorder(null);
        txtEnombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEnombresKeyTyped(evt);
            }
        });
        panelEmpleados.add(txtEnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 200, 25));

        txtEapellidos.setBorder(null);
        txtEapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEapellidosKeyTyped(evt);
            }
        });
        panelEmpleados.add(txtEapellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 200, 25));

        txtEdni.setBorder(null);
        txtEdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdniKeyTyped(evt);
            }
        });
        panelEmpleados.add(txtEdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 200, 25));

        cmbEtipoEmpleado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbEtipoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Usuario" }));
        panelEmpleados.add(cmbEtipoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 200, 30));

        jScrollPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 131, 42), 1, true));

        tblEmpleados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        tblEmpleados.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
        tblEmpleados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblEmpleados.setFocusable(false);
        tblEmpleados.setGridColor(new java.awt.Color(51, 51, 51));
        tblEmpleados.setRowHeight(25);
        tblEmpleados.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblEmpleados.getTableHeader().setResizingAllowed(false);
        tblEmpleados.getTableHeader().setReorderingAllowed(false);
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblEmpleados);

        panelEmpleados.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 327, 820, 260));

        btnEregistrar.setBackground(new java.awt.Color(34, 73, 24));
        btnEregistrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEregistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEregistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-text-plus-lined-32.png"))); // NOI18N
        btnEregistrar.setText("Registrar");
        btnEregistrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnEregistrar.setBorderPainted(false);
        btnEregistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEregistrar.setFocusPainted(false);
        btnEregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEregistrarActionPerformed(evt);
            }
        });
        panelEmpleados.add(btnEregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 140, 40));

        btnEactualizar.setBackground(new java.awt.Color(34, 73, 24));
        btnEactualizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEactualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnEactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-reload-thin-32.png"))); // NOI18N
        btnEactualizar.setText("Actualizar");
        btnEactualizar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnEactualizar.setBorderPainted(false);
        btnEactualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEactualizar.setFocusPainted(false);
        btnEactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEactualizarActionPerformed(evt);
            }
        });
        panelEmpleados.add(btnEactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 140, 40));

        btnEeliminar.setBackground(new java.awt.Color(34, 73, 24));
        btnEeliminar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEeliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEeliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-text-minus-lined-32.png"))); // NOI18N
        btnEeliminar.setText("Eliminar");
        btnEeliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnEeliminar.setBorderPainted(false);
        btnEeliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEeliminar.setFocusPainted(false);
        btnEeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEeliminarActionPerformed(evt);
            }
        });
        panelEmpleados.add(btnEeliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 140, 40));

        btnEmostrar.setBackground(new java.awt.Color(34, 73, 24));
        btnEmostrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEmostrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEmostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-list-lined-32.png"))); // NOI18N
        btnEmostrar.setText("Mostrar");
        btnEmostrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnEmostrar.setBorderPainted(false);
        btnEmostrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmostrar.setFocusPainted(false);
        btnEmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmostrarActionPerformed(evt);
            }
        });
        panelEmpleados.add(btnEmostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, 140, 40));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Sueldo");
        panelEmpleados.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, -1, -1));

        txtEsueldo.setBorder(null);
        txtEsueldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEsueldoKeyTyped(evt);
            }
        });
        panelEmpleados.add(txtEsueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 200, 25));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("Cargo");
        panelEmpleados.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, -1, -1));

        txtEcargo.setBorder(null);
        panelEmpleados.add(txtEcargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 200, 25));

        paneles.addTab("EMPLEADOS", panelEmpleados);

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

        btnCactualizar.setBackground(new java.awt.Color(62, 131, 42));
        btnCactualizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCactualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnCactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-reload-thin-32.png"))); // NOI18N
        btnCactualizar.setText("Actualizar");
        btnCactualizar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnCactualizar.setBorderPainted(false);
        btnCactualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCactualizar.setFocusPainted(false);
        btnCactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCactualizarActionPerformed(evt);
            }
        });
        panelClientes.add(btnCactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 140, 40));

        btnCeliminar.setBackground(new java.awt.Color(62, 131, 42));
        btnCeliminar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCeliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnCeliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-text-minus-lined-32.png"))); // NOI18N
        btnCeliminar.setText("Eliminar");
        btnCeliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnCeliminar.setBorderPainted(false);
        btnCeliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCeliminar.setFocusPainted(false);
        btnCeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCeliminarActionPerformed(evt);
            }
        });
        panelClientes.add(btnCeliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 140, 40));

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

        panelUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        panelUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 131, 42), 1, true));

        tblUsuarios.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        tblUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblUsuarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblUsuarios.setFocusable(false);
        tblUsuarios.setGridColor(new java.awt.Color(51, 51, 51));
        tblUsuarios.setRowHeight(25);
        tblUsuarios.setSelectionBackground(new java.awt.Color(62, 131, 42));
        tblUsuarios.getTableHeader().setResizingAllowed(false);
        tblUsuarios.getTableHeader().setReorderingAllowed(false);
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblUsuarios);

        panelUsuarios.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 830, 280));

        btnUmostrar.setBackground(new java.awt.Color(62, 131, 42));
        btnUmostrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUmostrar.setForeground(new java.awt.Color(255, 255, 255));
        btnUmostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-list-lined-32.png"))); // NOI18N
        btnUmostrar.setText("Mostrar");
        btnUmostrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnUmostrar.setBorderPainted(false);
        btnUmostrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUmostrar.setFocusPainted(false);
        btnUmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUmostrarActionPerformed(evt);
            }
        });
        panelUsuarios.add(btnUmostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, 140, 40));

        btnUregistrar.setBackground(new java.awt.Color(62, 131, 42));
        btnUregistrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUregistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnUregistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-text-plus-lined-32.png"))); // NOI18N
        btnUregistrar.setText("Registrar");
        btnUregistrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnUregistrar.setBorderPainted(false);
        btnUregistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUregistrar.setFocusPainted(false);
        btnUregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUregistrarActionPerformed(evt);
            }
        });
        panelUsuarios.add(btnUregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 140, 40));

        btnUactualizar.setBackground(new java.awt.Color(62, 131, 42));
        btnUactualizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUactualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnUactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-reload-thin-32.png"))); // NOI18N
        btnUactualizar.setText("Actualizar");
        btnUactualizar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnUactualizar.setBorderPainted(false);
        btnUactualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUactualizar.setFocusPainted(false);
        btnUactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUactualizarActionPerformed(evt);
            }
        });
        panelUsuarios.add(btnUactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 140, 40));

        btnUeliminar.setBackground(new java.awt.Color(62, 131, 42));
        btnUeliminar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUeliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnUeliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-text-minus-lined-32.png"))); // NOI18N
        btnUeliminar.setText("Eliminar");
        btnUeliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnUeliminar.setBorderPainted(false);
        btnUeliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUeliminar.setFocusPainted(false);
        btnUeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUeliminarActionPerformed(evt);
            }
        });
        panelUsuarios.add(btnUeliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 140, 40));

        jSeparator21.setForeground(new java.awt.Color(0, 0, 0));
        panelUsuarios.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 200, 10));

        txtUuser.setBorder(null);
        panelUsuarios.add(txtUuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 200, 25));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setText("Nombre de usuario");
        panelUsuarios.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jSeparator22.setForeground(new java.awt.Color(0, 0, 0));
        panelUsuarios.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 200, 10));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setText("Contraseña");
        panelUsuarios.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jSeparator24.setForeground(new java.awt.Color(0, 0, 0));
        panelUsuarios.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 200, 10));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setText("Empleado");
        panelUsuarios.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, -1));

        txtUempleado.setEditable(false);
        txtUempleado.setBorder(null);
        panelUsuarios.add(txtUempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 200, 25));

        btnSelecEmpleado.setBackground(new java.awt.Color(34, 73, 24));
        btnSelecEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSelecEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        btnSelecEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-search-thin-32.png"))); // NOI18N
        btnSelecEmpleado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnSelecEmpleado.setBorderPainted(false);
        btnSelecEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSelecEmpleado.setFocusPainted(false);
        btnSelecEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecEmpleadoActionPerformed(evt);
            }
        });
        panelUsuarios.add(btnSelecEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, 70, 40));

        txtUpass.setBorder(null);
        panelUsuarios.add(txtUpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 200, 25));

        paneles.addTab("USUARIOS", panelUsuarios);

        getContentPane().add(paneles, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 650));

        panelNav.setBackground(new java.awt.Color(45, 156, 35));
        panelNav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sec01.setBackground(new java.awt.Color(45, 156, 35));
        sec01.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec01.setForeground(new java.awt.Color(255, 255, 255));
        sec01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-medical-13-32.png"))); // NOI18N
        sec01.setText("  Productos     ");
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
        panelNav.add(sec01, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 50));

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
        panelNav.add(sec02, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 50));

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
        panelNav.add(sec03, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 190, 50));

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
        panelNav.add(sec04, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 190, 50));

        sec05.setBackground(new java.awt.Color(45, 156, 35));
        sec05.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec05.setForeground(new java.awt.Color(255, 255, 255));
        sec05.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec05.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-user-17-32.png"))); // NOI18N
        sec05.setText("  Empleados   ");
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
        panelNav.add(sec05, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 190, 50));

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
        panelNav.add(sec06, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 190, 50));

        sec07.setBackground(new java.awt.Color(45, 156, 35));
        sec07.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sec07.setForeground(new java.awt.Color(255, 255, 255));
        sec07.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sec07.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmonstr-user-circle-thin-32.png"))); // NOI18N
        sec07.setText("  Usuarios        ");
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
        panelNav.add(sec07, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 190, 50));

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
        panelNav.add(sec08, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 190, 50));

        getContentPane().add(panelNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 190, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String buscar = txtPbuscar.getText();

        productos = productoJDBC.search(buscar);
        actualizarTablaProducto();
    }//GEN-LAST:event_jButton2ActionPerformed

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
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }

    }//GEN-LAST:event_btnPregistrarActionPerformed

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

    private void btnPmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPmostrarActionPerformed
        productos = productoJDBC.select();
        actualizarTablaProducto();

        btnPregistrar.setEnabled(true);
        btnPactualizar.setEnabled(false);
        btnPeliminar.setEnabled(false);

    }//GEN-LAST:event_btnPmostrarActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        /*Con este evento cargamos las cajas de texto con los valores de la fila
        seleccionada para eliminar o actualiziar despues*/
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
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }

    }//GEN-LAST:event_btnPactualizarActionPerformed

    private void btnPeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeliminarActionPerformed
        /*la variable idProducto se actualiza al hacer click en alguna fila 
        de la tabla productos*/
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
        if (txtEdni.getText().length() == 8) {
            try {
                String nombres = txtEnombres.getText();
                String apellidos = txtEapellidos.getText();
                String dni = txtEdni.getText();
                String tipo = (String) cmbEtipoEmpleado.getSelectedItem();
                float sueldo = Float.parseFloat(txtEsueldo.getText());
                String cargo = txtEcargo.getText();

                empleadoJDBC.insert(new Empleado(nombres, apellidos, dni, tipo, sueldo, cargo));

                limpiarRegistrosEmpleados();

                if (empleadoJDBC.getError()) {
                    JOptionPane.showMessageDialog(this, "El DNI ya se encuentra en uso");
                } else {
                    JOptionPane.showMessageDialog(this, "Empleado Regisrado");
                }

            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
            }
        } else {
            txtEdni.setText("");
            JOptionPane.showMessageDialog(this, "DNI no válido");
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
        if (txtEdni.getText().length() == 8) {
            try {
                String nombre = txtEnombres.getText();
                String apellidos = txtEapellidos.getText();
                String dni = txtEdni.getText();
                String tipo = (String) cmbEtipoEmpleado.getSelectedItem();
                float sueldo = Float.parseFloat(txtEsueldo.getText());
                String cargo = txtEcargo.getText();

                empleadoJDBC.update(new Empleado(idEmpleado, nombre, apellidos, dni, tipo, sueldo, cargo));

                limpiarRegistrosEmpleados();

                if (empleadoJDBC.getError()) {
                    JOptionPane.showMessageDialog(this, "El DNI ya se encuentra en uso");
                } else {
                    JOptionPane.showMessageDialog(this, "Empleado Actualizado");
                }

            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
            }
        } else {
            txtEdni.setText("");
            JOptionPane.showMessageDialog(this, "DNI no válido");
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
        limitarEntradaDatos(txtEdni, 8, evt);
    }//GEN-LAST:event_txtEdniKeyTyped

    private void txtEsueldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEsueldoKeyTyped
        validarSoloNumeros(evt);
    }//GEN-LAST:event_txtEsueldoKeyTyped

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
        btnCactualizar.setEnabled(false);
        btnCeliminar.setEnabled(false);
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

    private void btnCactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCactualizarActionPerformed
        if (txtCdni.getText().length() == 8) {
            try {
                String nombres = txtCnombres.getText();
                String apellidos = txtCapellidos.getText();
                String dni = txtCdni.getText();

                if (rbtCnatural.isSelected()) {
                    clienteJDBC.update(new Cliente(idCliente, "Natural", nombres, apellidos, dni, "", ""));
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
                        clienteJDBC.update(new Cliente(idCliente, "Jurídica", nombres, apellidos, dni, ruc, razon));
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

    }//GEN-LAST:event_btnCactualizarActionPerformed

    private void btnCeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCeliminarActionPerformed
        clienteJDBC.delete(new Cliente(idCliente));
        limpiarRegistrosClientes();
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
        sec01.setBackground(colorEntrar);
    }//GEN-LAST:event_sec01MouseEntered

    private void sec01MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec01MouseExited
        sec01.setBackground(colorSalir);
    }//GEN-LAST:event_sec01MouseExited

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

    private void sec04MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec04MouseEntered
        sec04.setBackground(colorEntrar);
    }//GEN-LAST:event_sec04MouseEntered

    private void sec04MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec04MouseExited
        sec04.setBackground(colorSalir);
    }//GEN-LAST:event_sec04MouseExited

    private void sec05MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec05MouseEntered
        sec05.setBackground(colorEntrar);
    }//GEN-LAST:event_sec05MouseEntered

    private void sec05MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec05MouseExited
        sec05.setBackground(colorSalir);
    }//GEN-LAST:event_sec05MouseExited

    private void sec06MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec06MouseEntered
        sec06.setBackground(colorEntrar);
    }//GEN-LAST:event_sec06MouseEntered

    private void sec06MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec06MouseExited
        sec06.setBackground(colorSalir);
    }//GEN-LAST:event_sec06MouseExited

    private void sec07MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec07MouseEntered
        sec07.setBackground(colorEntrar);
    }//GEN-LAST:event_sec07MouseEntered

    private void sec07MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec07MouseExited
        sec07.setBackground(colorSalir);
    }//GEN-LAST:event_sec07MouseExited

    private void sec08MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec08MouseEntered
        sec08.setBackground(colorEntrar);
    }//GEN-LAST:event_sec08MouseEntered

    private void sec08MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec08MouseExited
        sec08.setBackground(colorSalir);
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

    private void tblVentasReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasReporteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblVentasReporteMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String fechaHoy = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ventas = ventaJDBC.selectVentaDia(empleados, clientes, productos, fechaHoy);

        actualizarTablaVenta();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ventas = ventaJDBC.selectVentaPorDias(empleados, clientes, productos, 7);
        actualizarTablaVenta();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnVactualizarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVactualizarReporteActionPerformed
        ventas = ventaJDBC.select(empleados, clientes, productos);
        actualizarTablaVenta();
    }//GEN-LAST:event_btnVactualizarReporteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ventas = ventaJDBC.selectVentaPorDias(empleados, clientes, productos, 15);
        actualizarTablaVenta();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ventas = ventaJDBC.selectVentaUltimoMes(empleados, clientes, productos);
        actualizarTablaVenta();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void sec01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec01MouseClicked
        paneles.setSelectedIndex(0);
    }//GEN-LAST:event_sec01MouseClicked

    private void sec02MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec02MouseClicked
        paneles.setSelectedIndex(1);
        detalles = new ArrayList<>();
    }//GEN-LAST:event_sec02MouseClicked

    private void sec03MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec03MouseClicked
        paneles.setSelectedIndex(2);
    }//GEN-LAST:event_sec03MouseClicked

    private void sec04MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec04MouseClicked
        paneles.setSelectedIndex(3);
    }//GEN-LAST:event_sec04MouseClicked

    private void sec05MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec05MouseClicked
        paneles.setSelectedIndex(4);
    }//GEN-LAST:event_sec05MouseClicked

    private void sec06MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec06MouseClicked
        paneles.setSelectedIndex(5);
    }//GEN-LAST:event_sec06MouseClicked

    private void sec07MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec07MouseClicked
        paneles.setSelectedIndex(6);
    }//GEN-LAST:event_sec07MouseClicked

    private void sec08MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sec08MouseClicked
        int op = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Salir", 0);
        if (op == 0) {
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_sec08MouseClicked

    private void btnVactualizarReporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVactualizarReporte1ActionPerformed
        // Generar Reporte pruebas
        Reporte reporte = new Reporte(Reporte.COMPROBANTE);
        reporte.crearReporte(ventas, 2);
    }//GEN-LAST:event_btnVactualizarReporte1ActionPerformed

    private void txtCnombresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCnombresFocusGained
        txtCnombres.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_txtCnombresFocusGained

    private void txtCnombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCnombresFocusLost
        txtCnombres.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtCnombresFocusLost

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        try {
            int x = tblUsuarios.getSelectedRow();

            idUsuario = usuarios.get(x).getIdUsuario();
            txtUuser.setText(usuarios.get(x).getUser());
            txtUpass.setText(password.desencriptar(usuarios.get(x).getPassword()));
            txtUempleado.setText("" + usuarios.get(x).getEmpleado().getidEmpleado());
            btnUregistrar.setEnabled(false);
            btnUactualizar.setEnabled(true);
            btnUeliminar.setEnabled(true);
            btnSelecEmpleado.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dar solo click izquierdo.");
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnUmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUmostrarActionPerformed
        usuarios = usuarioJDBC.select(empleados);
        actualizarTablaUsuario();

        btnUregistrar.setEnabled(true);
        btnSelecEmpleado.setEnabled(true);
        btnUactualizar.setEnabled(false);
        btnUeliminar.setEnabled(false);
    }//GEN-LAST:event_btnUmostrarActionPerformed

    private void btnUregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUregistrarActionPerformed
        try {
            int idEmple = Integer.parseInt(txtUempleado.getText());
            String user = txtUuser.getText();
            String pass = password.encriptar(String.valueOf(txtUpass.getPassword()));

            int posEmp = 0;
            for (int i = 0; i < empleados.size(); i++) {
                if (empleados.get(i).getidEmpleado() == idEmple) {
                    posEmp = i;
                    break;
                }
            }

            usuarioJDBC.insert(new Usuario(empleados.get(posEmp), user, pass));

            limpiarRegistrosUsuarios();

            if (usuarioJDBC.getError()) {
                JOptionPane.showMessageDialog(this, "El Usuario y/o Empleado ya se encuentra en uso");
            } else {
                JOptionPane.showMessageDialog(this, "Usuario Registrado");
            }

        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }
    }//GEN-LAST:event_btnUregistrarActionPerformed

    private void btnUactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUactualizarActionPerformed
        try {
            String user = txtUuser.getText();
            String pass = password.encriptar(String.valueOf(txtUpass.getPassword()));

            usuarioJDBC.update(new Usuario(idUsuario, user, pass));

            limpiarRegistrosUsuarios();

            if (usuarioJDBC.getError()) {
                JOptionPane.showMessageDialog(this, "El Usuario y/o Empleado ya se encuentra en uso");
            } else {
                JOptionPane.showMessageDialog(this, "Usuario Actualizado");
            }

        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Llenar todos los campos y/o error en la entrada de datos");
        }
    }//GEN-LAST:event_btnUactualizarActionPerformed

    private void btnUeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUeliminarActionPerformed
        usuarioJDBC.delete(new Usuario(idUsuario));
        limpiarRegistrosUsuarios();
        JOptionPane.showMessageDialog(this, "Usuario Eliminado");
    }//GEN-LAST:event_btnUeliminarActionPerformed

    private void btnSelecEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecEmpleadoActionPerformed
        empleados = empleadoJDBC.select();
        actualizarTablaEmpleado();
        jDialogEmpleado.setVisible(true);
    }//GEN-LAST:event_btnSelecEmpleadoActionPerformed

    private void tblEmpleadoDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadoDialogMouseClicked
        int x = tblEmpleadoDialog.getSelectedRow();
        idEmpleado = empleados.get(x).getidEmpleado();

        txtUempleado.setText("" + idEmpleado);
        jDialogEmpleado.dispose();
    }//GEN-LAST:event_tblEmpleadoDialogMouseClicked

    private void txtVcantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVcantidadKeyTyped
        validarSoloNumeros(evt);
    }//GEN-LAST:event_txtVcantidadKeyTyped

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
            java.util.logging.Logger.getLogger(Plataforma.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Plataforma.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Plataforma.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Plataforma.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

    private void limpiarRegistrosUsuarios() {
        txtUuser.setText("");
        txtUpass.setText("");
        txtUempleado.setText("");
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
    private javax.swing.JButton btnSelecEmpleado;
    private javax.swing.JButton btnSelectProducto;
    private javax.swing.JButton btnUactualizar;
    private javax.swing.JButton btnUeliminar;
    private javax.swing.JButton btnUmostrar;
    private javax.swing.JButton btnUregistrar;
    private javax.swing.JButton btnVactualizar;
    private javax.swing.JButton btnVactualizarReporte;
    private javax.swing.JButton btnVactualizarReporte1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cabecera;
    private javax.swing.JComboBox<String> cmbEtipoEmpleado;
    private javax.swing.JComboBox<String> cmbPpresentacion;
    private javax.swing.JComboBox<String> cmbVmetodoPago;
    private javax.swing.JLabel foto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JDialog jDialogClientes;
    private javax.swing.JDialog jDialogDetalles;
    private javax.swing.JDialog jDialogEmpleado;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel panelCarrito;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelEmpleados;
    private javax.swing.JPanel panelInfoUsuario;
    private javax.swing.JPanel panelNav;
    private javax.swing.JPanel panelProductos;
    private javax.swing.JPanel panelReportes;
    private javax.swing.JPanel panelUsuarios;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JTabbedPane paneles;
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
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblClientesDialog;
    private javax.swing.JTable tblDetalles;
    private javax.swing.JTable tblDetallesDialog;
    private javax.swing.JTable tblEmpleadoDialog;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProductosDialog;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTable tblVentasReporte;
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
    private javax.swing.JTextField txtUempleado;
    private javax.swing.JPasswordField txtUpass;
    private javax.swing.JLabel txtUsuarioActivo;
    private javax.swing.JTextField txtUuser;
    private javax.swing.JTextField txtVcantidad;
    private javax.swing.JTextField txtVcliente;
    private javax.swing.JTextField txtVmonto;
    private javax.swing.JTextField txtVproducto;
    // End of variables declaration//GEN-END:variables
}
