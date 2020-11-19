package Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import Model.*;

public class FileManager {
	public static final String SEPARATOR = ",";
	public static final String SEPARATORLIST = "/";

	public static ArrayList readAdmin() throws IOException {
		String filename = "data/user-admin.txt" ;
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
				String  username = star.nextToken().trim();	
				String  password = star.nextToken().trim();	
				Admin admin = new Admin(username,password);
				alr.add(admin) ;
			}
			return alr ;
	}
	
	public static HashMap<String, Student> readStudents() throws IOException {
		String filename = "data/user-student.txt" ;

		HashMap<String,Student> alr = new HashMap<>();
		String line;
		BufferedReader br = new BufferedReader(new FileReader(filename));
		while((line = br.readLine()) != null) {
			String [] parts = line.split(SEPARATOR);
			String username = parts[0].trim();
			String password = parts[1].trim();
			String email = parts[2].trim();
			String name = parts[3].trim();
			String matricNumber = parts[4].trim();
			String gender = parts[5].trim();
			String nationality = parts[6].trim();
			LocalDateTime accessStartPeriod = LocalDateTime.parse(parts[7].trim());
			LocalDateTime accessEndPeriod = LocalDateTime.parse(parts[8].trim());
			Student stu = new Student(username, password, email, name, matricNumber, gender, nationality, accessStartPeriod, accessEndPeriod);
			alr.put(matricNumber, stu);
		}

		return alr ;
	}

	
	public static void saveStudent(HashMap<String,Student> al) throws IOException {
		String filename = "data/user-student.txt" ;
		//List alw = new ArrayList() ;
		BufferedWriter bf = new BufferedWriter(new FileWriter(filename));
		
		for(Map.Entry<String, Student> entry : al.entrySet()){
            
            //put key and value separated by a colon
            bf.write(entry.getValue().getUsername() + SEPARATOR + entry.getValue().getPassword() + SEPARATOR +
            		entry.getValue().getEmail() + SEPARATOR + entry.getValue().getName() + SEPARATOR + entry.getKey() + SEPARATOR + entry.getValue().getGender() +
            		SEPARATOR + entry.getValue().getNationality() + SEPARATOR + entry.getValue().getAccessStartPeriod() + SEPARATOR + entry.getValue().getAccessEndPeriod());
            
            //new line
            bf.newLine();
        }
        
        bf.flush();
        bf.close();
	}

	public static HashMap<String,Course> readCourse() throws IOException {
		String filename = "data/courses.txt" ;
		ArrayList stringArray = (ArrayList)read(filename);
		HashMap<String,Course> courses = new HashMap<>();

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);
			
			String  courseCode = star.nextToken().trim();
			String  courseName = star.nextToken().trim();
			String  school = star.nextToken().trim();
			int au = Integer.parseInt(star.nextToken().trim());
			
			
			courses.put(courseCode, new Course(courseCode, courseName, school, au));
			
		}
		return courses;
	}
	
	

	public static void saveCourse(HashMap courses) throws IOException {
		String filename = "data/courses.txt";
		List alw = new ArrayList() ;
		Set set = courses.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Course course = (Course)entry.getValue();
			StringBuilder st =  new StringBuilder() ;
			st.append(course.getCourseCode().trim());
			st.append(SEPARATOR);
			st.append(course.getCourseName().trim());
			st.append(SEPARATOR);
			st.append(course.getSchool().trim());
			st.append(SEPARATOR);
			st.append(course.getAu());

			alw.add(st.toString()) ;
		}
		write(filename,alw);
	}
	
	public static HashMap readIndex() throws IOException {
		String filename = "data/indexes.txt" ;
		ArrayList stringArray = (ArrayList)read(filename);
		HashMap<Integer,Index> indexes = new HashMap<>();

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);
			StringTokenizer starList = new StringTokenizer(st , SEPARATORLIST);
			Queue<String> waitList = new LinkedList<String>();
			
			int indexNumber = Integer.parseInt(star.nextToken().trim());
			String coursecode = star.nextToken().trim();
			int vacancy = Integer.parseInt(star.nextToken().trim());
			int max = Integer.parseInt(star.nextToken().trim());
			
			
			String waitListBeforeSplit = star.nextToken().trim();
			String[] waitListSplit = waitListBeforeSplit.split(SEPARATORLIST);
			for(String a : waitListSplit) {
				waitList.add(a);
			}
			
			indexes.put(indexNumber,new Index(indexNumber,coursecode,vacancy,max,waitList));

		}
		return indexes;
	}

	public static void saveIndex(HashMap indexes) throws IOException {
		String filename = "data/indexes.txt" ;
		List alw = new ArrayList() ;
		Set set = indexes.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Index index = (Index)entry.getValue();
			StringBuilder st =  new StringBuilder() ;
			st.append(index.getIndexNumber());
			st.append(SEPARATOR);
			st.append(index.getCourseCode());
			st.append(SEPARATOR);
			st.append(index.getVacancy());
			st.append(SEPARATOR);
			st.append(index.getMax());
			st.append(SEPARATOR);
			
			if(index.getWaitList().size() <= 0) {
				st.append(SEPARATORLIST);
			}
			else {
				for(int i = 0; i < index.getWaitList().size(); i++) {
					st.append(index.getWaitList().poll());
					st.append(SEPARATORLIST);
				}
			}
			alw.add(st.toString()) ;
		}
		write(filename,alw);
	}



    public static void write(String fileName, List data) throws IOException  {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        try {
    		for (int i =0; i < data.size() ; i++) {
          		out.println((String)data.get(i));
    		}
        }
        finally {
          out.close();
        }
      }
      public static List read(String fileName) throws IOException {
    	List data = new ArrayList() ;
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
          while (scanner.hasNextLine()){
            data.add(scanner.nextLine());
          }
        }
        finally{
          scanner.close();
        }
        return data;
      }
      
      public static ArrayList<StudentRegisteredCourses> readStudentRegisteredCourses() throws IOException {
  		String filename = "data/student-registered-courses.txt" ;
  		ArrayList stringArray = (ArrayList)read(filename);
  		ArrayList<StudentRegisteredCourses> alr = new ArrayList();

        for (int i = 0 ; i < stringArray.size() ; i++) {
  			String st = (String)stringArray.get(i);
  			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
  			String  matricNumber = star.nextToken().trim();	
  			int  indexNumber = Integer.parseInt(star.nextToken().trim());	
  			Boolean  complete = Boolean.parseBoolean(star.nextToken().trim());	
  				
  			StudentRegisteredCourses course = new StudentRegisteredCourses(matricNumber, indexNumber, complete);
  				
  			alr.add(course);
  		}
  		return alr;
  	}
      
      public static void saveRegisteredCourses(List al) throws IOException {
			String filename = "data/student-registered-courses.txt" ;
			List alw = new ArrayList() ;
	        for (int i = 0 ; i < al.size() ; i++) {
	        		StudentRegisteredCourses course = (StudentRegisteredCourses)al.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(course.getMatricNumber().trim());
					st.append(SEPARATOR);
					st.append(String.valueOf(course.getIndexNumber()).trim());
					st.append(SEPARATOR);
					st.append(course.getComplete());
					
					alw.add(st.toString()) ;
				}
				write(filename,alw);
		}
      
      public static ArrayList<Lesson> readLessonArray() throws IOException {
    		String filename = "data/lessons.txt" ;
    		ArrayList stringArray = (ArrayList)read(filename);
    		ArrayList<Lesson> alr = new ArrayList();

    		for (int i = 0 ; i < stringArray.size() ; i++) {
	        	String st = (String)stringArray.get(i);
	    		StringTokenizer star = new StringTokenizer(st , SEPARATOR);
	    		
	        	int indexNumber = Integer.parseInt(star.nextToken().trim());
	    		LocalTime startTime = LocalTime.parse(star.nextToken().trim());
	    		LocalTime endTime = LocalTime.parse(star.nextToken().trim());
	    		String day = star.nextToken().trim();
	    		String type = star.nextToken().trim();
	    		String venue = star.nextToken().trim();
    			
    			Lesson le = new Lesson(indexNumber, startTime, endTime, day, type, venue);
    			alr.add(le);
    		}
    		
    		
    		
    		return alr;
    	}
      
      public static void saveLesson(ArrayList<Lesson> al) throws IOException {
			String filename = "data/lessons.txt" ;
			List alw = new ArrayList();
	        for (int i = 0 ; i < al.size() ; i++) {
					Lesson lesson = (Lesson)al.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(lesson.getIndexNumber());
					st.append(SEPARATOR);
					st.append(lesson.getStartTime().toString().trim());
					st.append(SEPARATOR);
					st.append(lesson.getEndTime().toString().trim());
					st.append(SEPARATOR);
					st.append(lesson.getDay().trim());
					st.append(SEPARATOR);
					st.append(lesson.getType().trim());
					st.append(SEPARATOR);
					st.append(lesson.getVenue().trim());
					
					alw.add(st.toString()) ;
				}
				write(filename,alw);
		}
}
    
