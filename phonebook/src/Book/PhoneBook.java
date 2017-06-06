package Book;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JTextArea;
import javax.swing.JTextField;


class PhoneBook {

    static String name[] = new String[100];
    static String tel[] = new String[100];
    static String mail[] = new String[100];
    static int line; // 줄 수 -> 객체 수
    static Method method = new Method(); // Method 클래스를 사용하기 위한 method 구조체 선언

    public static void main(String[] args) throws IOException {

        // TODO Auto-generated method stub
        int choice; // 메뉴를 선택하기 위한 choice변수 설정
        SwingInterface swing = new SwingInterface();
        FileRead.readTXT();
        method.insertClass();
        Scanner scan = new Scanner(System.in);

        while (true) { // 5번 프로그램을 종료하기 전까지 입력,검색,삭제,출력을 계속 하기위한 무한루프문

            System.out.println("Phone Book");
            System.out.println("1. Input Data");
            System.out.println("2. Search Data");
            System.out.println("3. Delete Data");
            System.out.println("4. Print Data");
            System.out.println("5. EXIT");
            System.out.print("Choice Number :");
            choice = scan.nextInt();

            if (choice > 0 && choice < 6) { // 1~5번 이외의 값을 입력받았을 시 예외처리를 하기 위한

                // if문
                switch (choice) {

                    case 1: // 1번을 입력시 inputData 호출
                        method.inputData();
                        break;
                    case 2:
                        method.searchData(); // 2번을 입력시 searchData 호출
                        break;
                    case 3:
                        method.deleteData(); // 3번을 입력시 deleteData 호출
                        break;
                    case 4:
                        method.showAllData(); // 4번을 입력시 showAllData 호출
                        break;
                    case 5: // 5번을 입력시 종료 함수 호출
                        method.writeTXT();
                        System.out.println("EXIT Program");
                        System.exit(0); // 무한루프를 끝내기 위한 함수
                        swing.dispose();
                }
            } else {
                System.out.println("Wrong Number...please retry...");
            }
        }
    }
}