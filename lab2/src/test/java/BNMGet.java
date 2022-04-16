import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.ForbiddenClassException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class BNMGet {
    public static void main(String[] args) {
        try (CSVReader csvReader = new CSVReader(new FileReader("Date.csv"))) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            String[]  values;
            XStream xstream = new XStream();
            xstream.processAnnotations(Valute.class);
            xstream.processAnnotations(ValCurs.class);
            xstream.allowTypes(new Class[]{ValCurs.class, Valute.class});
            while ((values = csvReader.readNext()) != null) {
                for(String parametru : values){
                    XSSFSheet sheet = workbook.createSheet(parametru);
                    Row date = sheet.createRow(0);
                    date.createCell(0).setCellValue(parametru);
                    Row head = sheet.createRow(1);
                    head.createCell(0).setCellValue("ID");
                    head.createCell(1).setCellValue("Num Code");
                    head.createCell(2).setCellValue("Char Code");
                    head.createCell(3).setCellValue("Nominal");
                    head.createCell(4).setCellValue("Name");
                    head.createCell(5).setCellValue("Value");

                    int rownum = 2;
                    HttpClient httpClient = HttpClientBuilder.create().build();
                    HttpGet getRequest = new HttpGet(
                            "http://www.bnm.md/ro/official_exchange_rates?get_xml=1&date=" + parametru);
                    getRequest.addHeader("accept", "application/xml");
                    HttpResponse response = httpClient.execute(getRequest);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader((response.getEntity().getContent())));

                        String output;
                        String xml = "";
                        while ((output = br.readLine()) != null) {
                            xml = xml.concat(output.trim());
                        }
                        ValCurs valCurs;
                        valCurs = (ValCurs) xstream.fromXML(xml);
                        for (Valute currentVal : valCurs.getValutes()) {
                            Row row = sheet.createRow(rownum++);
                            row.createCell(0).setCellValue(Integer.parseInt(currentVal.getId()));
                            row.createCell(1).setCellValue(Integer.parseInt(currentVal.getNumCode()));
                            row.createCell(2).setCellValue(currentVal.getCharCode());
                            row.createCell(3).setCellValue(currentVal.getNominal());
                            row.createCell(4).setCellValue(currentVal.getName());
                            row.createCell(5).setCellValue(currentVal.getValue());

                        }
                        System.out.println();
                    }
                }
            }
            FileOutputStream out = new FileOutputStream("valute.xlsx");
            workbook.write(out);
            out.close();
        } catch (IOException | ForbiddenClassException e) {
            e.printStackTrace();
            e.getCause();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

    }
}
