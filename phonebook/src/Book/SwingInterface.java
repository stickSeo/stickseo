package Book;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author LeeYongil
 */

// Frame - 5개
// Main(SwingInterface), ADDInterface, DELETEInterface, SEARCHInterface, ALLSearchInterface

class SwingInterface extends JFrame {

    JButton srchdelBtn = new JButton("검색 & 삭제");
    JButton addBtn = new JButton("추가");
    JButton listBtn = new JButton("전체보기");
    JButton updateBtn = new JButton("수정");


    JTextArea textArea = new JTextArea(20, 25);

    public SwingInterface() {

        super();
        setBounds(350, 50, 540, 550);
        setLayout(new BorderLayout());
        Border border = BorderFactory.createEtchedBorder();
        Border srchBorder = BorderFactory.createTitledBorder(border, "Main");
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(srchBorder);
        mainPanel.setLayout(new GridLayout(5,1,1,1));

        mainPanel.add(srchdelBtn);
        mainPanel.add(addBtn);
        mainPanel.add(updateBtn);
        mainPanel.add(listBtn);

    
        add(mainPanel, BorderLayout.CENTER);
        

        srchdelBtn.addActionListener(new del());
        addBtn.addActionListener(new add());
        updateBtn.addActionListener(new update());
        listBtn.addActionListener(new allsearch());
        listBtn.addActionListener(new ShowListEventHandler(textArea));
      
        setVisible(true);
    }

    
    
    
    
    
    
    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.dispose();
    }
}

class AddInterface extends JFrame {

   
    JButton addBtn = new JButton("추가"); 
    JTextField addNameField = new JTextField(12);
    JTextField addTelField = new JTextField(12);
    JTextField addMailField = new JTextField(12);
    JTextArea addNameArea = new JTextArea("이름");
    JTextArea addNumArea = new JTextArea("번호");
    JTextArea addEmailArea = new JTextArea("메일");
    JTextArea textArea = new JTextArea(10, 15);

    public AddInterface() {

        super();
        setBounds(350, 50, 540, 300);
        setLayout(new BorderLayout());
        Border border = BorderFactory.createEtchedBorder();
        
        Border addBorder = BorderFactory.createTitledBorder(border, "Add");

        JPanel addPanel = new JPanel();

        addPanel.setBorder(addBorder);
        addPanel.setLayout(new FlowLayout());
        addPanel.setSize(0, 300);
        addPanel.add(addNameArea);
        addPanel.add(addNameField);
        addPanel.add(addNumArea);
        addPanel.add(addTelField);
        addPanel.add(addEmailArea);
        addPanel.add(addMailField);
        addPanel.add(addBtn);

   

        JScrollPane scrollTextArea = new JScrollPane(textArea);
        Border textBorder = BorderFactory.createTitledBorder(border, "Infomation board");
        scrollTextArea.setBorder(textBorder);
    
        add(addPanel, BorderLayout.CENTER);
        add(scrollTextArea, BorderLayout.NORTH);


        addBtn.addActionListener(new AddEventHandler(addNameField, addTelField, addMailField, textArea));

        setVisible(true);
    } 
    
    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.dispose();
    }
}

class DelInterface extends JFrame {

    JButton srchBtn = new JButton("검색");
    JButton delBtn = new JButton("삭제");
    JTextField addNameField = new JTextField(12);
    JTextField addTelField = new JTextField(12);
    JTextField addMailField = new JTextField(12);
    JTextField srchField = new JTextField(15);
    JTextArea addNameArea = new JTextArea("이름");
    JTextArea addNumArea = new JTextArea("번호");
    JTextArea addEmailArea = new JTextArea("메일");
    JTextArea textArea = new JTextArea(20, 25);

