import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.InetAddress;
public class test {
	public static void main(String args[]){
		try{
			
			URL url = new URL("http://172.0.0.1:2375/info/");
			//URL url = new URL("http://localhost:23");
	                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
        	        conn.setRequestMethod( "POST" );
                	//conn.addRequestProperty("Content-Type", "application/json");
	                //conn.connect();
        	        //byte[] outputBytes = "{\"AttachStdin\": false, \"AttachStdout\": true, \"AttachStderr\": true, \"Tty\": false, \"Cmd\": [ \"ls\",\"-al\"]}".getBytes("UTF-8");
                	//OutputStream os = conn.getOutputStream();
	                //os.write(outputBytes);
                	//os.close();
			InputStream is = conn.getInputStream();
			System.out.println(getStringFromInputStream(is));
			is.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	public static String getStringFromInputStream(InputStream is){
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;

		try{
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null){
				sb.append(line);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		} finally {
			if (br != null){
				try {
					br.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
