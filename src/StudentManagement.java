import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
	public static List<Student> students = new ArrayList<Student>();

	public static void createStudent() {
		InputStream stream = System.in;
		Scanner sc = new Scanner(stream);
		System.out.println("Nhap ten: ");
		String name = sc.nextLine();
		System.out.println("Nhap dia chi: ");
		String address = sc.nextLine();
		System.out.println("Nhap nam sinh: ");
		int year = sc.nextInt();
		System.out.println("Nhap diem trung binh: ");
		float point = sc.nextFloat();
		Student hs = new Student(name, address, year, point);
		students.add(hs);

	}

	private static void getAllStudent() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("student.txt"))) {
			String name, address;
			int year;
			float point;
			while (true) {
				name = br.readLine();
				if (name == null) {
					break;
				}
				address = br.readLine();
				year = Integer.parseInt(br.readLine());
				point = Float.parseFloat(br.readLine());
				Student st = new Student(name, address, year, point);
				students.add(st);
				name = br.readLine();
			}
		}
		sort();
	}

	public static void showOnConsole() {
		sort();
		for (Student student : students) {
			System.out.println("Ten: " + student.getName());
			System.out.println("Dia chi: " + student.getAddress());
			System.out.println("Tuoi: " + student.getYear());
			System.out.println("Diem: " + student.getPoint());
		}
	}

	public static void writeToFile() throws IOException {
		sort();
		FileWriter fw = null;
		try {
			fw = new FileWriter("student.txt");
			for (Student student : students) {
				fw.write(student.getName() + "\n");
				fw.write(student.getAddress() + "\n");
				fw.write(String.valueOf(student.getYear()) + "\n");
				fw.write(String.valueOf(student.getPoint()) + "\n");
				fw.write("\n");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		fw.close();
	}

	public static int findOne(String name) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getName().contentEquals(name)) {
				return i;
			}
		}
		return -1;
	}

	public static void sort() {
		Student temp = null;
		for (int i = 0; i < students.size(); i++) {
			temp = students.get(i);
			for (int j = i + 1; j < students.size(); j++) {
				if (students.get(j).getPoint() > temp.getPoint()) {
					students.set(i, students.get(j));
					students.set(j, temp);
					temp = students.get(i);
				}
			}
		}
	}

	public static void deleteStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap ten hoc sinh can xoa: ");
		String name = sc.nextLine();
		int index = findOne(name);
		if (index != -1) {
			students.remove(index);
			System.out.println("xoa thanh cong");
		} else
			System.out.println("xoa that bai");

	}

	public static void updateStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap ten hoc sinh can sua: ");
		String name = sc.nextLine();
		int index = findOne(name);
		if (index != -1) {
			System.out.println("Nhap ten: ");
			name = sc.nextLine();
			System.out.println("Nhap dia chi: ");
			String address = sc.nextLine();
			System.out.println("Nhap nam sinh: ");
			int year = sc.nextInt();
			System.out.println("Nhap diem trung binh: ");
			float point = sc.nextFloat();
			Student hs = new Student(name, address, year, point);
			students.set(index, hs);
		} else {
			System.out.println("khong tim thay hoc sinh");
		}

	}

	public static void showOneStudent(int index) {
		System.out.println("Ten: " + students.get(index).getName());
		System.out.println("Dia chi: " + students.get(index).getAddress());
		System.out.println("Tuoi: " + students.get(index).getYear());
		System.out.println("Diem: " + students.get(index).getPoint());
	}

	public static void main(String[] args) throws IOException {
		getAllStudent();
		Scanner sc = new Scanner(System.in);
		int k;
		while (true) {
			System.out.println("0/Ket thuc cong viec");
			System.out.println("1/Xem danh sach hoc sinh");
			System.out.println("2/Tim kiem mot hoc sinh");
			System.out.println("3/Tao moi hoc sinh");
			System.out.println("4/Xoa hoc sinh");
			System.out.println("5/Sua thong tin hoc sinh");
			System.out.println("Nhap chuc nang: ");
			k = sc.nextInt();
			if (k == 0)
				break;
			switch (k) {
			case 1:
				showOnConsole();
				break;
			case 2:
				System.out.println("Nhap ten hoc sinh can tim: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "utf8"));
				String name = br.readLine();
				int index = findOne(name);
				showOneStudent(index);
				break;
			case 3:
				createStudent();
				break;
			case 4:
				deleteStudent();
				break;
			case 5:
				updateStudent();
				break;
			case 0:
				break;
			default:
				System.out.println("Sai code");
				break;
			}
		}
		writeToFile();
	}
}
