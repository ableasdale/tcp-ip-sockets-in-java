# tcp-ip-sockets-in-java

My follow-on code from reading the TCP/IP Sockets in Java book

## InetAddressExample

A simple example demonstrating the creation of an `InetAddress` and retrieving the metadata around that connection.

A single argument can be provided with an IP address or a DNS resolvable name.

Example use:

```bash
gradle inetaddress --args="192.168.1.1"
gradle inetaddress --args="www.google.com"
```

## TCPEchoClient

Demonstrating a basic TCP echo client using a TCP Socket connection (on port 7 unless specified).

In order for this to work, the client needs to connect to a server; you can do this easily by running the following in a terminal window:

```bash
socat -v tcp-l:7,fork exec:'/bin/cat'
```

You can also use `netcat` to create the server for this (installed as part of the `nmap` package:

```bash
ncat -e /bin/cat -k -l 7
```

To test the server from the command-line - in a separate terminal session, run:

```bash
echo hello | netcat localhost 7
```

To run the example:

```bash
gradle tcpecho --args="localhost hello"
```


## Troubleshooting

```bash
lsof -i 4tcp:8080 -sTCP:LISTEN
```
