package com.rw;

import com.rw.Model.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static com.rw.SignInController.CURRENT_USER;

public class SocketConnection {
    public User authorize(String username, String password) {
        String response = "";
        User user = null;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            User obj = new User();
            obj.setUsername(username);
            obj.setPassword(password);
            obj.setRole(1);
            obj.requestType = "authorization";


// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            user = (User) ois.readObject();

            //System.out.println(serverResponse.body);


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


        return user;


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

    public ArrayList<ServerFlightsResponse> findTickets(LocalDate date, String where, String whereTo) {
        String response = "";
        String result = "";
        ArrayList<ServerFlightsResponse> serverFlightsResponses = null;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            FlightsRequest obj = new FlightsRequest();
            obj.setWhere(where);
            obj.setWhereTo(whereTo);
            obj.setDate(date);
            obj.requestType = "findTicket";


// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            serverFlightsResponses = (ArrayList<ServerFlightsResponse>) ois.readObject();


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

        return serverFlightsResponses;


    }

    public ArrayList<Price> getPrices(String flightCode) {
        String response = "";
        String result = "";
        ArrayList<Price> Prices = null;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();

            Price obj = new Price();
            obj.setFlightCode(flightCode);
            obj.requestType = "getPrice";

// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            Prices = (ArrayList<Price>) ois.readObject();


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

        return Prices;


    }


    public int setPassenger(Passenger passenger) {

        String result = null;
        int genId = 0;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {

            System.out.println("Client connected to socket.");
            System.out.println();

            Passenger obj = new Passenger();
            obj = passenger;
            obj.requestType = "setPassenger";

// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            genId = (int) ois.readObject();
            System.out.println(genId);

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
        return genId;

    }

    public Ticket bookTicket(Ticket ticket) {

        Ticket res = null;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {

            System.out.println("Client connected to socket.");
            System.out.println();

            Ticket obj = new Ticket();
            obj = ticket;

            obj.requestType = "bookTicket";

// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            res = (Ticket) ois.readObject();
            System.out.println(res);


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
        return res;
    }

    public ArrayList<Ticket> getTicket() {
        String response = "";
        String result = "";
        ArrayList<Ticket> tickets = new ArrayList<>();
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();

            var obj = new AuthorizationRequest();
            obj.username = CURRENT_USER;
            obj.requestType = "getTickets";

// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            tickets = (ArrayList<Ticket>) ois.readObject();


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

        return tickets;


    }

    public ArrayList<FlightsRequest> getFlights() {
        String response = "";
        String result = "";
        ArrayList<FlightsRequest> flights = null;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            FlightsRequest obj = new FlightsRequest();

            obj.requestType = "getFlights";


// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            flights = (ArrayList<FlightsRequest>) ois.readObject();


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


        return flights;
    }

    public ArrayList<FlightsRequest> deleteFlight(String flightCode) {
        String res = null;
        ArrayList<FlightsRequest> upFlights = null;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            FlightsRequest obj = new FlightsRequest();
            obj.setFlightCode(flightCode);

            obj.requestType = "deleteFlight";


// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            var ret = (ServerResponse) ois.readObject();
            res = ret.body;
             upFlights = getFlights();
            System.out.println(ret.body);
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


        return upFlights;
    }

    public void addFlight(FlightsRequest flight) {
        ArrayList<FlightsRequest> upFlights = null;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            FlightsRequest obj = flight;
            obj.requestType = "addFlight";


// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            var ret = (ServerResponse) ois.readObject();
            String res = ret.body;

            System.out.println(ret.body);
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

    }

    public ArrayList<Passenger> getPassengers() {
        String response = "";
        String result = "";
        ArrayList<Passenger> passengers = null;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            Passenger obj = new Passenger();

            obj.requestType = "getPassengers";


// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            passengers = (ArrayList<Passenger>) ois.readObject();


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


        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        ArrayList<FlightsRequest> upFlights = null;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            Passenger obj = passenger;
            obj.requestType = "addPassenger";


// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            var ret = (ServerResponse) ois.readObject();
            String res = ret.body;

            System.out.println(ret.body);
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

    }

    public ArrayList<Passenger> deletePassenger(int currentPas) {
        String res = null;
        ArrayList<FlightsRequest> upFlights = null;
        ArrayList<Passenger> update = null;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            Passenger obj = new Passenger();
            obj.setPassId(currentPas);

            obj.requestType = "deletePassenger";


// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            var ret = (ServerResponse) ois.readObject();
            res = ret.body;
            update = getPassengers();
            System.out.println(ret.body);
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


        return update;
    }

    public ArrayList<Ticket> getTicketsForAdmin() {
        String response = "";
        String result = "";
        ArrayList<Ticket> tickets = null;
        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


            System.out.println("Client connected to socket.");
            System.out.println();


            Ticket obj = new Ticket();

            obj.requestType = "getTicketsForAdmin";


// пишем данные с консоли в канал сокета для сервера

            oos.writeObject(obj);
            oos.flush();


// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// если успел забираем ответ из канала сервера в сокете и сохраняемеё в ois переменную,  печатаем на консоль
            System.out.println("reading...");
            tickets = (ArrayList<Ticket>) ois.readObject();


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


        return tickets;
    }
}


