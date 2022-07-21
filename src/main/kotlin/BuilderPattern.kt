import org.assertj.core.api.Assertions
import org.junit.Test

class Component private constructor(builder: Builder) {
    var param1 : String? = null
    var param2 : Int? = null
    var param3 : Boolean? = null

    class Builder {
        private var _param1 : String? = null
        private var _param2 : Int? = null
        private var _param3 : Boolean? = null

        val param1 get() = _param1
        val param2 get() = _param2
        val param3 get() = _param3

        fun setParam1(param1 : String) = apply { this._param1 = param1 }
        fun setParam2(param2 : Int) = apply { this._param2 = param2 }
        fun setParam3(param3 : Boolean) = apply { this._param3 = param3 }
        fun build () = Component( this)
    }

    init {
        param1 = builder.param1
        param2 = builder.param2
        param3 = builder.param3
    }
}

class CompontentTest {
    val testString = "Some Value"
    val testBool = false

    @Test
    fun builderTest() {

        val component : Component = Component.Builder()
            .setParam1(testString)
            .setParam3(testBool)
            .build()

        Assertions.assertThat(component.param1).isEqualTo(testString)
        Assertions.assertThat(component.param2).isEqualTo(null)
        Assertions.assertThat(component.param3).isEqualTo(testBool)

    }

}