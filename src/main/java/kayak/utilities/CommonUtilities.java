package kayak.utilities;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtilities {
    Logger log = Logger.getLogger(getClass().getSimpleName());

    static Properties prop = new Properties();
    static FileInputStream fis = null;
    static JSONParser jsonParser;

    public void loadPropertyFile(String sPropFileName) throws IOException {
        log.info("Loading property file info " + sPropFileName);
        fis = new FileInputStream(sPropFileName);
        prop.load(fis);
        System.getProperties().putAll(prop);
    }

    public void loadLog4jPropertyFile(String sPropertiesFile) throws IOException {
        log.info("loading log4j file properties ");
        fis = new FileInputStream(sPropertiesFile);
        prop.load(fis);
        PropertyConfigurator.configure(prop);
    }
    public String loadJsonFile(String sJsonFileName)
    {
        String json = "";
        log.info("loading json file ");
        try {
            jsonParser = new JSONParser();
            //Use JSONObject for simple JSON and JSONArray for array of JSON.
            JSONObject data = (JSONObject) jsonParser.parse(new FileReader(sJsonFileName));

            json = data.toJSONString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return json;
    }

    public  List<HashMap<String, String>> readExcelDataFromFile(String filePath, String sheetName) {
        List<HashMap<String, String>> excelData = new ArrayList<HashMap<String, String>>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            for (int r = 1; r < sheet.getPhysicalNumberOfRows(); r++) {
                Row CurrentRow = sheet.getRow(r);
//each row of data is stored in new hashmap

                HashMap<String, String> currentRowMap = new HashMap<String, String>();

                for (int c = 0; c < CurrentRow.getPhysicalNumberOfCells(); c++) {
                    Cell CurrentCell = CurrentRow.getCell(c);
                    switch (CurrentCell.getCellType()) {
                        case NUMERIC:
                            // System.out.print(CurrentCell.getStringCellValue() + "\t");
                          //  currentRowMap.put(headerRow.getCell(c).getStringCellValue(), String.valueOf(CurrentCell.getNumericCellValue()));
                            String cellValue=String.valueOf(CurrentCell.getNumericCellValue());
                            String sPattern = "MMMMMMMMM d";
                               DateFormat df = new SimpleDateFormat(sPattern);
                                Date date = CurrentCell.getDateCellValue();
                                cellValue = df.format(date);
                            currentRowMap.put(headerRow.getCell(c).getStringCellValue(), cellValue);
                            break;
                        case STRING:
                            currentRowMap.put(headerRow.getCell(c).getStringCellValue(), CurrentCell.getStringCellValue());
                            break;

// i.e hashmap<key, value> = <row(0)column(c), row(1)column(c)>
                    }

                }
                excelData.add(currentRowMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return excelData;

    }

}
