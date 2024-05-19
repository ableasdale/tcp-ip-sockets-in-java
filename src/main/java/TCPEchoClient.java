import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.net.Socket;
import java.net.SocketException;

/**
 * From Chapter 2: Basic Sockets - pp. 13-14
 * <p>
 * Note: As the default TCP Echo Port on most systems is on port 7, try:
 * telnet <servername> 7
 */
public class TCPEchoClient {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Parameters: <Server> <Word> [<Port>]");
        }
        String server = args[0];
        byte[] byteBuffer = args[1].getBytes();
        int serverPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

        LOG.info("About to attempt to create a TCP socket connection to: "+server+" on TCP port: "+serverPort);
        try {
            Socket socket = new Socket(server, serverPort);
            LOG.info("TCP Socket connection established with server...");
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write(byteBuffer);

            int totalBytesReceived = 0;
            int bytesReceived;

            while(totalBytesReceived < byteBuffer.length){
                if((bytesReceived = in.read(byteBuffer, totalBytesReceived, byteBuffer.length - totalBytesReceived)) == -1){
                    LOG.error("Connection closed prematurely");
                    throw new SocketException();
                }
                totalBytesReceived += bytesReceived;
            }

            LOG.info("Received: "+ new String(byteBuffer));
            socket.close();

        } catch (IOException e) {
            LOG.error("Unable to establish TCP Socket connection", e);
            throw new RuntimeException(e);
        }

    }
}
