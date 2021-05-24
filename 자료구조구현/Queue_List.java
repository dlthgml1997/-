// 배열로 큐 구현하기
// 선입선출 
public class Queue_List {
	static int MAX = 100;
	static int front;
	static int rear;
	static int[] queue;

	public static boolean isEmpty() {
		return front == rear;
	}

	public static boolean isFull() {
		return rear == MAX - 1;
	}

	public static int size() {
		return rear - front;
	}

	public static void push(int value) {
		if (isFull()) {
			System.out.println("full");
			return;
		}
		queue[rear++] = value;
	}

	public static int pop() {
		if (isEmpty()) {
			System.out.println("empty");
			return -1;
		}
		return queue[front++];
	}

	public static int peek() {
		if (isEmpty()) {
			System.out.println("empty");
			return -1;
		}
		return queue[front];
	}

	public static void main(String[] args) {
		front = rear = 0;
		queue = new int[MAX];
		push(1);
		push(2);
		push(3);
		
		System.out.println(peek());
		System.out.println(size()+"size");
		pop();
		System.out.println(peek());
	}
}
