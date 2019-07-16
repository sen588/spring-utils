package sen.utils.java;


import lombok.Data;

public class equalsDemo {

    public static void main(String[] args)
    {
        student student1 = new student();
        student student2 = new student();
        //false
        System.out.println(student1.equals(student2));
        //false
        System.out.println(student1 == student2);

        student1.setName("张三");
        student2.setName("张三");
        System.out.println(student1.getName() == student2.getName());

        String string1 = new String();
        String string2 = new String();
        //true
        System.out.println(string1.equals(string2));
        //false
        System.out.println(string1 == string2);
    }
}

@Data
class student
{
    private Integer id;
    private String name;
}