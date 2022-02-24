package com.inspray.escposhelperbackend.beans.actions;

import com.inspray.escposhelperbackend.beans.Block;
import com.inspray.escposhelperbackend.util.escpos.Commands;
import com.inspray.escposhelperbackend.util.escpos.NetworkPosPrinter;
import com.inspray.escposhelperbackend.util.escpos.PosPrinter;

import java.io.IOException;
import java.util.List;

public class PrintAction {
    public void print(List<Block> blocks, String address, int port) throws IOException {
        PosPrinter printer = new NetworkPosPrinter(address, port, 10000);
        printer.connect();
        for (Block block : blocks) {
            printer.print(parseBlock(block));
        }
        printer.print("\n\n");
        printer.sendCommand(Commands.cutAndFeed(0));
        printer.flush();
        printer.disconnect();
    }

    public void outputSettingsQR(String address, int port, String origin, String printerUrl, String serverUrl) throws IOException {
        PosPrinter printer = new NetworkPosPrinter(address, port, 10000);
        printer.connect();

        printer.sendCommand(Commands.alignCenter());
        printer.sendCommand(Commands.setCharacterSize(3, 3));
        printer.print("扫码打印\n\n");
        printer.sendCommand(Commands.qrCode(String.format("%s?p=%s&s=%s", origin, printerUrl, serverUrl), 2, 12, 0));
        printer.print("\n\n");
        printer.sendCommand(Commands.alignLeft());
        printer.sendCommand(Commands.setCharacterSize(1, 1));
        printer.sendCommand(Commands.setHorizontalTabPosition(new int[]{5, 15, 17}));
        printer.print("服务器URL");
        printer.sendCommand(Commands.horizontalTab());
        printer.print(":");
        printer.sendCommand(Commands.horizontalTab());
        printer.printLn(serverUrl);
        printer.print("打印机URL");
        printer.sendCommand(Commands.horizontalTab());
        printer.print(":");
        printer.sendCommand(Commands.horizontalTab());
        printer.printLn(printerUrl);
        printer.print("网页URL");
        printer.sendCommand(Commands.horizontalTab());
        printer.print(":");
        printer.sendCommand(Commands.horizontalTab());
        printer.printLn(origin);

        printer.print("\n\n\n");
        printer.sendCommand(Commands.cutAndFeed(0));
        printer.flush();
        printer.disconnect();
    }

    private String parseBlock(Block block) {
        StringBuilder res = new StringBuilder();
        switch (block.getAlign()) {
            case 1: {
                res.append(new String(Commands.alignLeft()));
                break;
            }
            case 2: {
                res.append(new String(Commands.alignCenter()));
                break;
            }
            case 3: {
                res.append(new String(Commands.alignRight()));
                break;
            }
        }
        res.append(new String(Commands.setCharacterSize(block.getWidth(), block.getHeight())));
        res.append(block.getText());
        if (block.isLf()) {
            res.append('\n');
        }
        return res.toString();
    }
}
