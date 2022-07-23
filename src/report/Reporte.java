package report;

import clases.DetalleVenta;
import clases.Venta;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reporte {

    public static int COMPROBANTE = 1;
    private Document doc;

    public Reporte(int tipoReporte) {
        if (tipoReporte == 1) {
            Rectangle pageSize = new Rectangle(215, 500);
            this.doc = new Document(pageSize, 10, 10, 1, 1);
        }
    }

    public void crearReporte(ArrayList<Venta> ventas, int ventaSeleccionada) {
        ArrayList<DetalleVenta> detalles = ventas.get(ventaSeleccionada).getDetalles();
        try {
            FileOutputStream ficheroPDF = new FileOutputStream(
                    "venta-" + ventas.get(ventaSeleccionada).getIdVenta() + ".pdf");
            PdfWriter.getInstance(doc, ficheroPDF);

            doc.open();

            agregarTitulo("LIANFARMA\n", 14);
            agregarTitulo("MAS SALUD A MEJOR PRECIO\n\n", 12);
            agregarTitulo("AV. ENRIQUE MEIGGS #750 - CHIMBOTE - ANCASH\n"
                    + "TELEFONO: 043-255411 - 943024714\nWWW.LIANFARMA.COM.PE\nR.U.C 2010021099\n", 10);
            agregarTitulo("---------------------------------------", 14);
            
            String karma = 
                      "     Id Venta    : " + ventas.get(ventaSeleccionada).getIdVenta() + "\n"
                    + "     Fecha       : " + ventas.get(ventaSeleccionada).getFecha() + "\n"
                    + "     Cliente     : " + ventas.get(ventaSeleccionada).getCliente().getNombre() 
                    + " " + ventas.get(ventaSeleccionada).getCliente().getApellido();
            
            agregarTexto(karma, 10);
            
            agregarTitulo("---------------------------------------", 14);

            PdfPTable tabla = new PdfPTable(5);
            tabla.setTotalWidth(50);

            float[] values = new float[5];
            values[0] = 3;
            values[1] = 10;
            values[2] = 4;
            values[3] = 4;
            values[4] = 5;
            tabla.setWidths(values);

            tabla.addCell(celda("Id"));
            tabla.addCell(celda("Descripción"));
            tabla.addCell(celda("Can"));
            tabla.addCell(celda("P.U."));
            tabla.addCell(celda("Iprt."));

            detalles.forEach(venta -> {
                tabla.addCell(celda("" + venta.getProducto().getIdProducto()));
                tabla.addCell(celda("" + venta.getProducto().getNombreProducto()));
                tabla.addCell(celda("" + venta.getCantidad()));
                tabla.addCell(celda("" + venta.getProducto().getPrecioVenta()));
                tabla.addCell(celda("" + venta.getImporte()));
            });

            doc.add(tabla);

            agregarTitulo("---------------------------------------", 14);
            
            String text = "                                 Importe Total : " + ventas.get(ventaSeleccionada)
                    .getImporteTotal();
            agregarTexto(text, 9);
            
            doc.close();
            System.out.println("Archivo creado - venta ");
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    private PdfPCell celda(String texto) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(0);
        cell.addElement(new Paragraph(texto, fuente(9)));
        return cell;
    }

    private void agregarTitulo(String texto, int tamanio) throws DocumentException {
        Paragraph titulo = new Paragraph(texto, fuenteTitulo(tamanio));
        titulo.setAlignment(FlowLayout.CENTER);
        doc.add(titulo);
    }
    
    private void agregarTexto(String texto, int tamanio) throws DocumentException{
        Paragraph txt = new Paragraph(texto, fuente(tamanio));
        doc.add(txt);
    }

    private com.itextpdf.text.Font fuenteTitulo(int tamanio) {
        return FontFactory.getFont("Arial Black", tamanio, com.itextpdf.text.Font.BOLD, BaseColor.BLACK);
    }

    private com.itextpdf.text.Font fuente(int tamanio) {
        return FontFactory.getFont("SansSerif", tamanio, com.itextpdf.text.Font.BOLD, BaseColor.BLACK);
    }
}
