import java.util.concurrent.atomic.AtomicLong;

public class heapObject {
    int priority;
    int id;
    //static final AtomicLong NEXT_ID = new AtomicLong(0);
    //final long id = NEXT_ID.getAndIncrement();

    public heapObject(int id, int priorityVal){
        if(priorityVal <1){
            this.priority=1;
            return;
        }
        this.priority = priorityVal;
        this.id = id;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "heapObject{" +
                "priority=" + priority +
                ", id=" + id +
                '}';
    }
}
