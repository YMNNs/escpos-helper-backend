package com.inspray.escposhelperbackend.controllers;

import com.inspray.escposhelperbackend.beans.actions.PrintAction;
import com.inspray.escposhelperbackend.beans.requests.PrintRequest;
import com.inspray.escposhelperbackend.beans.requests.SettingRequest;
import com.inspray.escposhelperbackend.beans.response.PrintResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/print")
public class PrintController {


    @PostMapping
    PrintResponse print(@RequestBody PrintRequest printRequest) {
        try {
            String address = printRequest.getPrinterUrl().split(":")[0];
            int port = Integer.parseInt(printRequest.getPrinterUrl().split(":")[1]);
            PrintAction printAction = new PrintAction();
            printAction.print(printRequest.getBlocks(), address, port);
        } catch (IOException e) {
            e.printStackTrace();
            return new PrintResponse("print failed");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new PrintResponse("invalid port");
        }

        return new PrintResponse("ok");
    }

    @PostMapping("/outputSettingsQRCode")
    PrintResponse outputSettingsQRCode(@RequestBody SettingRequest settingRequest) {
        try {
            String address = settingRequest.getPrinterUrl().split(":")[0];
            int port = Integer.parseInt(settingRequest.getPrinterUrl().split(":")[1]);
            PrintAction printAction = new PrintAction();
            printAction.outputSettingsQR(address, port, settingRequest.getOrigin(), settingRequest.getPrinterUrl(), settingRequest.getServerUrl());
        } catch (IOException e) {
            e.printStackTrace();
            return new PrintResponse("print failed");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new PrintResponse("invalid port");
        }

        return new PrintResponse("ok");
    }
}
