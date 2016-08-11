import java.io.*;
import java.lang.InterruptedException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
//import java.lang.StringBuffer;
public class daemonTest {
	public static void main(String args[]){
	
		String ip = "10.10.30.235";
		String vip = "10.10.30.234";
		ArrayList<String> params = new ArrayList<String>();
		params.add("bash");
		params.add("/tmp/Api-Invokers/writer");
		params.add(ip+" "+vip);
		params.add("/tmp/cluster.txt");
		System.out.println(Arrays.toString(params.toArray()));
		System.out.println(runCommand(params));
		
		try{
			Thread.sleep(20000);
		} catch(InterruptedException I){
			System.out.println("I");
		}
		params.clear();
		params.add("sudo");
		params.add("rm");
		params.add("/tmp/cluster.txt");
		System.out.println(Arrays.toString(params.toArray()));
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
	
			URL url = new URL("http://10.10.30.235:2375/containers/console/exec");
		        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		        conn.setDoOutput(true);
                	conn.setRequestMethod( "POST" );
	                conn.addRequestProperty("Content-Type", "application/json"); 
		        OutputStream os = conn.getOutputStream();
	        	OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			String message = "{\"AttachStdin\": false, \"AttachStdout\": true, \"AttachStderr\": true, \"Tty\": false, \"Cmd\": [ ";
			for (int i = 0;i<params.size()-1;i++){
				message = message + "\"" + params.get(i) + "\",";
			}
			message = message + "\"" + params.get(params.size()-1) + "\" ]}";
			osw.write(message);
			osw.flush();
			osw.close();
			System.out.println(message);
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
		
		} catch (IOException e){
			System.out.println("error");
		}
		return (sb_start.toString());

	}

}
