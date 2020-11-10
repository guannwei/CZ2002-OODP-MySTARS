package Controller;
import Controller.FileManager;
import Model.Course;
import Model.Index;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CourseController {
    private HashMap<String,Course> courses;
    private HashMap<Integer, Index> indexes;

    public CourseController( HashMap courses, HashMap indexes ){
        this.courses=courses;
        this.indexes=indexes;
    }



    public void updateCourse() throws IOException {
        //scan for Object with courseCode = CourseCode
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Course Code to update:");
        String CourseCode=sc.next();
        if (courses.get(CourseCode)!=null){
            Course course =courses.get(CourseCode);



            System.out.println("Choose what to update: \n" +
                    "1)Course Code\n" +
                    "2)School\n" +
                    "3)Index Numbers\n" +
                    "4)Vacancy\n");

            switch (sc.nextInt()) {
                case 1:
                    System.out.print("Enter new Course Code:");
                    course.setCourseCode(sc.next());
                    System.out.println("Course Code successfully updated!");
                    break;

                case 2:

                    System.out.print("Enter new School:");
                    course.setSchool(sc.next());
                    System.out.println("School successfully updated!");
                    break;

                case 3:
                    System.out.println("Do you want to: \n" +
                            "1)Change an index's number\n" +
                            "2)Add an index\n");

                    switch (sc.nextInt()) {
                        case 1:
                            System.out.print("Enter the index you wish to change: ");
                            String indexChange = sc.next();
                            System.out.print("Enter the new index: ");
                            int newChange = sc.nextInt();
                            indexes.get(indexChange).setIndexNumber(newChange);
                            break;

                        case 2:
                            System.out.print("Enter the index you wish to add: ");
                            int indexNum = sc.nextInt();
                            System.out.print("Enter the Course Code: ");
                            String courseCode = sc.next();
                            System.out.print("Enter the Vacancy: ");
                            int vacancyInt = sc.nextInt();
                            indexes.put(indexNum,new Index(indexNum, courseCode, vacancyInt));
                            break;
                    }
                    break;

                case 4:
                    System.out.print("Enter the index which vacancy you wish to change: ");
                    String indexChange = sc.next();
                    System.out.print("Enter the new vacancy: ");
                    int newChange = sc.nextInt();
                    indexes.get(indexChange).setVacancy(newChange);
                    break;

            }
            FileManager.saveCourse(courses);
            FileManager.saveIndex(indexes);







        }
        else
            System.out.println("Course Code does not exist");





    }


    public void addCourse() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new Course Code:");
        String courseCode=sc.next();
        if (courses.get(courseCode)!=null){
            System.out.println("This Course Code already exists");
            return;
        }
        System.out.print("Enter new Course Name:");
        String courseName=sc.next();
        System.out.print("Enter Course School:");
        String school=sc.next();
        courses.put(courseCode,new Course(courseCode,courseName,school));
        FileManager.saveCourse(courses);

        System.out.println("New Course successfully added!");

    }

    public void delCourse() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Course Code to be deleted:");
        String courseCode=sc.next();
        if (courses.get(courseCode)==null){
            System.out.println("This Course Code does not exists");
            return;
        }

        courses.remove(courseCode);
        FileManager.saveCourse(courses);

        System.out.println("New Course successfully added!");
    }

    public void checkVacant(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the index of which you like to check vacancy for:");
        int index = sc.nextInt();
        if (indexes.get(index)==null){
            System.out.println("This index does not exist.");
            return;
        }
        int vacancy=indexes.get(index).getVacancy();
        System.out.println("This index has vacancy of "+vacancy+" students.");
    }

    

}



