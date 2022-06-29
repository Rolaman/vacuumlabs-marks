package rolaman.vacuumlabsmarks

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import rolaman.vacuumlabsmarks.config.TestElasticConfig
import rolaman.vacuumlabsmarks.model.Trademark
import rolaman.vacuumlabsmarks.repository.TrademarkRepository
import rolaman.vacuumlabsmarks.web.view.TrademarkView
import rolaman.vacuumlabsmarks.web.view.trademarkView
import java.util.UUID

@Import(TestElasticConfig::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrademarkControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Autowired
    lateinit var trademarkRepository: TrademarkRepository

    @Test
    fun shouldReturnTrademarkByName() {
        val trademark = Trademark(
            id = UUID.randomUUID().toString(),
            name = "TestMark",
            normalizedName = "testmark",
            externalId = UUID.randomUUID().toString(),
        )
        trademarkRepository.add(trademark)

        val responseEntity =
            testRestTemplate.getForEntity("/api/trademark?name=${trademark.name}", TrademarkView::class.java)
        assertEquals(trademarkView(trademark), responseEntity.body)
    }
}
