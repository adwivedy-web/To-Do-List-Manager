

public class ToDoFX {

public static class TaskList {

class Node {
String task;
Node next;

Node(String task) {
this.task = task + " [Pending]";
this.next = null;
}
}

private Node head;

public void addTask(String task) {
Node newNode = new Node(task);
if (head == null) {
head = newNode;
} else {
Node temp = head;
while (temp.next != null)
temp = temp.next;
temp.next = newNode;
}
}


public void markDone(int index) {
Node temp = head;
int i = 0;
while (temp != null) {
if (i == index) {
temp.task = temp.task.replace("[Pending]", "[Done]");
break;
}
temp = temp.next;
i++;
}
}

    
public void deleteTask(int index) {
if (head == null) return;
if (index == 0) {
        head = head.next;
    return;
     }

            Node temp = head;
            for (int i = 0; temp.next != null; i++) {
   if (i == index - 1) {
        temp.next = temp.next.next;
        break;
     }
 temp = temp.next;
            }
        }


        public void clear() {
        head = null;
        }


public String[] getTasks() {
    int size = size();
    String[] tasks = new String[size];
    Node temp = head;
    int i = 0;
    while (temp != null) {
    tasks[i++] = temp.task;
    temp = temp.next;
            }
            return tasks;
        }

        
public int size() {
int count = 0;
Node temp = head;
while (temp != null) {
    count++;
        temp = temp.next;
}
return count;
}
}

        public static void main(String[] args) {
        ToDoUI.launch(ToDoUI.class, args);  
    }
}
