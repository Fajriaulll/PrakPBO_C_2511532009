package pekan3;

import java.util.*;

public class Main {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Rekening> daftarRekening = new ArrayList<>();
        Rekening akunAktif = null;
        boolean isRunning = true;
        
    
        System.out.println("=== SISTEM PERBANKAN ====");

        while (isRunning) {
        System.out.println("\nMenu Utama: ");
        System.out.println("1.  Buka Rekening Baru");
        System.out.println("2.  Setor Tunai");
        System.out.println("3.  Tarik Tunai");
        System.out.println("4.  Cek Informasi Rekening");
        System.out.println("5.  Ganti Akun");
        System.out.println("6.  Cek Mutasi");
        System.out.println("7.  Ganti PIN");
        System.out.println("8.  Keluar");
        System.out.print("Pilih menu: ");

        int pilihan = input.nextInt();
        input.nextLine();

        switch (pilihan) {
            case 1:
                System.out.print("Masukkan No Rekening:  ");
                String no = input.nextLine();
                System.out.print("Masukkan Nama Pemilik: ");
                String nama = input.nextLine();
                System.out.print("Masukkan Saldo Awal:  ");
                double saldo = input.nextDouble();
                System.out.print("Masukkan PIN (6 digit): ");
                String pinuser = input.next();

                while (!pinuser.matches("\\d{6}")) {
                    System.out.println("PIN harus berupa 6 digit angka!");
                    System.out.print("Masukkan PIN lagi: ");
                    pinuser = input.next();
                }

                Rekening rekeningBaru = new Rekening(no, nama, saldo, pinuser);
                daftarRekening.add(rekeningBaru);
                akunAktif = rekeningBaru;
                
                break;

            case 2:
                if (akunAktif == null) {
                    System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
                } else {
                    System.out.print("Masukkan nominal setor: ");
                    double setor = input.nextDouble();
                    akunAktif.setorTunai(setor); // Memanggil Behavior / method
                }
                break;

            case 3:
                if (akunAktif == null) {
                	System.out.println("Error: Mohon maaf, anda belum memiliki nomor rekening!");
                } else {
                	System.out.println("Masukkan PIN : ");
                	String inputPin = input.next();
                	
                	if (akunAktif.otentikasi(inputPin)) {
                	
                	System.out.print("Masukkan Nominal yang Ingin ditarik: ");
                	double tarik = input.nextDouble();
                	akunAktif.tarikTunai(tarik);
                	} else {
                		System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                	}
                }
                break;

            case 4:
                if (akunAktif == null) {
                    System.out.println("Error: Anda belum membuka rekening!");
                } else {
                    akunAktif.cekInformasi();
                }
                break;
                
            case 5:
            	System.out.println("Masukkan No Rekening: ");
            	String cariNorek = input.nextLine();
            	
            	boolean ditemukan = false;
            	
            	for (Rekening rekening : daftarRekening) {
            		if (rekening.getNomorRekening().equals(cariNorek)) {
            			akunAktif = rekening;
            			ditemukan = true;
            			
            			System.out.println("Berhasil mengganti akun.");
            			akunAktif.cekInformasi();
            			
            			break;
            		}
            	}
            	if (!ditemukan) {
            		System.out.println("Rekening tidak ditemukan!");
            	} 
            	
            		break;
            		
            case 6:
            	if (akunAktif == null ) {
            		System.out.println("Error: Anda belum membuka rekening!");
            	} else {
            		System.out.println("Masukkan PIN : ");
            		String inputPin = input.next();
            		
            		if (akunAktif.otentikasi(inputPin)) {
                		akunAktif.cetakMutasi();

            		} else {
            			System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
            		}
            	}
            	
            	break;
            	
            	case 7:
            	    if (akunAktif == null) {
            	        System.out.println("Error: Anda belum membuka rekening!");
            	    } else {
            	        System.out.print("Masukkan PIN lama: ");
            	        String pinLama = input.next();

            	        System.out.print("Masukkan PIN baru: ");
            	        String pinBaru = input.next();

            	        if (!pinBaru.matches("\\d{6}")) {
            	            System.out.println("PIN baru harus berupa 6 angka!");
            	        } else {
            	            String hasil = akunAktif.gantiPin(pinLama, pinBaru);
            	            System.out.println(hasil);
            	        }
            	    }
            	    break;
            	

            case 8:
                isRunning = false;
                System.out.println("Sistem ditutup. Terima kasih!");
                break;
            
            default:
                System.out.println("Pilihan tidak valid!");
                

        	}
        }
        input.close();
    }
    
}
