package Helper;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {

    public static final String Sheet2 = "Sheet2";
    public static final String Sheet1 = "Sheet1";

    private static final String TESTDATA = "./Config/testData.xlsx";

    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Sheet sh;
    private Row row;
    private CellStyle cellstyle;
    private Color mycolor;
    private final Map<String, Integer> columns = new HashMap<>();

    @DataProvider(name = "data-set")
    public static Object[][] DataSet() throws Exception {
        ExcelUtil excel = new ExcelUtil();
        excel.setExcelFile("Sheet2");
        Object[][] obj = excel.to2DArray();
        return obj;
    }


    public static void main(String[] args) throws Exception {
        ExcelUtil excel = new ExcelUtil();
        excel.setExcelFile(Sheet2);

        excel.setExcelFile(Sheet1);
        System.out.println(excel.getCellData("fullname", 1));
        System.out.println(excel.getCellData("email", 1));
        System.out.println(excel.getCellData("telephone", 1));
        System.out.println(excel.getCellData(2, 2));
        System.out.println(excel.getCellData(1, 1));
        System.out.println(excel.getCellData(1, 1));
        System.out.println(excel.getCellData(1, 1));

        excel.getCellData(1, 1);
        Object[][] obj = excel.to2DArray();
        excel.setExcelFile(Sheet1);
        for (Object[] objects : obj) {
            for (Object object : objects) {
                System.out.println(object);
            }
        }
    }

    public void setExcelFile(String SheetName) {
        try {
            File fis = new File(TESTDATA);
            Workbook wb = WorkbookFactory.create(fis);
            sh = wb.getSheet(SheetName);

            sh.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCellData(int rownum, int colnum) {
        try {
            Cell cell = sh.getRow(rownum).getCell(colnum);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    public String getCellData(String columnName, int rownum) {
        return getCellData(rownum, columns.get(columnName));
    }

    public int getNoOfRows() {
        return sh.getPhysicalNumberOfRows();
    }

    public int getNoOfColumns() {
        return sh.getRow(0).getLastCellNum();
    }

    public Object[][] to2DArray() {
        int noOfRows = getNoOfRows() - 1;
        int noOfCells = getNoOfColumns();
        Object[][] obj = new Object[noOfRows][noOfCells];

        for (int i = 0; i < noOfRows; i++) { //i = 0 1 2
            for (int j = 0; j < noOfCells; j++) {
                obj[i][j] = getCellData(i + 1, j);
            }
        }
        return obj;
    }
}
