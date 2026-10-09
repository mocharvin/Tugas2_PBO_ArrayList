package T2;

public class Bank {
    private String idBank;
    private String namaBank;
    private String password;
    private double balance;
    private double biayaAdmin;
    private String riwayatTransaksi = "";

    //mehtod buat bank baru (contructor)
    public Bank(String idBank, String namaBank, String password, double initialBalance, double biayaAdmin) {
        this.idBank = idBank;
        this.namaBank = namaBank;
        this.password = password;
        this.balance = initialBalance;
        this.biayaAdmin = biayaAdmin;
        if (initialBalance > 0) {
            tambahRiwayat("Akun " + namaBank + " dibuat dengan saldo awal: Rp " + (int) initialBalance);
        }
    }

    //accessor
    public String getIdBank() {
        return idBank;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public double getBiayaAdmin() {
        return biayaAdmin;
    }

    //method depo
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Berhasil Deposit ke " + namaBank + ": Rp " + (int) amount);
            tambahRiwayat("Deposit: +Rp " + (int) amount);
        } else {
            System.out.println("Jumlah deposit tidak valid!");
        }
    }

    //method wd
    public void withdraw(double amount) {
        //harus minimal Rp1.000 kalo mau wd, ada biaya admin (beda2 tiep bank), dan menyisakan saldo minimal Rp10.000
        double totalDitarik = amount + biayaAdmin;

        if (amount < 1000) {
            System.out.println("Withdraw Gagal: Minimal penarikan adalah Rp 1.000!");
        } else if ((balance - totalDitarik) < 10000) {
            System.out.println("Withdraw Gagal: Saldo pengendapan minimal Rp 10.000 dan biaya admin Rp " + (int) biayaAdmin + "!");
        } else {
            balance -= totalDitarik;
            System.out.println("Berhasil Withdraw: Rp " + (int) amount + " (Biaya Admin: Rp " + (int) biayaAdmin + ")");
            tambahRiwayat("Withdraw: -Rp " + (int) amount + " (Admin: Rp " + (int) biayaAdmin + ")");
        }
    }

    public void tampilkanSaldo() {
        System.out.println("Saldo " + namaBank + " Anda: Rp " + (int) getBalance());
    }

    private void tambahRiwayat(String catatan) {
        riwayatTransaksi += "- " + catatan + "\n";
    }

    public void tampilkanRiwayat() {
        System.out.println("=== RIWAYAT TRANSAKSI (" + namaBank.toUpperCase() + ") ===");
        if (riwayatTransaksi.equals("")) {
            System.out.println("Belum ada transaksi.");
        } else {
            System.out.print(riwayatTransaksi);
        }
    }

    public static void bersihkanLayar() {
        System.out.print("\033[H\033[2J"); //pake kode ANSII biar cepet
        System.out.flush();
    }
}