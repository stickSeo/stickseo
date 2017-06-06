package project1;
import java.util.Scanner;


//�Լ� Ŭ����
class method{


	static String[] year = {"�ò�����, �� ����", "Ǫ��","��ο� (����)","������","��ũ��","���"
							,"�����ο�","�밨��","��ī�ο�","��ɸ���"}; 
	static String[] month = {"����", "�¾�","��","��","Ȳ��","�Ҳ�","����","�޺�","��","����","�ϴ�","�ٶ�"}; 
	static String[] day = {"~��(��) �Բ� ����", "~�� ���","~��(��) �׸��� �ӿ�","","",""
							,"~�� ȯ��","~�� ����","~�Ʒ�����","~��(��) ����.","~��(��) �뷡�ϴ�."
							,"~�� �״� �� �׸���","~�� �ϰ�","~���� �i��� ����","~�� ����","~�� ��"
							,"~�� ����","~�� ���� ��.","~��(��) �ǳ� ���ܴ�.","~ó��..","~�� ����"
							,"~�� ����","~��(��) ���� ģ��","~�� �뷡","~�� ����","~�� �ļ���","~�� �Ǹ�"
							,"~��(��) ���� �糪��","~�� �����ڡ���(��) �����߸� ��","~�� ȥ","~��(��) ���� ����."}; 


// ��� �Լ�
public static void print(){

	System.out.println("--------------------------------");
	System.out.println("| �ε��� �̸� ���� | ");
	System.out.println("--------------------------------");
	System.out.println(" '2015�� 7�� 24��' �Ǵ� '20150724' �� �����ּ��� ");
	System.out.println(" q�� ������ <����> �˴ϴ�. ");
	System.out.println("--------------------------------");
	System.out.print("������� �Է� : ");

}

// �ε�� �̸����� �Լ�
public static void naming(String birth){

	System.out.print("�ε��� �̸� : "); 
	System.out.print(year[Integer.valueOf(String.valueOf(birth.charAt(birth.indexOf("��")-1)))]);
	System.out.print(month[Integer.valueOf(birth.substring(birth.indexOf("��")+1, birth.indexOf("��")).trim())-1]);
	System.out.println(day[Integer.valueOf(birth.substring(birth.indexOf("��")+1, birth.indexOf("��")).trim())-1]);

}

public static void numNaming(String birth){

	System.out.print("�ε��� �̸� : "); 
	System.out.print(year[Integer.valueOf(String.valueOf(birth.charAt(3)))]);
	System.out.print(month[Integer.valueOf(birth.substring(4, 6))-1]);
	System.out.println(day[Integer.valueOf(birth.substring(6, 8))-1]);

}

/*
* ��, �� �˻�
* 
*/
public static boolean errorCheck(String birth){

	boolean check = false;
	boolean monthCheck = false;
	boolean dayCheck = false;

	if(birth.length()<8){
		check = false;
	}else if(birth.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"))
	{

		int month = Integer.valueOf(birth.substring(4, 6));
		int day = Integer.valueOf(birth.substring(6, 8));
		if(month>=1 && month<=12){
		monthCheck = true;
		}
		if(day>=1 && day <= 31){
			dayCheck = true;
		}
		if(monthCheck==true&&dayCheck==true){
			check = true;
		}
		
	}else if(birth.indexOf("��")!=-1 && birth.indexOf("��")!=-1 && birth.indexOf("��")!=-1 )
	{

		int month = Integer.valueOf(birth.substring(birth.indexOf("��")+1, birth.indexOf("��")).trim());
		int day = Integer.valueOf(birth.substring(birth.indexOf("��")+1, birth.indexOf("��")).trim());

		if(month>=1 && month<=12){
			monthCheck = true;
		}
		if(day>=1 && day <= 31){
			dayCheck = true;
		}
		if(monthCheck==true&&dayCheck==true){
			check = true;
		}
	}
	return check;
}

}

public class indian {

	public static void main(String[] args) {
	
		while(true){
		
			Scanner s = new Scanner(System.in);
			method.print();
			String birth = null;
		
			try{
			birth=s.nextLine();
			}catch(Exception e){
			e.getMessage();
			}
			if(birth.equals("q")){
				System.exit(0);
			}else{
				if(method.errorCheck(birth)){
					if(birth.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")){
						method.numNaming(birth);
					}else{
						method.naming(birth);
					}
				}else{
					System.out.println("���̳� ���� �� ���ԷµǾ����ϴ�.");
					System.out.println("���̳� ���� ���ڸ� �� �� ��쿡 �տ� 0�� �������ּ���. ");
					continue;
				}
			}
		}
	}
}

