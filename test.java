import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.InetAddress;
//import java.lang.StringBuffer;
public class test {
	public static void main(String args[]){
		try{
			
			URL url_id = new URL("http://10.10.30.235:2375/containers/console/exec");
	                HttpURLConnection conn_id = (HttpURLConnection)url_id.openConnection();
			conn_id.setDoOutput(true);
        	        conn_id.setRequestMethod( "POST" );
                	conn_id.addRequestProperty("Content-Type", "application/json"); 
			OutputStream os = conn_id.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        	        //osw.write("{\"AttachStdin\": false, \"AttachStdout\": true, \"AttachStderr\": true, \"Tty\": false, \"Cmd\": [ \"ls\",\"-al\"]}");
        	        osw.write("{\"AttachStdin\": false, \"AttachStdout\": true, \"AttachStderr\": true, \"Tty\": false, \"Cmd\": [ \"python\", \"/tmp/Api-Invokers/createCluster.py\", \"10.10.30.235\", \"10.10.30.234\" ]}");
			osw.flush();
			osw.close();
					
			StringBuilder sb = getStringBuilder(conn_id);
			String ID = sb.toString().substring(7,sb.toString().length()-3);
			System.out.println(ID);
			
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
			StringBuilder sb_start = getStringBuilder(conn_start);
			System.out.println(sb_start.toString());

		}catch(IOException e){
			System.out.println("ERROR1");
			e.printStackTrace();
		}
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

}
