/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erhannis.networkinterfaceinspector;

import com.erhannis.mathnstuff.MeUtils;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *
 * @author erhannis
 */
public class Main {
    public static final Boolean GET_CANONICAL_HOSTNAME = false;

    public static void main(String[] args) {
        Enumeration<NetworkInterface> interfaces;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                try {
                    NetworkInterface ni = interfaces.nextElement();
                    printNetworkInterface("", ni);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }

    public static void printNetworkInterface(String prefix, NetworkInterface ni) throws SocketException {
        System.out.println(prefix + "NI " + ni.getIndex() + " : " + ni);
        byte[] hAddr = ni.getHardwareAddress();
        System.out.println(prefix + "        hAddr: " + (hAddr == null ? null : MeUtils.bytesToHex(hAddr)));
        System.out.println(prefix + "           up: " + ni.isUp());
        System.out.println(prefix + "     loopback: " + ni.isLoopback());
        System.out.println(prefix + "  point2point: " + ni.isPointToPoint());
        System.out.println(prefix + "      virtual: " + ni.isVirtual());
        System.out.println(prefix + "          mtu: " + ni.getMTU());
        System.out.println(prefix + "    multicast: " + ni.supportsMulticast());
        System.out.println(prefix + "  INTERFACE ADDRESSES:");
        for (InterfaceAddress ia : ni.getInterfaceAddresses()) {
            printInterfaceAddress(prefix+"<==>", ia);
        }
        System.out.println(prefix + "  SUB-INTERFACES:");
        Enumeration<NetworkInterface> subs = ni.getSubInterfaces();
        while (subs.hasMoreElements()) {
            NetworkInterface sub = subs.nextElement();
            printNetworkInterface(prefix+"<==>", sub);
        }
    }
    
    public static void printInterfaceAddress(String prefix, InterfaceAddress ia) {
        System.out.println(prefix+"Interface Address");
        System.out.println(prefix+"  prefixLen: "+ia.getNetworkPrefixLength());
        System.out.println(prefix+"  ADDRESS");
        printInetAddress(prefix+"    ", ia.getAddress());
        System.out.println(prefix+"  BROADCAST");
        printInetAddress(prefix+"    ", ia.getBroadcast());
    }
    public static void printInet4Address(String prefix, Inet4Address ia) {
        System.out.println(prefix+"Inet4Address");
        System.out.println(prefix+"     address: " + ia.getHostAddress());
        System.out.println(prefix+"     address: " + MeUtils.bytesToHex(ia.getAddress()));
        if (GET_CANONICAL_HOSTNAME) {
            System.out.println(prefix+"   canonical: " + ia.getCanonicalHostName());
        }
        System.out.println(prefix+"    loopback: " + ia.isLoopbackAddress());
        System.out.println(prefix+"    anyLocal: " + ia.isAnyLocalAddress());
        System.out.println(prefix+"   linkLocal: " + ia.isLinkLocalAddress());
        System.out.println(prefix+"   siteLocal: " + ia.isSiteLocalAddress());
        System.out.println(prefix+"   multicast: " + ia.isMulticastAddress());
        System.out.println(prefix+" mcNodeLocal: " + ia.isMCNodeLocal());
        System.out.println(prefix+" mcLinkLocal: " + ia.isMCLinkLocal());
        System.out.println(prefix+" mcSiteLocal: " + ia.isMCSiteLocal());
        System.out.println(prefix+"  mcOrgLocal: " + ia.isMCOrgLocal());
        System.out.println(prefix+"    mcGlobal: " + ia.isMCGlobal());
    }
    public static void printInet6Address(String prefix, Inet6Address ia) {
        System.out.println(prefix+"Inet6Address");
        System.out.println(prefix+"     address: " + ia.getHostAddress());
        System.out.println(prefix+"     address: " + MeUtils.bytesToHex(ia.getAddress()));
        if (GET_CANONICAL_HOSTNAME) {
            System.out.println(prefix+"   canonical: " + ia.getCanonicalHostName());
        }
        System.out.println(prefix+"    loopback: " + ia.isLoopbackAddress());
        System.out.println(prefix+"    anyLocal: " + ia.isAnyLocalAddress());
        System.out.println(prefix+"   linkLocal: " + ia.isLinkLocalAddress());
        System.out.println(prefix+"   siteLocal: " + ia.isSiteLocalAddress());
        System.out.println(prefix+"   multicast: " + ia.isMulticastAddress());
        System.out.println(prefix+" mcNodeLocal: " + ia.isMCNodeLocal());
        System.out.println(prefix+" mcLinkLocal: " + ia.isMCLinkLocal());
        System.out.println(prefix+" mcSiteLocal: " + ia.isMCSiteLocal());
        System.out.println(prefix+"  mcOrgLocal: " + ia.isMCOrgLocal());
        System.out.println(prefix+"    mcGlobal: " + ia.isMCGlobal());
        System.out.println(prefix+"  ipv4compat: " + ia.isIPv4CompatibleAddress());
        System.out.println(prefix+"     scopeId: " + ia.getScopeId());
        NetworkInterface ni = ia.getScopedInterface();
        System.out.println(prefix+" scopedIFace: " + (ni == null ? ni : ni.getIndex() + " : " + ni));
    }
    public static void printInetAddress(String prefix, InetAddress ia) {
        if (ia instanceof Inet4Address) {
            printInet4Address(prefix, (Inet4Address)ia);
        } else if (ia instanceof Inet6Address) {
            printInet6Address(prefix, (Inet6Address)ia);
        } else if (ia == null) {
            System.out.println(prefix+"InetAddress NULL");
        } else {
            System.out.println(prefix+"InetAddress");
            System.out.println(prefix+"     address: " + ia.getHostAddress());
            System.out.println(prefix+"     address: " + MeUtils.bytesToHex(ia.getAddress()));
            if (GET_CANONICAL_HOSTNAME) {
                System.out.println(prefix+"   canonical: " + ia.getCanonicalHostName());
            }
            System.out.println(prefix+"    loopback: " + ia.isLoopbackAddress());
            System.out.println(prefix+"    anyLocal: " + ia.isAnyLocalAddress());
            System.out.println(prefix+"   linkLocal: " + ia.isLinkLocalAddress());
            System.out.println(prefix+"   siteLocal: " + ia.isSiteLocalAddress());
            System.out.println(prefix+"   multicast: " + ia.isMulticastAddress());
            System.out.println(prefix+" mcNodeLocal: " + ia.isMCNodeLocal());
            System.out.println(prefix+" mcLinkLocal: " + ia.isMCLinkLocal());
            System.out.println(prefix+" mcSiteLocal: " + ia.isMCSiteLocal());
            System.out.println(prefix+"  mcOrgLocal: " + ia.isMCOrgLocal());
            System.out.println(prefix+"    mcGlobal: " + ia.isMCGlobal());
        }
    }
}