    public DelInterface() {

        super();
        setBounds(350, 50, 540, 650);
        setLayout(new BorderLayout());
        Border border = BorderFactory.createEtchedBorder();
        Border srchBorder = BorderFactory.createTitledBorder(border, "Search & Delete");
        JPanel srchPanel = new JPanel();
        srchPanel.setBorder(srchBorder);
        srchPanel.setLayout(new FlowLayout());

        srchPanel.add(srchField);
        srchPanel.add(srchBtn);
        srchPanel.add(delBtn);


        JScrollPane scrollTextArea = new JScrollPane(textArea);
        Border textBorder = BorderFactory.createTitledBorder(border, "Infomation board");

        scrollTextArea.setBorder(textBorder);
        add(srchPanel, BorderLayout.CENTER);
        add(scrollTextArea, BorderLayout.NORTH);

        srchBtn.addActionListener(new SearchEventHandler(srchField, textArea));
        delBtn.addActionListener(new DeleteEventHandler(srchField, textArea,delBtn));

        setVisible(true);
    }

    
    
    
    
    
    
    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.dispose();
    }
}

class SearchInterface extends JFrame {

    JButton srchBtn = new JButton("검색");
    JButton delBtn = new JButton("삭제");
    JButton addBtn = new JButton("ADD");
    JButton listBtn = new JButton("전체보기");
    JTextField addNameField = new JTextField(12);
    JTextField addTelField = new JTextField(12);
    JTextField addMailField = new JTextField(12);
    JTextField srchField = new JTextField(15);
    JTextArea addNameArea = new JTextArea("이름");
    JTextArea addNumArea = new JTextArea("번호");
    JTextArea addEmailArea = new JTextArea("메일");
    JTextArea textArea = new JTextArea(20, 25);

    public SearchInterface() {

        super();
        setBounds(350, 50, 540, 650);
        setLayout(new BorderLayout());
        Border border = BorderFactory.createEtchedBorder();
        Border srchBorder = BorderFactory.createTitledBorder(border, "Search & Delete");
        JPanel srchPanel = new JPanel();
        srchPanel.setBorder(srchBorder);
        srchPanel.setLayout(new FlowLayout());

        srchPanel.add(srchField);
        srchPanel.add(srchBtn);
        srchPanel.add(delBtn);

        Border addBorder = BorderFactory.createTitledBorder(border, "Add");

        JPanel addPanel = new JPanel();

        addPanel.setBorder(addBorder);
        addPanel.setLayout(new FlowLayout());
        addPanel.setSize(0, 300);
        addPanel.add(addNameArea);
        addPanel.add(addNameField);
        addPanel.add(addNumArea);
        addPanel.add(addTelField);
        addPanel.add(addEmailArea);
        addPanel.add(addMailField);
        addPanel.add(addBtn);

        Border delBorder = BorderFactory.createTitledBorder(border, "Show List");
        JPanel delPanel = new JPanel();
        delPanel.setBorder(delBorder);
        delPanel.setLayout(new FlowLayout());
        delPanel.add(listBtn);

        JScrollPane scrollTextArea = new JScrollPane(textArea);
        Border textBorder = BorderFactory.createTitledBorder(border, "Infomation board");

        scrollTextArea.setBorder(textBorder);
        add(srchPanel, BorderLayout.WEST);
        add(addPanel, BorderLayout.CENTER);
        add(delPanel, BorderLayout.SOUTH);
        add(scrollTextArea, BorderLayout.NORTH);

        srchBtn.addActionListener(new SearchEventHandler(srchField, textArea));
        addBtn.addActionListener(new AddEventHandler(addNameField, addTelField, addMailField, textArea));
        delBtn.addActionListener(new DeleteEventHandler(srchField, textArea,delBtn));
        listBtn.addActionListener(new ShowListEventHandler(textArea));

        setVisible(true);
    }

    
    
    
    
    
    
    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.dispose();
    }
}

class AllSearchInterface extends JFrame {

  
    JButton listBtn = new JButton("전체보기");
    JTextField addNameField = new JTextField(12);
    JTextField addTelField = new JTextField(12);
    JTextField addMailField = new JTextField(12);
    JTextField srchField = new JTextField(15);
    JTextArea addNameArea = new JTextArea("이름");
    JTextArea addNumArea = new JTextArea("번호");
    JTextArea addEmailArea = new JTextArea("메일");
    JTextArea textArea = new JTextArea(20, 25);

