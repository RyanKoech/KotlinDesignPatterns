import org.junit.Test
import java.io.File

interface EventListener{
    fun update(eventType: String? , file: File?)
}

class EventManager (vararg operations: String) {
    var listeners = hashMapOf<String, ArrayList<EventListener>>()

    init {
        for (operation in operations){
            listeners[operation] = ArrayList<EventListener>()
        }
    }

    fun subscribe(eventType: String? , listener: EventListener){
        val users = listeners.get(eventType)
        users?.add(listener)
    }

    fun unsubscribe(eventType: String? , listener: EventListener){
        val users = listeners.get(eventType)
        users?.remove(listener)
    }

    fun notify(eventType: String?, file: File?) {
        val users = listeners.get(eventType)
        users?.let{
            for (listener in users){
                listener.update(eventType,file)
            }
        }

    }
}

//Event Generator
class Editor {
    var event = EventManager("open", "save")

    private var file: File? = null


    fun openFile(filePath: String){
        file = File(filePath)
        event.notify("open", file)
    }

    fun saveFile(){
       file?.let {
           event.notify("save", file)
       }
    }
}

//Mock event listeners

class EmailNotificationListener (private val email: String): EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Email to $email: Someone performed as $eventType operation on the file ${file?.name}")
    }
}

class LogOpenListener(var filename: String) : EventListener{
    override fun update(eventType: String?, file: File?) {
        println("Save to log ${eventType}: Someone has performed as $filename operation on the file ${file?.name}")
    }
}

class ObserverTest {

    @Test
    fun testObserver() {
        val editor = Editor()
        editor.event.subscribe("open", LogOpenListener("path/to/log/file.txt"))
        editor.event.subscribe("save", EmailNotificationListener("test@test.com"))

        editor.openFile("test.txt")
        editor.saveFile()

    }

}