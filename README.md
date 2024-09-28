# ChatApp
Basic Chat Application using Java Socket Programming

The chat app is built using Java Socket Programming and threads. The implementation is explained step by step below -

#### Client -
The client will establish a connection with the server to send the messages. It uses Java Socket to connect to the server opened on a specific port.

1. Socket (clientSocket) - The socket object will be used to send and receive the data from server. It takes two parameters host and port. (Socket clientSocket = new Socket (host, port);)
2. BufferedReader in - This object is used to read data from clientSocket object.
3. PrintWriter out - This object is used to write data into the clientSocket object.


#### Server -
The server listens to the client request to establish a connection and exchanges messages. The server is started on a specific port using Java Socket.

1. ServerSocket - The socket object starts a server on given port and **listens to the client request** to establish a connection and start communication. (ServerSocket serverSocket = new ServerSocket(port);
2. Socket (clientSocket) - The server socket mentioned above uses method accept() which waits for client request (blocking request - blocks and waits for client to connect). It returns a Socket (clientSocket) when request is accepted.

#### Sender Thread -
The sender thread is used to send the messages from client to server and vice versa.
#### Receiver Thread -
The receiver thread is used to read the messages received from client to server and vice versa.
