/* Kotlin Project Homework 7.4
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 5.
 * Excel File Input / Output, 시계열 데이터 분석 (time-series data analysis)
 */

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
data class TimeSeriesData(val date: Date, val avg: Double, val low: Double, val high: Double)

fun readExcelFile(filePath: String): List<TimeSeriesData> {
    val inputStream = FileInputStream(File(filePath))
    val workbook = WorkbookFactory.create(inputStream)
    val sheet = workbook.getSheetAt(0)
    val records = mutableListOf<TimeSeriesData>()
    // 첫 번째 행을 건너뛰고 데이터를 읽음
    var date : Date
    var avg : Double
    var low : Double
    var high : Double
    for (row in sheet.drop(1)) {
        //println("reading %d-th row".format(row.rowNum))
        if (row.getCell(0) == null)
            break
        date = row.getCell(2).dateCellValue
        /* Kotlin Excel File Input/Output, Time-series Data Analysis (2) */
        if (row.getCell(3) != null)
            avg = row.getCell(3).numericCellValue
        else
            continue
        if (row.getCell(4) != null)
            low = row.getCell(4).numericCellValue
        else
            continue
        if (row.getCell(5) != null)
            high = row.getCell(5).numericCellValue
        else
            continue
        records.add(TimeSeriesData(date, avg, low, high))
    }
    inputStream.close()
    workbook.close()
    return records
}

fun writeExcelFile(filePath: String, data: List<TimeSeriesData>) {
    val workbook = XSSFWorkbook()
    val sheet = workbook.createSheet("Daegu_Temperature")
    // 헤더 작성
    val header = sheet.createRow(0)
    header.createCell(0).setCellValue("Date")
    header.createCell(1).setCellValue("Value")
    // 데이터 작성
    data.forEachIndexed { index, record ->
        val row = sheet.createRow(index + 1)
        row.createCell(0).setCellValue(record.date)
        row.createCell(1).setCellValue(record.avg)
        row.createCell(2).setCellValue(record.low)
        row.createCell(3).setCellValue(record.high)
    }
    val outputStream = FileOutputStream(File(filePath))
    workbook.write(outputStream)
    workbook.close()
    outputStream.close()
}

fun analyzeTimeSeriesData(data: List<TimeSeriesData>) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val lowest = data.minByOrNull{it.low}
    if (lowest != null) {
        print("The lowest temperature of Daegu in last 10 years = %.2f (".
        format(lowest.low))
        print(dateFormat.format(lowest.date))
        println(")")
    } else {
        println("Error in getting lowest temperature")
    }
    val highest = data.maxByOrNull{it.high}
    if (highest != null) {
        print("The highest temperature of Daegu in last 10 years = %.2f (".
        format(highest.high))
        print(dateFormat.format(highest.date))
        println(")")
    } else {
        println("Error in getting highest temperature")
    }
    val avg_temp = (data.map{it.avg}).average()
    println("Average temperature of Daegu in last 10 years = %.2f".
    format(avg_temp))
}

fun main() {
    // Excel 파일 읽기
    val timeSeriesFileName = "DaeguTemp_10years.xlsx"
    println("Reading %s ......".format(timeSeriesFileName))
    val timeSeriesData = readExcelFile(timeSeriesFileName)
    // Time-series 데이터 분석
    println("Analyzing %S ......".format(timeSeriesFileName))
    analyzeTimeSeriesData(timeSeriesData)
}