package Book;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author LeeYongil
 */


class add implements ActionListener {

 
    public void actionPerformed(ActionEvent e) {

       AddInterface swing2 = new AddInterface();
        
    }


}
class del implements ActionListener {

	 
    public void actionPerformed(ActionEvent e) {

        DelInterface swing3 = new DelInterface();
    }


}
class search implements ActionListener {

	 
    public void actionPerformed(ActionEvent e) {

        SearchInterface swing4 = new SearchInterface();
    }


}
class allsearch implements ActionListener {

	 
    public void actionPerformed(ActionEvent e) {

    	AllSearchInterface swing5 = new AllSearchInterface();
    }


}
class update implements ActionListener {

	 
    public void actionPerformed(ActionEvent e) {

    	UpdateInterface swing6 = new UpdateInterface();
    }


}

class Information { // �낅젰諛쏆쓣 蹂�닔瑜��ㅼ젙�섍퀬 異쒕젰���꾪븳 �⑥닔

    String name;
    String phoneNumber;
    String email;

    public Information(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    } // End p.Information

    public void showData() { // 異쒕젰�섎뒗 媛믪쓣 蹂댁뿬二��꾪븳 �⑥닔
        if (name != null) // ��젣�⑥닔�먯꽌 吏�슦��諛⑹떇��""濡�珥덇린���섍린 �뚮Ц��""遺�텇��異쒕젰���덊븯湲��꾪븳 if臾�
        {
            System.out.println("Name : " + name);
        }
        if (phoneNumber != null) {
            System.out.println("Number : " + phoneNumber);
        }
        if (email != null) {
            System.out.println("E-Mail : " + email);
        }

        System.out.println("\n");
    } // End showData
} // End Information

class Method { // �낅젰, 寃�깋, ��젣 , �꾩껜異쒕젰���⑥닔�ㅼ쓣 紐⑥븘���대옒��

    final int MAX = 100; // ��옣�섎뒗 諛곗뿴��100媛쒕줈 �ㅼ젙.
    Information[] saveData = new Information[MAX]; // �대쫫, 踰덊샇, �대찓�쇱쓽 �뺣낫瑜�媛��怨��덉쓣
    int value = 0;
    Scanner scan = new Scanner(System.in); // Scanner�⑥닔瑜��곌린 �꾪븳 �ㅼ젙

    public void inputData() { // �대쫫, 踰덊샇, �대찓�쇱쓣 �낅젰諛쏅뒗 �⑥닔

        System.out.println("Input Dat");
        System.out.print("Name : ");
        String name = scan.nextLine();
        System.out.print("Number : ");
        String phone = scan.nextLine();
        System.out.print("E-Mail : ");
        String email = scan.nextLine();

        saveData[value] = new Information(name, phone, email); // value媛믪씠 吏�젙��

        // saveData���뺣낫瑜�

        // �섑��닿린 �꾪븳 蹂�닔 �ㅼ젙

        value++; // �쒖감�곸쑝濡��뺣낫瑜���옣�섍린 �꾪븳 value++

        System.out.println("Complete Input Data...\n");

    } // End inputData

    public void searchData() { // �대쫫���낅젰諛쏆븘 data��媛믪씠 0�몄�, �뚯닔�몄����곕씪 異쒕젰�섎뒗 �⑥닔

        System.out.println("Search Data");

        System.out.print("Name : ");

        String name = scan.nextLine();

        int data = search(name); // search�⑥닔�먯꽌 諛섑솚 諛쏆� 媛믪쓣 data蹂�닔����옣

        if (data < 0) { // 諛섑솚媛믪씠 �뚯닔�쇰븣 if臾�

            System.out.println("No Data.\n");

        } else { // 諛섑솚媛믪씠 �묒닔�쇰븣 else臾�

            saveData[data].showData(); // search�⑥닔�먯꽌 諛섑솚 諛쏆� �대떦 data�꾩튂��媛믪쓣

            // showData�⑥닔�먯꽌 異쒕젰��

            System.out.println("Complete Search Data... \n");

        }

    } // End searchData

    public void deleteData() { // �대쫫���낅젰諛쏆븘 data��媛믪씠 0�몄�, �뚯닔�몄����곕씪 data瑜�吏�슦���⑥닔

        System.out.println("Delete Data");

        System.out.print("Name : ");

        String name = scan.nextLine();

        int data = search(name);

        if (data < 0) {
            System.out.println("No Data \n");
        } else {

            for (int i = data; i < value - 1; i++) {

                saveData[i].name = saveData[i + 1].name;

                saveData[i].phoneNumber = saveData[i + 1].phoneNumber;

                saveData[i].email = saveData[i + 1].email;

            }

            value--;

            System.out.println("Complete Delete Data... \n");

        }

    } // End delteData

