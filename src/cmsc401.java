import java.util.Scanner;
public class cmsc401 {

    public static heapObject[] minHeap;
    private static final Scanner scanner = new Scanner(System.in);
    static int N;
    static int lastHeapPosition = -1;

        public static void main(String[] args) {
            System.out.println("Enter the size of the min-heap (1-10000)");

            N = readInt();
//        if(N >10000) N=10000;
//        else if(N <1) N=1;
            minHeap = new heapObject[N];
            scanner.nextLine();
            readNextInput();
        }

        public static void options() {
            System.out.println("OPTIONS:\nINSERT: enter (1 x y) in console with x being the id and y being the priority to enter" +
                    "the object into the min-heap.\nDECREASEKEY: enter (2 x y) with x being the id and y being the new lower priority" +
                    "to decrease the priority current object with the id of x to the new priority of y.\n" +
                    "EXTRACTMIN: enter (3) into the console to return the minimum priority object from the min-heap.\n" +
                    "EXIT: enter (4) into the console to exit the program.\n" +
                    "NOTE: enter commands without ()");
        }

        public static void insert(int id, int priorityKey) {
            //if(minHeap[N-1] !=null) return;
            heapObject insert = new heapObject(id, priorityKey);
            lastHeapPosition++;
            minHeap[lastHeapPosition] = insert;
            decreaseKey(id, lastHeapPosition, priorityKey);
        }

        public static void decreaseKey(int id, int position, int newPriorityKey) {
            if (newPriorityKey > minHeap[position].priority) {
                System.out.println("Error, new priority is higher than current priority");
                return;
            }

            minHeap[position].priority = newPriorityKey;
            int tempPos = position;
            // now work for 1 2 9, 1 1 4, 1 0 1
            while (tempPos > 0 && minHeap[(int) Math.floor(tempPos / 2)].priority > minHeap[tempPos].priority) {
                swap(tempPos, (int) Math.floor(tempPos / 2));
                tempPos = (int) Math.floor(tempPos / 2);
            }
        }

        public static heapObject extractMin() {
            heapObject min = minHeap[0];
            if (lastHeapPosition == 0) {
                minHeap[0] = null;
                lastHeapPosition--;
                System.out.println(min.id + " " + min.priority);
                return min;
            } else {
                minHeap[0] = minHeap[lastHeapPosition];
                minHeap[lastHeapPosition] = null;
                lastHeapPosition--;

                System.out.println(min.id + " " + min.priority);
                minHeapify(minHeap, lastHeapPosition, 0);
                return min;
            }
        }

        //    public static void notEnoughArgs(){
//            //System.out.println("not enough arguments, check options");
//            options();
//            readNextInput();
//    }
        public static void minHeapify(heapObject[] minHeap, int n, int i) {
            heapObject smallest;
            heapObject left = null;
            heapObject right = null;
            int smallestPosition;
            if ((2 * i + 2 > n && 2 * i + 1 > n) && i == 0) return;

            if (i >= lastHeapPosition / 2 && i != 0) return;// the rest are leaves

            if(!(2*i +1 >n )) {
                left = minHeap[2 * i + 1];
            }
            if(!(2*i+2>n)) {
                right = minHeap[2 * i + 2];
            }
            if(left == null) return;
            if(right == null) return;
                if (left.id <= n && left.priority < minHeap[i].priority) {
                    smallest = left;
                    smallestPosition = 2 * i + 1;
                } else {
                    smallest = minHeap[i];
                    smallestPosition = i;
                }
                if (right.id <= n && right.priority < smallest.priority) {
                    smallest = right;
                    smallestPosition = 2 * i + 2;
                }
                if (smallest != minHeap[i]) {

                    swap(smallestPosition, i);
                    //minHeapify(minHeap, n-1, smallestPosition);// may need to think about when the n when minus, does it mess with
                    //lastheapposition?
                    minHeapify(minHeap, n, smallestPosition);
                }
            }

        public static void buildMinHeap() {

        }

        public static void readNextInput() {
            System.out.println("Enter a command: type options to see commands");
            String response = readString();
            String[] parsedResponse = response.split(" ");

//        if(response.equalsIgnoreCase("options")){
//            options();
//            readNextInput();
//        }
            if (parsedResponse[0].equalsIgnoreCase("1")) {
//            if(parsedResponse.length<3){
//                notEnoughArgs();
//            }
                insert(Integer.parseInt(parsedResponse[1]), Integer.parseInt(parsedResponse[2]));
                printHeap();
                readNextInput();
            } else if (parsedResponse[0].equalsIgnoreCase("2")) {
//            if(parsedResponse.length<3){
//                notEnoughArgs();
//            }
                int id = Integer.parseInt(parsedResponse[1]);
                int newPrio = Integer.parseInt(parsedResponse[2]);
                int pos = 0;
                for (int i = 0; i <= lastHeapPosition; i++) {
                    if (minHeap[i].id == id) {
                        pos = i;
                        break;
                    }
                }
                decreaseKey(id, pos, newPrio);
                minHeapify(minHeap, lastHeapPosition, 0);
                printHeap();
                readNextInput();
            } else if (parsedResponse[0].equalsIgnoreCase("3")) {
                extractMin();
                printHeap();
                readNextInput();
            } else if (parsedResponse[0].equalsIgnoreCase("4")) {
                //System.out.println("Goodbye!");
                System.exit(0);
            } else {
                printHeap();
                readNextInput();
            }
        }

        // takes in positions respective to the minHeap (the index of minHeapPositions is equal to the id of the heap object,
        // and it holds the position the heapObject is currently at in the minHeap) and swaps the objects in those positions.
        public static void swap(int a, int b) {
            heapObject temp = minHeap[a];
            minHeap[a] = minHeap[b];
            minHeap[b] = temp;
        }

        private static Integer readInt() {
            return scanner.nextInt();
        }

        private static String readString() {
            return scanner.nextLine();
        }

        private static void printHeap() {
            if (lastHeapPosition == -1) return;
            String heap = "";
            for (int i = 0; i <= lastHeapPosition; i++) {
                if (minHeap[i] != null) {
                    heap += minHeap[i].priority + ",";

                }
            }
            System.out.println(heap);
        }
    }