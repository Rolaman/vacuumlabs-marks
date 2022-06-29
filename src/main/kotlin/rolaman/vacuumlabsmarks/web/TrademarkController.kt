package rolaman.vacuumlabsmarks.web

import org.springframework.web.bind.annotation.*
import rolaman.vacuumlabsmarks.web.view.TrademarkView
import rolaman.vacuumlabsmarks.repository.TrademarkRepository
import rolaman.vacuumlabsmarks.web.exception.TrademarkNotFoundException
import rolaman.vacuumlabsmarks.web.view.TrademarkListView
import rolaman.vacuumlabsmarks.web.view.trademarkView

@RestController
@RequestMapping("/api/trademark")
class TrademarkController(
    private val trademarkRepository: TrademarkRepository,
) {

    @GetMapping
    fun find(@RequestParam name: String): TrademarkView {
        val trademark = trademarkRepository.findByName(name) ?: throw TrademarkNotFoundException(name)
        return trademarkView(trademark)
    }

    @GetMapping("/search")
    fun search(@RequestParam text: String): TrademarkListView {
        val trademarks = trademarkRepository.findManyByText(text)
        return TrademarkListView(
            items = trademarks.map { trademarkView(it) }.toList(),
        )
    }
}