    public int search(String name) { // �낅젰諛쏆� �대쫫怨���옣�섏뼱 �덈뒗 �대쫫��媛숈�吏��ㅻⅨ吏�� 援щ텇�섎뒗 �⑥닔

        for (int i = 0; i <= value - 1; i++) { // �꾩옱 ��옣�섏뼱�덈뒗 踰덊샇源뚯� for臾��ㅽ뻾

            if (name.equals(saveData[i].name) == true) { // �낅젰諛쏆� �대쫫怨�temp����옣�섏뼱 �덈뒗 媛믪쓣 鍮꾧탳�섏뿬 媛숈쑝硫�0��諛섑솚, �꾨땲硫�-1��諛섑솚

                return i; // 諛섑솚媛믪씠 �대떦 媛믪쓽 諛곗뿴 踰덊샇�ъ빞 �섍린 �뚮Ц��諛섑솚媛믪� i

            }
        }
        return -1;
    }

    public void insertClass() {

        for (int i = 0; i < PhoneBook.line; i++) {

            saveData[value] = new Information(PhoneBook.name[i],
                    PhoneBook.tel[i], PhoneBook.mail[i]); // value媛믪씠 吏�젙��

            value++; // �쒖감�곸쑝濡��뺣낫瑜���옣�섍린 �꾪븳 value++

        }

    }

    public void showAllData() { // �꾩옱 ��옣�섏뼱 �덈뒗 媛믩뱾��紐⑤몢 異쒕젰�섎뒗 �⑥닔

        for (int i = 0; i < value; i++) {

            if (saveData[i].name != null) // delete臾몄뿉����옣���대떦 踰덊샇瑜�蹂댁씠吏��딄린�꾪븳 if臾�
            {
                System.out.println((i + 1) + ". "); // 蹂대뒗 踰덊샇��1遺�꽣 �쒖옉�대�濡�i+1
            }
            saveData[i].showData();

        }

    }

    public void writeTXT() throws IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));

        int lineNum = value * 3;

        out.write(lineNum + "\n");

        for (int i = 0; i < value; i++) {

            out.write(saveData[i].name);

            out.newLine();

            out.write(saveData[i].phoneNumber);

            out.newLine();

            out.write(saveData[i].email);

            out.newLine();

        }

        out.close();

    }
}// end Method class

class SearchEventHandler implements ActionListener {

    JTextField searchField;
    JTextArea textArea;

    public SearchEventHandler(JTextField field, JTextArea area) {

        searchField = field;

        textArea = area;

    }

    public void actionPerformed(ActionEvent e) {

        String name1 = searchField.getText();

        // int data = PhoneBook.method.search(name); // search�⑥닔�먯꽌 諛섑솚 諛쏆� 媛믪쓣

        // data蹂�닔��

        // ��옣

        int count = 0;

        for (int i = 0; i < PhoneBook.method.value; i++) {

            if (name1.equals(PhoneBook.method.saveData[i].name)) {

                count++;

                textArea.append("이름 : " + PhoneBook.method.saveData[i].name
                        + "\n");

                textArea.append("전화번호 : "
                        + PhoneBook.method.saveData[i].phoneNumber + "\n");

                textArea.append("email : " + PhoneBook.method.saveData[i].email
                        + "\n\n");

            }

        }

        if (count == 0) {
            textArea.append("해당하는 사람이 없습니다.\n");
        }

    }
}// end SearchEventHandler class



class AddEventHandler implements ActionListener {
    

    JTextField nameField;
    JTextField telField;
    JTextField mailField;
    JTextArea textArea;

    public AddEventHandler(JTextField field1, JTextField field2,
            JTextField field3, JTextArea area) {

        nameField = field1;

        telField = field2;

        mailField = field3;

        textArea = area;

    }

    public void actionPerformed(ActionEvent e) {

        String name = nameField.getText();

        String tel = telField.getText();

        String mail = mailField.getText();

        if (name.equals("")) {

            textArea.append("이름을 입력하세요\n");

        } else {

            PhoneBook.method.saveData[PhoneBook.method.value] = new Information(
                    name, tel, mail); // value媛믪씠 吏�젙��

          

            PhoneBook.method.value++; // �쒖감�곸쑝濡��뺣낫瑜���옣�섍린 �꾪븳 value++

            textArea.append("저장완료\n");
           
        }

        try {

            PhoneBook.method.writeTXT();

        } catch (IOException e1) {

            e1.printStackTrace();

        }

    }
}// end AddEventHandler class

class ShowListEventHandler implements ActionListener {

    JTextArea textArea;

    public ShowListEventHandler(JTextArea area) {
        textArea = area;
    }

    public void actionPerformed(ActionEvent e) {

        textArea.append("珥�" + PhoneBook.method.value + "紐낆쓽 �꾪솕遺�� ��옣�섏뼱 �덉뒿�덈떎.\n");
        textArea.append("  �대쫫  \t|" + "                     �꾪솕踰덊샇  \t|\t �대찓��\n");
        textArea.append("\t|\t\t|\n");
        for (int i = 0; i < PhoneBook.method.value; i++) {
            textArea.append((i + 1) + ". " + PhoneBook.method.saveData[i].name+ "\t|");
            textArea.append("            "+ PhoneBook.method.saveData[i].phoneNumber + "            "+ "\t|");
            textArea.append("            " + PhoneBook.method.saveData[i].email+ "\n");
            textArea.append("\t|\t\t|\n");
        }// end for

    }
}// end ShowListEventHandler class

// end ShowListEventHandler class
