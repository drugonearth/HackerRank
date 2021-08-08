import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

//https://www.hackerrank.com/challenges/java-priority-queue/problem

class Student
{
    int id;
    String name;
    double CGPA;

    public Student(int id, String name, double CGPA) {
        this.id = id;
        this.name = name;
        this.CGPA = CGPA;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCGPA() {
        return CGPA;
    }
}

class Priorities
{
    PriorityQueue<Student> priorityQueue = new PriorityQueue<>((student1, student2) ->
    {
        if(Double.compare(student1.getCGPA(), student2.getCGPA())!=0)
            return Double.compare(student2.getCGPA(), student1.getCGPA());
        else if(student1.getName().compareTo(student2.getName())!=0)
            return student1.getName().compareTo(student2.getName());
        else return Integer.compare(student1.getID(), student2.getID());
    });
    public List<Student> getStudents(List<String> events)
    {
        for(int i = 0; i < events.size(); i++)
        {
            if(events.get(i).equals("SERVED")) priorityQueue.poll();
            else
            {
                String[] s = events.get(i).split(" ");
                priorityQueue.add(new Student(Integer.parseInt(s[3]), s[1],Double.parseDouble(s[2])));
            }
        }
        List<Student> students = new ArrayList<>();
        int size = priorityQueue.size();
        for (int i = 0; i < size; i++) {
            students.add(priorityQueue.poll());
        }
        return students;
    }
}


public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}
