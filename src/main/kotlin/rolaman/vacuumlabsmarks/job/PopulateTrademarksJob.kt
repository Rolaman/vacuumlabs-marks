package rolaman.vacuumlabsmarks.job

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import rolaman.vacuumlabsmarks.converter.XmlTextTrademarkConverter
import rolaman.vacuumlabsmarks.model.XmlTrademarkItem
import rolaman.vacuumlabsmarks.model.XmlWordTrademarkItem
import rolaman.vacuumlabsmarks.reader.XmlTrademarkReader
import rolaman.vacuumlabsmarks.repository.TrademarkRepository
import rolaman.vacuumlabsmarks.scanner.FileScanner
import java.io.File
import javax.annotation.PostConstruct

private val log = KotlinLogging.logger {}

@Component
@Profile("populating")
class PopulateTrademarksJob(
    private val fileScanner: FileScanner,
    private val xmlTrademarkReader: XmlTrademarkReader,
    private val converter: XmlTextTrademarkConverter,
    private val repository: TrademarkRepository,
) {

    @Value("\${job.populate.dir:trademark}")
    lateinit var dir: String

    @PostConstruct
    fun init() {
        repopulate()
    }

    fun repopulate() {
        log.info { "Starting populate trademark collection" }
        val files = fileScanner.findByExtension("xml", File(dir))
        files.map {
            xmlTrademarkReader.read(it)
        }.forEach {
            when (it) {
                is XmlWordTrademarkItem -> processWordTrademark(it)
                else -> processOtherTrademark(it)
            }
        }
        log.info { "Finished populating trademark collection" }
    }

    private fun processWordTrademark(item: XmlWordTrademarkItem) {
        val trademark = converter.convert(item)
        repository.add(trademark)
        log.info { "Saving trademark $trademark" }
    }

    private fun processOtherTrademark(item: XmlTrademarkItem) {
        log.info { "Skipping trademark $item" }
    }
}
