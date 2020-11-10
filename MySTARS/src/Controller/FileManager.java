package Controller;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
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
	public static ArrayList readStudents() throws IOException {
		String filename = "data/user-student.txt" ;
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
				String  username = star.nextToken().trim();	
				String  password = star.nextToken().trim();	
				String  email = star.nextToken().trim();	
				String  name = star.nextToken().trim();	
				String  matricNumber = star.nextToken().trim();	
				String  gender = star.nextToken().trim();	
				String  nationality = star.nextToken().trim();	
				LocalDateTime accessStartPeriod = LocalDateTime.parse(star.nextToken().trim());
				LocalDateTime accessEndPeriod = LocalDateTime.parse(star.nextToken().trim());
				Student student = new Student(username, password, email,name,matricNumber,gender,nationality,accessStartPeriod,accessEndPeriod);
				alr.add(student) ;
			}
			return alr ;
	}
	
	public static ArrayList readStudentsNoAccessTime() throws IOException {
		String filename = "data/user-student.txt" ;
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
				String  username = star.nextToken().trim();	
				String  password = star.nextToken().trim();	
				String  email = star.nextToken().trim();	
				String  name = star.nextToken().trim();	
				String  matricNumber = star.nextToken().trim();	
				String  gender = star.nextToken().trim();	
				String  nationality = star.nextToken().trim();	
				Student student = new Student(username, password, email,name,matricNumber,gender,nationality);
				alr.add(student) ;
			}
			return alr ;
	}
	
	public static HashMap readStudents2() throws IOException {
		String filename = "data/user-student.txt" ;
		ArrayList stringArray = (ArrayList)read(filename);
		HashMap<String,Student> alr = new HashMap<>() ;

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);
			String  username = star.nextToken().trim();
			String  password = star.nextToken().trim();
			String  email = star.nextToken().trim();
			String  name = star.nextToken().trim();
			String  matricNumber = star.nextToken().trim();
			String  gender = star.nextToken().trim();
			String  nationality = star.nextToken().trim();
			LocalDateTime accessStartPeriod = LocalDateTime.parse(star.nextToken().trim());
			LocalDateTime accessEndPeriod = LocalDateTime.parse(star.nextToken().trim());
			Student student = new Student(username, password, email,name,matricNumber,gender,nationality,accessStartPeriod,accessEndPeriod);
			alr.put(matricNumber,student) ;
		}
		return alr ;
	}

	public static void saveStudent(List al) throws IOException {
			String filename = "data/user-student.txt" ;
			List alw = new ArrayList() ;
	        for (int i = 0 ; i < al.size() ; i++) {
					Student student = (Student)al.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(student.getUsername().trim());
					st.append(SEPARATOR);
					st.append(student.hashPassword(student.getPassword()).trim());
					st.append(SEPARATOR);
					st.append(student.getEmail().trim());
					st.append(SEPARATOR);
					st.append(student.getName().trim());
					st.append(SEPARATOR);
					st.append(student.getMatricNumber().trim());
					st.append(SEPARATOR);
					st.append(student.getGender().trim());
					st.append(SEPARATOR);
					st.append(student.getNationality().trim());
					st.append(SEPARATOR);
					st.append(student.getAccessStartPeriod().toString().trim());
					st.append(SEPARATOR);
					st.append(student.getAccessEndPeriod().toString());
					
					alw.add(st.toString()) ;
				}
				write(filename,alw);
		}
	
	public static void saveStudent2(HashMap<String,Student> al) throws IOException {
		String filename = "data/user-student.txt" ;
		List alw = new ArrayList() ;

		Set set = al.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Student student = (Student) entry.getValue();

			StringBuilder st =  new StringBuilder() ;
			st.append(student.getUsername().trim());
			st.append(SEPARATOR);
			st.append(student.hashPassword(student.getPassword()).trim());
			st.append(SEPARATOR);
			st.append(student.getEmail().trim());
			st.append(SEPARATOR);
			st.append(student.getName().trim());
			st.append(SEPARATOR);
			st.append(student.getMatricNumber().trim());
			st.append(SEPARATOR);
			st.append(student.getGender().trim());
			st.append(SEPARATOR);
			st.append(student.getNationality().trim());
			st.append(SEPARATOR);
			st.append(student.getAccessStartPeriod().toString().trim());
			st.append(SEPARATOR);
			st.append(student.getAccessEndPeriod().toString());

			alw.add(st.toString()) ;
		}
		write(filename,alw);
	}

	
	
	public static void saveAdmin(List al) throws IOException {
		String filename = "data/user-admin.txt" ;
		List alw = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < al.size() ; i++) {
				Admin admin = (Admin)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(admin.getUsername().trim());
				st.append(SEPARATOR);
				st.append(admin.hashPassword(admin.getPassword()).trim());
				
				alw.add(st.toString()) ;
			}
			write(filename,alw);
	}

	public static ArrayList readCourse() throws IOException {
		String filename = "course.txt" ;
		ArrayList stringArray = (ArrayList)read(filename);
		Map<String,Course> courses = new HashMap<>();

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);
			String  courseCode = star.nextToken().trim();
			String  courseName = star.nextToken().trim();
			String  school = star.nextToken().trim();
			courses.put(courseCode, new Course(courseCode, courseName, school));

		}
		return stringArray;
	}
	
	

	public static void saveCourse(HashMap courses) throws IOException {
		String filename = "data/user-student.txt" ;
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
			st.append(course.getCourseName().trim());

			alw.add(st.toString()) ;
		}
		write(filename,alw);
	}
	
	
	public static HashMap readIndex() throws IOException {
		String filename = "Index.txt" ;
		ArrayList stringArray = (ArrayList)read(filename);
		HashMap<Integer,Index> indexes = new HashMap<>();

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);
			StringTokenizer starList = new StringTokenizer(st , SEPARATORLIST);
			int indexNumber = Integer.parseInt(star.nextToken().trim());
			int vacancy = Integer.parseInt(star.nextToken().trim());
			
			Queue<String> waitList = new LinkedList<String>();
			while (starList.nextToken().trim()!=null){
				waitList.add(starList.nextToken().trim());
			}
			indexes.put(indexNumber,new Index(indexNumber,vacancy,waitList));

		}
		return indexes;
	}

	public static void saveIndex(HashMap indexes) throws IOException {
		String filename = "indexes.txt" ;
		List alw = new ArrayList() ;
		Set set = indexes.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Index index = (Index)entry.getValue();
			StringBuilder st =  new StringBuilder() ;
			st.append(index.getIndexNumber());
			st.append(SEPARATOR);
			st.append(index.getVacancy());
			st.append(SEPARATOR);

			for(int i = 0; i < index.getWaitList().size(); i++) {
				st.append(index.getWaitList().poll());
				st.append(SEPARATORLIST);
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
      
      public static ArrayList readStudentRegisteredCourses() throws IOException {
  		String filename = "data/student-registered-courses.txt" ;
  		ArrayList stringArray = (ArrayList)read(filename);
  		ArrayList alr = new ArrayList() ;

          for (int i = 0 ; i < stringArray.size() ; i++) {
  				String st = (String)stringArray.get(i);
  				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
  				String  matricNumber = star.nextToken().trim();	
  				int  indexNumber = Integer.parseInt(star.nextToken().trim());	
  				Boolean  complete = Boolean.parseBoolean(star.nextToken().trim());	
  				
  				StudentRegisteredCourses course = new StudentRegisteredCourses(matricNumber, indexNumber, complete);
  				alr.add(course) ;
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

}
    
