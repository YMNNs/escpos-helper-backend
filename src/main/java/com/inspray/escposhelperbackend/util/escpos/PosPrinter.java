package com.inspray.escposhelperbackend.util.escpos;

import java.io.IOException;
import java.util.List;

public interface PosPrinter {
    void connect() throws IOException;

    void disconnect() throws IOException;

    void printLn(String line) throws IOException;

    void print(String line) throws IOException;

    void sendCommand(byte[] command) throws IOException;

    void sendCommand(List<byte[]> commands) throws IOException;

    void flush() throws IOException;

    void reset();
}
