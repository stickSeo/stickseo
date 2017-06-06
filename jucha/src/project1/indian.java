package project1;
import java.util.Scanner;


//함수 클래스
class method{


	static String[] year = {"시끄러운, 말 많은", "푸른","어두운 (적색)","조용한","웅크린","백색"
							,"지혜로운","용감한","날카로운","욕심많은"}; 
	static String[] month = {"늑대", "태양","양","매","황소","불꽃","나무","달빛","말","돼지","하늘","바람"}; 
	static String[] day = {"~와(과) 함께 춤을", "~의 기상","~은(는) 그림자 속에","","",""
							,"~의 환생","~의 죽음","~아래에서","~을(를) 보라.","~이(가) 노래하다."
							,"~의 그늘 → 그림자","~의 일격","~에게 쫒기는 남자","~의 행진","~의 왕"
							,"~의 유령","~을 죽인 자.","~은(는) 맨날 잠잔다.","~처럼..","~의 고향"
							,"~의 전사","~은(는) 나의 친구","~의 노래","~의 정령","~의 파수꾼","~의 악마"
							,"~와(과) 같은 사나이","~의 심판자→을(를) 쓰러뜨린 자","~의 혼","~은(는) 말이 없다."}; 


// 출력 함수
public static void print(){

	System.out.println("--------------------------------");
	System.out.println("| 인디언식 이름 짓기 | ");
	System.out.println("--------------------------------");
	System.out.println(" '2015년 7월 24일' 또는 '20150724' 로 적어주세요 ");
	System.out.println(" q를 누르면 <종료> 됩니다. ");
	System.out.println("--------------------------------");
	System.out.print("생년월일 입력 : ");

}

// 인디언 이름짓기 함수
public static void naming(String birth){

	System.out.print("인디언식 이름 : "); 
	System.out.print(year[Integer.valueOf(String.valueOf(birth.charAt(birth.indexOf("년")-1)))]);
	System.out.print(month[Integer.valueOf(birth.substring(birth.indexOf("년")+1, birth.indexOf("월")).trim())-1]);
	System.out.println(day[Integer.valueOf(birth.substring(birth.indexOf("월")+1, birth.indexOf("일")).trim())-1]);

}

public static void numNaming(String birth){

	System.out.print("인디언식 이름 : "); 
	System.out.print(year[Integer.valueOf(String.valueOf(birth.charAt(3)))]);
	System.out.print(month[Integer.valueOf(birth.substring(4, 6))-1]);
	System.out.println(day[Integer.valueOf(birth.substring(6, 8))-1]);

}

/*
* 월, 일 검사
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
		
	}else if(birth.indexOf("년")!=-1 && birth.indexOf("월")!=-1 && birth.indexOf("일")!=-1 )
	{

		int month = Integer.valueOf(birth.substring(birth.indexOf("년")+1, birth.indexOf("월")).trim());
		int day = Integer.valueOf(birth.substring(birth.indexOf("월")+1, birth.indexOf("일")).trim());

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
					System.out.println("월이나 일이 잘 못입력되었습니다.");
					System.out.println("월이나 일이 한자리 수 일 경우에 앞에 0을 기입해주세요. ");
					continue;
				}
			}
		}
	}
}

