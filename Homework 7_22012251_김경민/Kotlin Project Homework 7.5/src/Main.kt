/* Kotlin Project Homework 7.5
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 5.
 * 스마트폰으로 촬영한 JPG 사진 파일의 메타 정보 추출 및 Binary Dump
 */

// metadata-extractor 라이브러리 추가
import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.Metadata
import com.drew.metadata.exif.ExifIFD0Directory
import com.drew.metadata.exif.GpsDirectory
import com.drew.metadata.exif.ExifSubIFDDirectory
import java.io.File
import java.io.FileInputStream

fun main() {
    val filePath = "sample_photo.jpeg" // 스마트폰으로 찍은 사진이 jpeg 형식
    val file = File(filePath) // File 객체를 생성 후, 지정된 파일 경로 참조
    if (!file.exists()) {
        println("파일이 존재하지 않습니다. ")
        return
    }

    try {
        // 메타데이터 일기
        val metadata: Metadata = ImageMetadataReader.readMetadata(file)

        // 촬영 날짜 추출
        val exifDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory::class.java)
        val date = exifDirectory?.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL)

        // GPS 정보 추출
        val gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory::class.java)
        val gpsLocation = gpsDirectory?.geoLocation

        // 촬영 날짜 결과 출력
        if (date != null) {
            println("촬영 날짜: $date")
        } else {
            println("촬영 날짜 정보를 찾을 수 없습니다.")
        }

        if (gpsLocation != null) {
            println("GPS 위치: 위도 ${gpsLocation.latitude}, 경도 ${gpsLocation.longitude}")
        } else {
            println("GPS 위치 정보를 찾을 수 없습니다.")
        }

        // 촬영 장치 정보 추출 (카메라 제조사 및 모델)
        val exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory::class.java)
        val cameraMake = exifIFD0Directory?.getString(ExifIFD0Directory.TAG_MAKE)
        val cameraModel = exifIFD0Directory?.getString(ExifIFD0Directory.TAG_MODEL)

        if (cameraMake != null && cameraModel != null) {
            println("촬영 장치: $cameraMake $cameraModel")
        } else {
            println("촬영 장치 정보를 찾을 수 없습니다.")
        }
    } catch (e: Exception) {
        e.printStackTrace()
        println("메타데이터를 읽는 중 오류가 발생했습니다.")
    }
    JPG_photo_dump(filePath)
}

fun JPG_photo_dump(filePath : String) {

    val dumpSize: Int = 256 // 읽을 바이트 크기
    val bufferSize = 16 // 한 번에 읽을 바이트 크기
    val buffer = ByteArray(bufferSize) // 데이터를 읽어 저장할 버퍼 생성

    try {
        val inputStream = FileInputStream(filePath)
        var bytesRead = inputStream.read(buffer)
        var addr:Int = 0

        while (bytesRead != -1) {
            printBinData(buffer, bytesRead, addr)
            addr = addr + bytesRead // 읽은 바이트 수만큼 주소값을 증가
            if (addr >= dumpSize)
                break
            bytesRead = inputStream.read(buffer)
        }
        inputStream.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun isControlChar(byte: Byte): Boolean { // 제어 문자 여부 판단 함수
    return ((byte < 32) || (byte.toInt() > 127))
}

// Function to process the data read from the buffer
fun printBinData(buffer: ByteArray, bytesRead: Int, addr: Int) {
    System.out.printf("%08X:", addr) // 주소 16진수로 출력
    for (i in 0 until bytesRead) {
        if (i % 8 == 0) // 8bytes 마다
            System.out.printf(" ") // 한 칸 띄우기
        System.out.printf("%02X ", buffer[i])
    }
    System.out.printf(" ")

    for (i in 0 until bytesRead) {
        var byte = buffer[i]
        if (isControlChar(byte)) // 제어 문자면 점(.)으로 표시
            System.out.printf(".")
        else System.out.printf("%c", buffer[i])
    }
    System.out.printf("\n")
}


