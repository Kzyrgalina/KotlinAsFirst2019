package exam

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.File

class examTest {
    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    fun correct() {
        //File("input/examOut.txt").delete()
        correct("input/examIn.txt", "input/examOut.txt")
        assertFileContent(
            "input/examOut.txt",
            """Строка без знака.
А Роза упала на лапу #этого Азора. 
Буратино не любит ходить в школу, потому что любит развлекаться.
Дополнительная строчка для теста.
Очень много строк 
для теста.
))))))))"""
        )
        File("input/examOut.txt").delete()
    }
}