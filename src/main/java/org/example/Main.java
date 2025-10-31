package org.example;

public class Main {
    public static void main(String[] args) {
        // ===== Point Testleri =====
        Point first = new Point(6, 5);
        Point second = new Point(3, 1);
        Point point = new Point(0, 0);

        System.out.println("distance(0,0)= " + first.distance());
        System.out.println("distance(second)= " + first.distance(second));
        System.out.println("distance(2,2)= " + first.distance(2, 2));
        System.out.println("distance()= " + point.distance());

        // ===== Player Testleri =====
        Player player1 = new Player("Arthur", 120, Weapon.SWORD);
        Player player2 = new Player("Lancelot", 50, Weapon.AXE);

        System.out.println(player1.healthRemaining()); // 100 (health 120 -> 100)
        player1.loseHealth(30);
        System.out.println(player1.healthRemaining()); // 70
        player1.loseHealth(80); // health goes below 0 -> message
        player1.restoreHealth(50);
        System.out.println(player1.healthRemaining()); // 50

        System.out.println(player2.healthRemaining()); // 50
        player2.restoreHealth(60); // 50 + 60 -> 100 max
        System.out.println(player2.healthRemaining()); // 100
    }

    // ===== Point Sınıfı =====
    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public void setX(int x) { this.x = x; }
        public void setY(int y) { this.y = y; }

        public double distance() {
            return distance(0, 0);
        }

        public double distance(Point p) {
            return distance(p.getX(), p.getY());
        }

        public double distance(int a, int b) {
            int dx = a - this.x;
            int dy = b - this.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    // ===== Weapon Enum =====
    enum Weapon {
        SWORD(50, 1.2),
        AXE(70, 0.9),
        BOW(40, 1.5);

        private final int damage;
        private final double attackSpeed;

        Weapon(int damage, double attackSpeed) {
            this.damage = damage;
            this.attackSpeed = attackSpeed;
        }

        public int getDamage() { return damage; }
        public double getAttackSpeed() { return attackSpeed; }
    }

    // ===== Player Sınıfı =====
    static class Player {
        private String name;
        private int healthPercentage;
        private Weapon weapon;

        public Player(String name, int healthPercentage, Weapon weapon) {
            this.name = name;
            if (healthPercentage > 100) this.healthPercentage = 100;
            else if (healthPercentage < 0) this.healthPercentage = 0;
            else this.healthPercentage = healthPercentage;
            this.weapon = weapon;
        }

        public int healthRemaining() {
            return healthPercentage;
        }

        public void loseHealth(int damage) {
            healthPercentage -= damage;
            if (healthPercentage < 0) {
                healthPercentage = 0;
                System.out.println(name + " player has been knocked out of game");
            }
        }

        public void restoreHealth(int healthPotion) {
            healthPercentage += healthPotion;
            if (healthPercentage > 100) healthPercentage = 100;
        }
    }
}
