package org.artie4.datatier.service

import org.artie4.datatier.dto.PriceCalculationDto
import java.math.BigDecimal

interface PriceCalculationService {

    fun calculate(calculationDto: PriceCalculationDto): BigDecimal
}
