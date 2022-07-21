import org.assertj.core.api.Assertions
import org.junit.Test

object Singleton {

    init {
        println("Initializing $this")
    }

    fun log() : Singleton = apply { println("Singleton: $this") }
}

class SingletonTest {

    @Test
    fun testSingleton(){
        println("Start")
        val object1 = Singleton.log()
        val object2 = Singleton.log()

        Assertions.assertThat(object1).isSameAs(Singleton)
        Assertions.assertThat(object2).isSameAs(Singleton)
    }
}