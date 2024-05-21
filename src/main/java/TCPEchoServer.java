import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServer {

    private static final int BUFSIZE = 32;
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        if(args.length != 1){
            throw new IllegalArgumentException("Parameter(s): <Port>");
        }

        int serverPort = Integer.parseInt(args[0]);
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            LOG.error("Unable to create the TCP Server Socket",e);
            throw new RuntimeException(e);
        }

        int receivedMessageSize;
        byte[] byteBuffer = new byte[BUFSIZE];
        Socket clientSocket;

        for(;;){
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                LOG.error("Unable to create the TCP Client Socket",e);
                throw new RuntimeException(e);
            }
            LOG.info("Handling client connection at: "+clientSocket.getInetAddress() + " on port: "+ clientSocket.getPort());

            InputStream in;
            OutputStream out;

            try {
                in = clientSocket.getInputStream();
                out = clientSocket.getOutputStream();
                while ((receivedMessageSize = in.read(byteBuffer)) != -1) {
                    out.write(byteBuffer, 0, receivedMessageSize);
                }
            } catch (IOException e) {
                LOG.error("Unable to create Socket I/O Streams",e);
                throw new RuntimeException(e);
            }
            try {
                clientSocket.close();
            } catch (IOException e) {
                LOG.error("Unable to close the TCP Client Socket",e);
                throw new RuntimeException(e);
            }

        }

    }
}
