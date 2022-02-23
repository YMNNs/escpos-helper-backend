package com.inspray.escposhelperbackend.util.escpos;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class NetworkPosPrinter implements PosPrinter {
    private final String address;
    private final int port;
    private Socket socket;
    private final int timeout;
    private OutputStreamWriter outputStreamWriter;
    private BufferedWriter bufferedWriter;

    public NetworkPosPrinter(String address, int port, int timeout) {
        this.address = address;
        this.port = port;
        this.timeout = timeout;
    }


    @Override
    public void connect() throws IOException {
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(this.address, this.port), this.timeout);
        this.outputStreamWriter = new OutputStreamWriter(this.socket.getOutputStream(), "GBK"); // 中文
        this.bufferedWriter = new BufferedWriter(this.outputStreamWriter);
        sendCommand(Commands.initialize());
    }

    @Override
    public void disconnect() throws IOException {
        this.bufferedWriter.flush();
        this.bufferedWriter.close();
        this.outputStreamWriter.close();
        this.socket.close();
    }

    @Override
    public void printLn(String line) throws IOException {
        this.bufferedWriter.write(line + "\n");
    }

    @Override
    public void print(String line) throws IOException {
        this.bufferedWriter.write(line);
    }


    @Override
    public void sendCommand(byte[] command) throws IOException {
        this.bufferedWriter.write(new String(command));
    }

    @Override
    public void sendCommand(List<byte[]> commands) throws IOException {
        for (byte[] command : commands) {
            this.bufferedWriter.write(new String(command));
        }
    }


    @Override
    public void flush() throws IOException {
        this.bufferedWriter.flush();
    }

    @Override
    public void reset() {
        this.bufferedWriter = null;
        this.outputStreamWriter = null;
        this.socket = null;
    }
}
