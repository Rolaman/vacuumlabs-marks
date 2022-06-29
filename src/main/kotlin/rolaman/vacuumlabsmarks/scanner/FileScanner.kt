package rolaman.vacuumlabsmarks.scanner

import org.springframework.stereotype.Component
import java.io.File

@Component
class FileScanner {

    fun findByExtension(extension: String, dir: File): Sequence<File> {
        return dir.walkBottomUp()
            .filter { it.isFile && it.extension == extension }
    }
}