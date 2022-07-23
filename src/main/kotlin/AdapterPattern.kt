import org.assertj.core.api.Assertions
import org.junit.Test

// 3rd PArty Functionality
data class DisplayDataType(val index: Float,val data: String)

class DataDisplay {
    fun displayData(data: DisplayDataType){
     println("Data is displayed: ${data.index} = ${data.data}")
    }
}
//-------------------------

//Our Code

data class DatabaseData(val position: Int, val amount : Int)

class DatabaseDataGenerator {
    fun generateData() : List<DatabaseData>{
        val list = arrayListOf<DatabaseData>(
            DatabaseData(1,1),
            DatabaseData(2,2),
            DatabaseData(3,3)
        )
        return list
    }
}

interface DatabaseDataConverter {
    fun convertData(data: List<DatabaseData>) : List<DisplayDataType>
}

class DataDisplayAdapter(val display: DataDisplay) : DatabaseDataConverter {
    override fun convertData(data: List<DatabaseData>): List<DisplayDataType> {
        val returnList = arrayListOf<DisplayDataType>()

        for(datum in data){
            val  ddt = DisplayDataType(datum.position.toFloat(), datum.amount.toString())
            display.displayData(ddt)
            returnList.add(ddt)
        }

        return returnList
    }

}

class AdapterTest {

    @Test
    fun adapterTest() {
        val generator = DatabaseDataGenerator()
        val generatedData = generator.generateData()
        val adapter = DataDisplayAdapter(DataDisplay())
        val convertData = adapter.convertData(generatedData)

        Assertions.assertThat(convertData.size).isEqualTo(3)
        Assertions.assertThat(convertData[1].index).isEqualTo(2f)
        Assertions.assertThat(convertData[1].data).isEqualTo("2")
    }
}


