import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {

        try {

            while(!serverSocket.isClosed()) {  //While there is a connection to the client



                Socket socket = serverSocket.accept(); //Listens for a connection to be made to this socket and accepts it.
                System.out.println("A new client has connected"); //Announces to the chat a new client connected
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler); //A thread is created for each new client
                thread.start(); //Thread is started

            }

        }catch(IOException e){

        }
    }
    public  void closeServerSocket(){ //Used to end connections

        try{

            if(serverSocket != null){
                serverSocket.close();

            }

        }catch(IOException e){

            e.printStackTrace();

        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234); //Creates a server socket, bound to the specified port.
        Server server = new Server(serverSocket); // New Server object created passing through the serverSocket
        server.startServer();

    }
}