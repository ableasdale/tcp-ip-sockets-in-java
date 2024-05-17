public class TCPEchoClient {

    /**
     * From Chapter 2: Basic Sockets - pp. 13-14
     *
     * Note: As the default TCP Echo Port on most systems is on port 7, try:
     * telnet <servername> 7
     */
    public static void main(String[] args) {

        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Parameters: <Server> <Word> [<Port>]");
        }
        String server = args[0];
        byte[] byteBuffer = args[1].getBytes();

        // TBC...

    }
}
