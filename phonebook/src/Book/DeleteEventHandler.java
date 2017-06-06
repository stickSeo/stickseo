package Book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.smardec.mousegestures.test.MouseGestureFrame;
public class DeleteEventHandler implements ActionListener {

    JTextField deleteField;
    JTextArea textArea;
    JButton delbutton;
	int count = 0;
    public DeleteEventHandler(JTextField field, JTextArea area,JButton del) {

        deleteField = field;

        textArea = area;
        
        delbutton = del;

    }

    public void actionPerformed(ActionEvent e) {

        String name = deleteField.getText();

        int data = PhoneBook.method.search(name); // search함수에서 반환 받은 값을 data변수에

        // 저장

        if (data < 0) { // 반환값이 음수일때 if문

            textArea.append("찾으시는 정보가 없습니다. \n");

        } else { // 반환값이 양수일때 else문
        	
        
        	//MouseGestureFrame ges = new MouseGestureFrame();
     


        	if(MouseGestureFrame.count == 0){
        		MouseGestureFrame.main(null);


        		delbutton.setText("실행하기");
        		textArea.append("화면문구를따라서 제스쳐를 그려야합니다.\n");
        		MouseGestureFrame.exit();

        		
        	}
        	if(MouseGestureFrame.count == 1){
        		textArea.append("인증성공! \n 삭제 합니다.");
        		del();
        		delbutton.setText("삭제");
        		MouseGestureFrame.count = 0;
        		MouseGestureFrame.exit();


        	}
    		//MouseGestureFrame.exit();

        	//count++;
        	
        	//del();
     
        	
        	
        }

    }
    
    

    
  public void del(){
    	
    	 String name = deleteField.getText();

         int data = PhoneBook.method.search(name); // search함수에서 반환 받은 값을 data변수에
    	
    	
    	
    	
    	

        for (int i = data; i < PhoneBook.method.value - 1; i++) {

            PhoneBook.method.saveData[i].name = PhoneBook.method.saveData[i + 1].name;

            PhoneBook.method.saveData[i].phoneNumber = PhoneBook.method.saveData[i + 1].phoneNumber;

            PhoneBook.method.saveData[i].email = PhoneBook.method.saveData[i + 1].email;

        }

        PhoneBook.method.value--;

        textArea.append("삭제를 완료했습니다.\n");

        try {

            PhoneBook.method.writeTXT();

        } catch (IOException e1) {

            // TODO Auto-generated catch block

            e1.printStackTrace();

        }
    	
    }
    
}// end DeletehEventHandler class