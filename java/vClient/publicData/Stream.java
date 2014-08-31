package publicData;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PushbackReader;
import java.nio.CharBuffer;

/*
 * Created on 2013-9-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author C5134
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Stream {
	FileWriter fw ;
	String path = System.getProperty("user.dir");
	public void StreamWrite(String id ,String pwd) {
		try {
			fw = new FileWriter(path+"/aa.txt");
			// fw = new FileWriter("aa.txt");
			fw.write( incode(id)+"\r");
			fw.write( incode(pwd)+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fw!=null)
				try {
					fw.close();
				} catch (IOException e1) {
				
					e1.printStackTrace();
				}
		}
	
	}
	public String[] StreamReader() {
		FileReader fr =null;
		PushbackReader pr = null;
		String str = null;
		String id = null;
		String pwd = null;
		String [] ss =null;
		try {
			fr = new FileReader(path+"/aa.txt");
			//fr = new FileReader("aa.txt");
			pr = new PushbackReader(fr,128);
			char[] b = new char[36];
			char[] buffer = new char[128];
			String lastContent ="";
			int hasRead = 0;
			
			while((hasRead = fr.read(b))>0){
				String content = new String(b,0,hasRead);
				int targetIndex =0;
				int m = 0;
				if((targetIndex = (lastContent + content).indexOf("\n"))>0)
				{
					pr.unread((lastContent + content).toCharArray());
					pr.read(b,0,targetIndex);
						str= new String(b,0,targetIndex);
						
				   
				}
				else
				{
					lastContent = content;
				}
			}
			if(str!=null){
			id = str.substring(0,str.indexOf("\r"));
			pwd = str.substring(str.indexOf("\r")+1,str.length());
			//System.out.println(id);
 			System.out.println(decode(id));
			System.out.println(decode(pwd));
			ss = new  String[]{decode(id),decode(pwd)};
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(fr!=null){
				try {
					fr.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		return ss;
		
		
	}
	private String incode(String str){
		char [] c = str.toCharArray();
		 for(int i=0;i<c.length;i++){
		 	if(i==2)
		 		continue;
		 	c[i] = (char) (c[i]+i+2);
		 	
		 }
		 return new String(c);
	}
	private String decode(String str){
		char [] c = str.toCharArray();
		for(int i =0;i<c.length;i++){
			if(i==2)
				continue;
			c[i]= (char) (c[i]-i-2);
		}
		return new String(c);
	}
	public static void main(String args[]){
		Stream s =new Stream(); 
	
			s.StreamWrite("213110337","200012");
			s.StreamReader();
		
	}

}
