package T2;

import java.util.ArrayList;
import java.util.Scanner;

public class BankArvin {
    // array penampung nomor urut akun untuk masing-masing bank
    // indeks bank: 0= ArvinBank, 1=HMB, 2=BHQ, 3=BankAI, 4=BSS
    private static int[] counterAkun = {1, 1, 1, 1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Bank> daftarBank = new ArrayList<>();

        // data dummy biar bisa langsung login
        // ID: F1D-ARVIN-030405-0781, Password: 123, Saldo: Rp100.000, Biaya Admin: Rp1.000
        daftarBank.add(new Bank("F1D-ARVIN-030405-0781", "ArvinBank", "123", 100000, 1000));
        counterAkun[0]++; //buat nambahin akun setelah data dummy

        boolean berjalan = true;

        while (berjalan) {
            //MENU UTAMA
            Bank.bersihkanLayar();
            System.out.println("========================================");
            System.out.println(" SELAMAT DATANG DI ARVIN FINANCE SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Login ke Akun Bank");
            System.out.println("2. Buat Akun Bank Baru");
            System.out.println("3. Keluar Aplikasi");
            System.out.print("Pilih menu (1-3): ");

            int menuUtama = scanner.nextInt();
            scanner.nextLine();

            if (menuUtama == 1) {
                //MENU LOGIN
                Bank.bersihkanLayar();
                System.out.println("=========================================");
                System.out.println("               LOGIN BANK                ");
                System.out.println("=========================================");
                System.out.print("Masukkan ID Bank : ");
                String inputId = scanner.nextLine();
                System.out.print("Masukkan Password: ");
                String inputPass = scanner.nextLine();

                //cocokin akun buat login
                Bank akunAktif = null;
                for (Bank b : daftarBank) {
                    if (b.getIdBank().equalsIgnoreCase(inputId) && b.getPassword().equals(inputPass)) {
                        akunAktif = b;
                        break;
                    }
                }

                //akun ada/berhasil login
                if (akunAktif != null) {
                    boolean diDalamBank = true;
                    while (diDalamBank) {
                        Bank.bersihkanLayar();
                        System.out.println("=========================================");
                        System.out.println("  SELAMAT DATANG DI " + akunAktif.getNamaBank().toUpperCase());
                        System.out.println("  ID  : " + akunAktif.getIdBank());
                        System.out.println("  Admin: Rp " + (int) akunAktif.getBiayaAdmin() + " / withdraw");
                        System.out.println("=========================================");
                        System.out.println("1. Lihat Saldo");
                        System.out.println("2. Deposit (Isi Saldo)");
                        System.out.println("3. Withdraw (Tarik Tunai)");
                        System.out.println("4. Riwayat Transaksi");
                        System.out.println("5. Logout / Kembali ke Menu Utama");
                        System.out.print("Pilih menu (1-5): ");

                        int pilihan = scanner.nextInt();
                        scanner.nextLine();

                        switch (pilihan) {
                            //MENU LIHAT SALDO
                            case 1:
                                Bank.bersihkanLayar();
                                System.out.println("\n--- LIHAT SALDO ---");
                                akunAktif.tampilkanSaldo();
                                System.out.println("\nTekan Enter untuk kembali...");
                                scanner.nextLine();
                                break;
                            //MENU DEPO
                            case 2:
                                Bank.bersihkanLayar();
                                boolean loopDeposit = true;
                                while (loopDeposit) {
                                    System.out.println("\n--- DEPOSIT (" + akunAktif.getNamaBank() + ") ---");
                                    System.out.println("1. Lakukan Deposit");
                                    System.out.println("2. Kembali");
                                    System.out.print("Pilih submenu (1-2): ");
                                    int subPilihan = scanner.nextInt();
                                    scanner.nextLine();

                                    if (subPilihan == 1) {
                                        Bank.bersihkanLayar();
                                        System.out.print("Masukkan jumlah deposit: Rp ");
                                        double jumlah = scanner.nextDouble();
                                        scanner.nextLine();
                                        akunAktif.deposit(jumlah);
                                        akunAktif.tampilkanSaldo();
                                        System.out.println("\nTekan Enter untuk melanjutkan...");
                                        scanner.nextLine();
                                        Bank.bersihkanLayar();
                                    } else if (subPilihan == 2) {
                                        loopDeposit = false;
                                    } else {
                                        System.out.println("Pilihan tidak valid!");
                                    }
                                }
                                break;
                            //MENU NARIK
                            case 3:
                                Bank.bersihkanLayar();
                                boolean loopWithdraw = true;
                                while (loopWithdraw) {
                                    System.out.println("\n--- WITHDRAW (" + akunAktif.getNamaBank() + ") ---");
                                    System.out.println("1. Lakukan Withdraw");
                                    System.out.println("2. Kembali");
                                    System.out.print("Pilih submenu (1-2): ");
                                    int subPilihan = scanner.nextInt();
                                    scanner.nextLine();

                                    if (subPilihan == 1) {
                                        Bank.bersihkanLayar();
                                        System.out.print("Masukkan jumlah withdraw: Rp ");
                                        double jumlah = scanner.nextDouble();
                                        scanner.nextLine();
                                        akunAktif.withdraw(jumlah);
                                        akunAktif.tampilkanSaldo();
                                        System.out.println("\nTekan Enter untuk melanjutkan...");
                                        scanner.nextLine();
                                        Bank.bersihkanLayar();
                                    } else if (subPilihan == 2) {
                                        loopWithdraw = false;
                                    } else {
                                        System.out.println("Pilihan tidak valid!");
                                    }
                                }
                                break;
                            //MENU HISTORY
                            case 4:
                                Bank.bersihkanLayar();
                                akunAktif.tampilkanRiwayat();
                                System.out.println("\nTekan Enter untuk kembali...");
                                scanner.nextLine();
                                break;
                            //KELUAR
                            case 5:
                                diDalamBank = false;
                                break;
                            default:
                                System.out.println("Pilihan tidak valid! Silakan pilih 1-5.");
                        }
                    }
                } else {
                    System.out.println("\nID Bank atau Password salah!");
                    System.out.println("Tekan Enter untuk coba lagi...");
                    scanner.nextLine();
                }

            } else if (menuUtama == 2) {
                //MENU BUAT AKUN BARU
                Bank.bersihkanLayar();
                System.out.println("=========================================");
                System.out.println("            BUAT AKUN BANK BARU          ");
                System.out.println("=========================================");
                System.out.println("Pilih Bank:");
                System.out.println("1. ArvinBank");
                System.out.println("2. Hueco Mundo Bank (HMB)");
                System.out.println("3. Bank of Holy Quincy (BHQ)");
                System.out.println("4. BankAI");
                System.out.println("5. Bank of Soul Society (BSS)");
                System.out.print("Pilih (1-5): ");

                int pilihBank = scanner.nextInt();
                scanner.nextLine();

                String prefix = "";
                String kodeKhusus = "";
                String namaBank = "";
                double biayaAdmin = 0;
                int bankIdx = pilihBank - 1;

                switch (pilihBank) {
                    case 1:
                        prefix = "F1D";
                        kodeKhusus = "078";
                        namaBank = "ArvinBank";
                        biayaAdmin = 1000;
                        break;
                    case 2:
                        prefix = "D1D";
                        kodeKhusus = "079";
                        namaBank = "Hueco Mundo Bank (HMB)";
                        biayaAdmin = 1500;
                        break;
                    case 3:
                        prefix = "A1D";
                        kodeKhusus = "080";
                        namaBank = "Bank of Holy Quincy (BHQ)";
                        biayaAdmin = 1500;
                        break;
                    case 4:
                        prefix = "B1D";
                        kodeKhusus = "081";
                        namaBank = "BankAI";
                        biayaAdmin = 1000;
                        break;
                    case 5:
                        prefix = "C1D";
                        kodeKhusus = "082";
                        namaBank = "Bank of Soul Society (BSS)";
                        biayaAdmin = 1500;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                        System.out.println("Tekan Enter untuk kembali...");
                        scanner.nextLine();
                        continue;
                }
                //form buat login
                Bank.bersihkanLayar();
                System.out.print("======= " + namaBank + " =======\n");
                System.out.print("Masukkan Nama Lengkap          : ");
                String nama = scanner.nextLine().toUpperCase().replaceAll("\\s+", "");
                System.out.print("Masukkan Tanggal Lahir (DDMMYY): ");
                String tglLahir = scanner.nextLine();
                System.out.print("Buat Password Baru             : ");
                String password = scanner.nextLine();

                //format ID unik
                String idGenerated = prefix + "-" + nama + "-" + tglLahir + "-" + kodeKhusus + counterAkun[bankIdx];
                counterAkun[bankIdx]++;

                //setoran awal harus minimal Rp100.000
                double setoranAwal = 0;
                while (setoranAwal < 100000) {
                    System.out.print("Masukkan Setoran Awal (Min. Rp 100.000): Rp ");
                    double inputSetoran = scanner.nextDouble();
                    scanner.nextLine();
                    setoranAwal += inputSetoran;
                    if (inputSetoran < 100000) {
                        double sisaKurang = 100000 - setoranAwal; 
                        System.out.println("Saldo saat ini: Rp " + (int) setoranAwal);
                        if (sisaKurang > 0){
                            System.out.println("Kurang Rp " + (int) sisaKurang + " Lagi! Isi saldomu minimal Rp100.000 untuk melanjutkan!\n");
                        } else if (sisaKurang < 0){
                            break;
                        }
                    }
                }

                //buat & tambahk ke arraylist
                Bank akunBaru = new Bank(idGenerated, namaBank, password, setoranAwal, biayaAdmin);
                daftarBank.add(akunBaru);

                System.out.println("\n=== AKUN BERHASIL DIBUAT ===");
                System.out.println("ID Bank Kamu : " + idGenerated);
                System.out.println("Password     : " + password);
                System.out.println("Simpan ID ini untuk melakukan Login!");
                System.out.println("\nTekan Enter untuk kembali ke Menu Utama...");
                scanner.nextLine();

            } else if (menuUtama == 3) {
                System.out.println("\nTerima kasih telah menggunakan layanan kami!");
                berjalan = false;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }

        scanner.close();
    }
}