package exam

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

fun correct(inputName: String, outputName: String) {
    try {
        val outputStream = File(outputName).bufferedWriter()
        //File("input/examIn.txt").delete()
        for (line in File(inputName).readLines()) {
            if (line.isEmpty()) {
                outputStream.newLine()
            }
            if ("$$" !in line) outputStream.write(line)
            else {
                var buffer = ""
                for (char in line) {
                    if ("$$" !in buffer) buffer += char
                    else {
                        val last = char.toString()
                        outputStream.write(buffer.substring(0, buffer.count() - 2))
                        outputStream.newLine()
                        buffer = last
                    }
                }
                outputStream.write(buffer)
            }
            outputStream.newLine()
        }
        outputStream.close()
    } catch (e: FileNotFoundException) {
        throw IOException("Ошибка открытия файла")
    }
}