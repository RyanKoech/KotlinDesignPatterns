import org.assertj.core.api.Assertions
import org.junit.Test

sealed class Country{
    object Canada: Country()
}

object Spain : Country()
class Greece(val someProperty: String) : Country()
data class USA(val someProperty: String) : Country()

class Currency (val code: String)

object CurrencyFactory {
    fun currencyForCountry(country: Country) :  Currency =
        when(country){
            is Spain -> Currency("EUR")
            is Greece -> Currency("EUR")
            is Country.Canada -> Currency("CAD")
            is USA -> Currency("USD")
        }
}

class FactoryMethodTest {

    @Test
    fun currencyTest(){
        val greekCurrency : String = CurrencyFactory.currencyForCountry(Greece("")).code
        val canadianCurrency : String = CurrencyFactory.currencyForCountry(Country.Canada).code

        Assertions.assertThat(greekCurrency).isEqualTo("EUR")
        Assertions.assertThat(canadianCurrency).isEqualTo("CAD")
    }
}
