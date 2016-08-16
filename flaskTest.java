import java.io.*;
import java.lang.InterruptedException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
public class flaskTest {
	public static void main(String args[]){
		String ip = "10.10.30.235";
		String vip = "10.10.30.234";
		ArrayList<String> params = new ArrayList<String>();
		System.out.println(runCommand(params));
							
	}
	public static StringBuilder getStringBuilder(HttpURLConnection con){
		StringBuilder sb = new StringBuilder();
		try{

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null){
				sb.append(line+"\n");
			}
			br.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		return sb;
	}
	public static String runCommand(ArrayList params){
		StringBuilder sb_start = null;
		try{
	
			URL url = new URL("http://localhost:5000/cluster");
		        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		        conn.setDoOutput(true);
                	conn.setRequestMethod( "POST" );
	                //conn.addRequestProperty("Content-Type", "application/json"); 
		        OutputStream os = conn.getOutputStream();
	        	OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			String message = "{\"ip\":\"10.10.30.235\",\"vip\":\"10.10.30.234\"}";
			System.out.println(message);
			osw.write(message);
			osw.flush();
			osw.close();
			/*
			StringBuilder sb = getStringBuilder(conn);
			String ID = sb.toString().substring(7,sb.toString().length()-3);
			URL url_start = new URL("http://10.10.30.235:2375/exec/"+ID+"/start");
			HttpURLConnection conn_start = (HttpURLConnection)url_start.openConnection();
			conn_start.setDoOutput(true);
                	conn_start.setRequestMethod( "POST" );
        	        conn_start.addRequestProperty("Content-Type", "application/json");
	                OutputStream os_start = conn_start.getOutputStream();
                	OutputStreamWriter osw_start = new OutputStreamWriter(os_start, "UTF-8");
        	        osw_start.write("{\"Detach\": false, \"Tty\": true}");
	                osw_start.flush();
                	osw_start.close();
			System.out.println(conn_start.getResponseCode());
			sb_start = getStringBuilder(conn_start);
			*/
		} catch (IOException e){
			e.getStackTrace();
			System.out.println("error");
		}
		return "Hello";
		//return (sb_start.toString());

	}

}
