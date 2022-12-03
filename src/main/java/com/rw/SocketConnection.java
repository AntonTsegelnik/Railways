package com.rw;

import com.rw.Model.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SocketConnection {
    public String authorize(String username, String password) {
        String response = "";
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            AuthorizationRequest obj = new AuthorizationRequest();
            obj.username = username;
            obj.password = password;
            obj.requestType = "authorization";


// пишем данные с консоли в канал сокета для сервера


            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            ServerResponse serverResponse = (ServerResponse) ois.readObject();

            //System.out.println(serverResponse.body);
            response = serverResponse.body;

            System.out.println("Closing connections & channels on clentSide - DONE.");

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return response;


    }

    public String register(String username, String password, int role) {
        String response = "";
        String result = "";
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            RegistrationRequest obj = new RegistrationRequest();
            obj.username = username;
            obj.password = password;
            obj.role = role;
            obj.requestType = "registration";


// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            ServerResponse serverResponse = (ServerResponse) ois.readObject();

            result = serverResponse.body;
            System.out.println("1" + result);

            System.out.println("Closing connections & channels on clentSide - DONE.");

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;


    }
    public String findTickets(LocalDate date, String where, String whereTo) {
        String response = "";
        String result = "";
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            FlightsRequest obj = new FlightsRequest();
            obj.Where = where;
            obj.WhereTo = whereTo;
            obj.Date = date;
            obj.requestType = "findTicket";


// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            var serverFlightsResponses = (ArrayList<ServerFlightsResponse>) ois.readObject();

            for (var item: serverFlightsResponses
                 ) {
                System.out.println(item.Date);
                System.out.println(item.Where);
                System.out.println(item.WhereTo);
            }

            System.out.println("Closing connections & channels on clentSide - DONE.");

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;


    }
}