    public AllSearchInterface() {

        super();
        setBounds(350, 50, 540, 650);
        setLayout(new BorderLayout());
        Border border = BorderFactory.createEtchedBorder();
      
   

        Border delBorder = BorderFactory.createTitledBorder(border, "Show List");
        JPanel delPanel = new JPanel();
        delPanel.setBorder(delBorder);
        delPanel.setLayout(new FlowLayout());
        delPanel.add(listBtn);

        JScrollPane scrollTextArea = new JScrollPane(textArea);
        Border textBorder = BorderFactory.createTitledBorder(border, "Infomation board");

        scrollTextArea.setBorder(textBorder);
        add(delPanel, BorderLayout.SOUTH);
        add(scrollTextArea, BorderLayout.NORTH);

        listBtn.addActionListener(new ShowListEventHandler(textArea));

        setVisible(true);
    }

    
    
    
    
    
    
    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.dispose();
    }
}
class UpdateInterface extends JFrame {

    JButton srchBtn = new JButton("검색");
    JButton delBtn = new JButton("삭제");
    JButton addBtn = new JButton("ADD");
    JButton listBtn = new JButton("전체보기");
    JTextField addNameField = new JTextField(12);
    JTextField addTelField = new JTextField(12);
    JTextField addMailField = new JTextField(12);
    JTextField srchField = new JTextField(15);
    JTextArea addNameArea = new JTextArea("이름");
    JTextArea addNumArea = new JTextArea("번호");
    JTextArea addEmailArea = new JTextArea("메일");
    JTextArea textArea = new JTextArea(20, 25);

    public UpdateInterface() {

        super();
        setBounds(350, 50, 540, 650);
        setLayout(new BorderLayout());
        Border border = BorderFactory.createEtchedBorder();
        Border srchBorder = BorderFactory.createTitledBorder(border, "Search & Delete");
        JPanel srchPanel = new JPanel();
        srchPanel.setBorder(srchBorder);
        srchPanel.setLayout(new FlowLayout());

        srchPanel.add(srchField);
        srchPanel.add(srchBtn);
        srchPanel.add(delBtn);

        Border addBorder = BorderFactory.createTitledBorder(border, "Add");

        JPanel addPanel = new JPanel();

        addPanel.setBorder(addBorder);
        addPanel.setLayout(new FlowLayout());
        addPanel.setSize(0, 300);
        addPanel.add(addNameArea);
        addPanel.add(addNameField);
        addPanel.add(addNumArea);
        addPanel.add(addTelField);
        addPanel.add(addEmailArea);
        addPanel.add(addMailField);
        addPanel.add(addBtn);

        Border delBorder = BorderFactory.createTitledBorder(border, "Show List");
        JPanel delPanel = new JPanel();
        delPanel.setBorder(delBorder);
        delPanel.setLayout(new FlowLayout());
        delPanel.add(listBtn);

        JScrollPane scrollTextArea = new JScrollPane(textArea);
        Border textBorder = BorderFactory.createTitledBorder(border, "Infomation board");

        scrollTextArea.setBorder(textBorder);
        add(srchPanel, BorderLayout.WEST);
        add(addPanel, BorderLayout.CENTER);
        add(delPanel, BorderLayout.SOUTH);
        add(scrollTextArea, BorderLayout.NORTH);

        srchBtn.addActionListener(new SearchEventHandler(srchField, textArea));
        addBtn.addActionListener(new AddEventHandler(addNameField, addTelField, addMailField, textArea));
        delBtn.addActionListener(new DeleteEventHandler(srchField, textArea,delBtn));
        listBtn.addActionListener(new ShowListEventHandler(textArea));

        setVisible(true);
    }

    
    
    
    
    
    
    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.dispose();
    }




}

