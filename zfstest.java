import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.InetAddress;
//import java.lang.StringBuffer;
public class zfstest {
	public static void main(String args[]){
            try{

                URL url_id = new URL("http://10.10.30.235:2375/containers/console/exec");
                HttpURLConnection conn_id = (HttpURLConnection)url_id.openConnection();

                conn_id.setDoOutput(true);
                conn_id.setRequestMethod( "POST" );
                conn_id.addRequestProperty("Content-Type", "application/json");
                OutputStream os = conn_id.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                //String vip = this.getVipAddress();
                //String nodeIPs = this.getNodeIPs();
                String name = "ZFSPoolTest";
                String node_list = "10.10.30.235";
                String message = "{\"AttachStdin\": false, \"AttachStdout\": true, \"AttachStderr\": true, \"Tty\": false, \"Cmd\": [ \"python\", \"/tmp/Api-Invokers/createZFSPool.py\", \""+name+"\", \""+node_list+"\" ]}";
                System.out.println(message);
                osw.write(message);
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

                File file = new File("/RuntimeCalls/zfs.txt");
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(name+"\n");
                bw.write(node_list+"\n");
                bw.write(message+"\n");
                bw.write(sb_start.toString());
                bw.close();

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
