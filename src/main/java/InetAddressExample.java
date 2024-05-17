import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * From Chapter 2: Basic Sockets - pp. 9-10
 */
public class InetAddressExample {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        try {
            InetAddress address = InetAddress.getLocalHost();
            LOG.info("Local Host:");
            LOG.info("Host Name\t"+ address.getHostName());
            LOG.info("Host Address\t"+ address.getHostAddress());
            //LOG.info("\t"+ address.getCanonicalHostName());
            LOG.info("Multicast?\t"+ address.isMulticastAddress());
            //LOG.info("Reachable?\t"+ address.isReachable());
        } catch (UnknownHostException e) {
            LOG.error("Unable to determine this host's address",e);
            throw new RuntimeException(e);
        }

        for (int i = 0; i < args.length; i++){
            try {
                InetAddress[] addressList = InetAddress.getAllByName(args[i]);
                LOG.info(args[i]+":");
                LOG.info("\t"+addressList[0].getHostName());
                for (int j = 0; j < addressList.length; j++){
                    LOG.info("\t"+addressList[j].getHostAddress());
                }
            } catch (UnknownHostException e) {
                LOG.error("Unable to find address for: "+args[i],e);
                throw new RuntimeException(e);
            }
        }
    }
}
