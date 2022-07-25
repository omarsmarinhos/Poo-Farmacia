package report;

import clases.Venta;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author anton
 */
public class GenerarReporteExcel {

private String fecha;

    public static String suma(int size) {

        String suma = null;
        String sumai = null;
        for (int a = 2; a < size + 2; a++) {

            if (a > 2) {
                sumai += "+D" + a;
            } else {
                suma = "D" + a + "+" + "0";
                sumai = suma;
            }

        }
        return sumai;
    }

    public void crearExcel(ArrayList<Venta> ventas, String generTipo) {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Hola-Java");

        Row rowPrimeraFila = sheet.createRow(0);
        rowPrimeraFila.createCell(0).setCellValue("Id Venta");
        rowPrimeraFila.createCell(1).setCellValue("Id Cliente");
        rowPrimeraFila.createCell(2).setCellValue("Tipo de Pago");
        rowPrimeraFila.createCell(3).setCellValue("Importe de Venta");
        rowPrimeraFila.createCell(4).setCellValue("Fecha");

        rowPrimeraFila.createCell(9).setCellValue("Fecha de Emision del Reporte");
        rowPrimeraFila.createCell(10).setCellValue("" + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm:ss")));

        for (int i = 1; i < ventas.size() + 1; i++) {
            Row row = sheet.createRow(i);
            int k = 0;

            row.createCell(k).setCellValue(ventas.get(i - 1).getIdVenta());
            k++;
            row.createCell(k).setCellValue(ventas.get(i - 1).getCliente().getIdCliente());
            k++;
            row.createCell(k).setCellValue(ventas.get(i - 1).getMetodoPago());
            k++;
            row.createCell(k).setCellValue(ventas.get(i - 1).getImporteTotal());
            k++;
            row.createCell(k).setCellValue(ventas.get(i - 1).getFecha());

        }

        Row rowSuma = sheet.createRow(ventas.size() + 3);

        Cell celdaTotal = rowSuma.createCell(2);
        celdaTotal.setCellValue("MONTO TOTAL VENDIDO:");

        Cell celdas = rowSuma.createCell(3);
        celdas.setCellFormula(suma(ventas.size()));

        try {
            fecha = "" + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd"));

            FileOutputStream fileout = new FileOutputStream("reportesExcel/Reporte" + "-" + generTipo + "-" + fecha + ".xlsx");
            book.write(fileout);
            fileout.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerarReporteExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerarReporteExcel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void abrirarchivoExcel(String generTipo) {

        try {

            File objetofile = new File("reportesExcel/Reporte"+"-"+ generTipo + "-" + fecha + ".xlsx");
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);

        }

    }

}
