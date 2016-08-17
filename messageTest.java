import java.io.*;
import java.util.ArrayList;
import java.lang.Boolean;
public class messageTest {
	public static void main(String[] args){
		String ip = "10.10.30.235";
		String vip = "10.10.30.234";
		String message = "{\"ip\":\""+ip+"\",\"vip\":\""+vip+"\"}";
		System.out.println(message);
	}
}
