package ir.ac.kntu;

import ir.ac.kntu.member.Admin;
import ir.ac.kntu.menus.AppMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        Admin admin = storage.makeStorage();
        AppMenu appMenu = new AppMenu(scanner,admin);
        System.out.println("\n" +
                "███████╗ █████╗ ██████╗ ██╗        ███████╗ █████╗  █████╗ ██████╗ \n" +
                "██╔════╝██╔══██╗██╔══██╗██║        ██╔════╝██╔══██╗██╔══██╗██╔══██╗\n" +
                "█████╗  ███████║██████╔╝██║        █████╗  ██║  ██║██║  ██║██║  ██║\n" +
                "██╔══╝  ██╔══██║██╔══██╗██║        ██╔══╝  ██║  ██║██║  ██║██║  ██║\n" +
                "██║     ██║  ██║██║  ██║██║        ██║     ╚█████╔╝╚█████╔╝██████╔╝\n" +
                "╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝        ╚═╝      ╚════╝  ╚════╝ ╚═════╝ ");

        appMenu.runMenu();

    }
}