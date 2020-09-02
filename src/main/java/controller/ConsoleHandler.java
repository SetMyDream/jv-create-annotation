package controller;

import dao.BetDao;
import dao.UserDao;
import java.util.Scanner;
import lib.Inject;
import model.Bet;
import model.User;

public class ConsoleHandler {
    @Inject
    BetDao betDao;
    @Inject
    UserDao userDao;

    public void handle() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("До побачення!");
                return;
            }
            Bet bet = null;
            User user = null;
            try {
                String splitter = " ";
                String[] betData = command.split(splitter);
                int value = Integer.parseInt(betData[0]);
                double risk = Double.parseDouble(betData[1]);
                String name = betData[2];
                double points = Double.parseDouble(betData[3]);
                bet = new Bet(value, risk);
                user = new User(name, points);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Будь ласка, введіть коректні дані");
                continue;
            }
            betDao.add(bet);
            userDao.add(user);
            System.out.println(bet == null ? null : bet.toString());
            System.out.println(user == null ? null : user.toString());
        }
    }
}
