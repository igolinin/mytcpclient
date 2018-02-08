/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytcpclient;

/**
 *
 * @author Dora
 */
import java.net.*; 
import java.io.*; 
import java.util.Scanner;

public class MyTCPClient 
{
    public static void main(String[] args) throws IOException 
    {
        InetAddress ip = null;
        Socket socket = null;
        
	// Pass Server IP as parameter arg[0]
        // Alternatively, pass null to getByName() to produce
	// special "Local Loopback" IP address, 
        // for testing on one machine without a network

        if (args.length != 1) 
        {
            System.err.println("No Server IP");
            ip =  InetAddress.getByName(null); 
            System.out.println("Server IP: " + ip); 
        }
        else
        {
            socket = new Socket(args[0], 6666);  
            System.out.println("Socket: " + socket);
        }   
        // Three I/O streams attached to the client:
        // Scanner sin is the incoming stream from the local console
        // Scanner in is the incoming stream from the Server (the response)
        // PrintWriter out is the outcoming stream to the Server (the request)
        Scanner sin;
        Scanner in;
        PrintWriter out;

        try 
        {  
            sin = new Scanner(System.in); 
            in = new Scanner(socket.getInputStream()); 
            out = new PrintWriter(socket.getOutputStream(),true); 

            System.out.println("Client up and running");
            System.out.println("Send a message or \"end\" to close connection");

            String userInput;
            // User type
            while ((userInput = sin.nextLine()) != null) 
            {
                // Send the same user message to the server
                out.println(userInput);

                // Get the response of the Server and print it out to the Client
                System.out.println(in.nextLine());
            }
        }
        catch (UnknownHostException e) 
        {
            System.err.println("Couldn't find host " + ip);
            System.exit(1);
        } 
        catch (IOException e) 
        {
            System.err.println("Couldn't get I/O connection to " + ip);
            System.exit(1);
        } 
        finally
        {
            System.out.println("Client closing..."); 
            socket.close(); 
        } 
    }
}
