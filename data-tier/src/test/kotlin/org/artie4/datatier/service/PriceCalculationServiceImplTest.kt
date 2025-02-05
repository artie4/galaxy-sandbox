package org.artie4.datatier.service

import io.kotest.matchers.shouldBe
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.artie4.datatier.dto.PriceCalculationDto
import org.artie4.datatier.service.impl.PriceCalculationServiceImpl
import org.galaxy.model.Products
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal

@ExtendWith(MockKExtension::class)
class PriceCalculationServiceImplTest {

    @InjectMockKs
    private lateinit var sut: PriceCalculationServiceImpl

    @MethodSource("amountAndResultPriceProvider")
    @ParameterizedTest
    fun calculate(amount: Int, expectedPrice: Double) {

        val calculationDto = PriceCalculationDto(
            planetProductRate = BigDecimal.ONE,
            productType = Products.FOODSTUFF,
            amount = amount,
        )

        val result = sut.calculate(calculationDto)

        result.setScale(3) shouldBe BigDecimal(expectedPrice).setScale(3)
    }

    companion object {
        @JvmStatic
        fun amountAndResultPriceProvider(): List<Arguments> =
            listOf(
                Arguments.of(99, 540),
                Arguments.of(100, 540),
                Arguments.of(999, 450),
                Arguments.of(1000, 450),
                Arguments.of(1001, 150),
                Arguments.of(5000, 150),
                Arguments.of(5001, 120),
            )
    }
}
