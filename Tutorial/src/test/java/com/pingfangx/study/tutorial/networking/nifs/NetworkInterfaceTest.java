package com.pingfangx.study.tutorial.networking.nifs;

import org.junit.Test;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/5/27
 */
public class NetworkInterfaceTest {
    @Test
    public void testListNetworkInterface() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
            displayNetworkInterface(networkInterface);
            displaySubInterfaces(networkInterface);
            System.out.println("");
        }
    }

    private void displaySubInterfaces(NetworkInterface networkInterface) throws SocketException {
        Enumeration<NetworkInterface> subInterfaces = networkInterface.getSubInterfaces();
        for (NetworkInterface subNetworkInterface : Collections.list(subInterfaces)) {
            displayNetworkInterface(subNetworkInterface, "\tSub Interface ");
        }
    }

    private void displayNetworkInterface(NetworkInterface networkInterface) throws SocketException {
        displayNetworkInterface(networkInterface, "");
    }

    private void displayNetworkInterface(NetworkInterface networkInterface, String prefix) throws SocketException {
        System.out.printf("%sDisplay name: %s%n", prefix, networkInterface.getDisplayName());
        System.out.printf("%sName: %s%n", prefix, networkInterface.getName());
        List<InterfaceAddress> interfaceAddresses = networkInterface.getInterfaceAddresses();
        for (InterfaceAddress interfaceAddress : interfaceAddresses) {
            System.out.printf("%sInterfaceAddress: %s%n", prefix, interfaceAddress);
        }
        System.out.printf("%sMTU:%s%n", prefix, networkInterface.getMTU());
    }

}
