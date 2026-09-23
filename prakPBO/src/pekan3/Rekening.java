package pekan3;
import java.util.*;

public class Rekening {
    private String nomorRekening;
    private String namaPemilik;
    private double saldo;
    private String pin; // data sensitif
    
    
    
    // implementasi asosiasi (1 to many)
    private ArrayList<Transaksi> riwayatTransaksi;

    // 2. Modifikasi Constructor untuk menerima Pin awal: 
    public Rekening (String nomor, String nama, double saldoAwal, String pinAwal) {
        nomorRekening = nomor;
        namaPemilik = nama;
        saldo = saldoAwal;
        
        // validasi pin di dalam constructor
        if (pinAwal.length() == 6) {
        	this.pin = pinAwal;
        } else {
        	System.out.println("Peringatan : PIN harus 6 digit! Menggunakan PIN default 12345");
        	this.pin = "123456";
        }
        
        //wajib menginisialisasi arraylist didalam constructor agar tidak null pointer exception
        this.riwayatTransaksi = new ArrayList<>();
        
        if (saldoAwal >= 50000) {
            System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
        } else {
            System.out.println("Saldo tidak boleh kurang dari Rp.50.000");
        }
    }
    
    // 3. getter untuk atribut yang diizinkan dibaca publik
    public String getNomorRekening() { return nomorRekening; }
    public String getNamaPemilik() { return namaPemilik; }
    
    // 4. Method otentikasi internal (validasi enkapsulasi)
    public boolean otentikasi(String inputPin) {
    	return this.pin.equals(inputPin);
    }
    

    public void setorTunai(double nominal) {
        if (nominal >= 10000) {
        	if (nominal >= 500000) {
        		System.out.println("Batas setor tunai adalah Rp.500.000");
        	} else {
        	
            saldo += nominal;
            //Merekam riwayat (Pembuatan objek transaksi di dalam method)
            String idTrx = "TRX-S-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
            riwayatTransaksi.add(trxBaru);
            
            System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
        	}
        } else {
            System.out.println("Gagal: Nominal setor harus lebih dari 10.000!");
        }
    }

    public void cekInformasi() {
        System.out.println("\n--- INFO REKENING ---  ");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : " + saldo);
        System.out.println("------------------------");
    }
    
    public void tarikTunai(double nominal) {
    	if (saldo >= 10000) {
    		if (saldo >= nominal) {
    		saldo = saldo-nominal;
    		System.out.println("Tarik tunai berhasil saldo sekrang : Rp" + saldo);
    		
    		// testing rekaman
    		String idTrx = "TRX-T-" + System.currentTimeMillis();
    		Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
    		riwayatTransaksi.add(trxBaru);
    		
    		} else {
    			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo anda: Rp." + saldo);
    		}
    		
    	} else {
    		System.out.println("Transaksi Gagal : Minimal nomonal penarikan 10.000");
    	}
    }
    
    public void cetakMutasi() {
    	System.out.println("Mutasi Akun");
    	if (riwayatTransaksi.isEmpty() ) {
			System.out.println("Belum ada transaksi yang terjadi");
			} else {
				int urutan;
	    		if (riwayatTransaksi.size() > 3) {
	    			urutan = riwayatTransaksi.size() -3;
	    		} else {
	    			urutan = 0;
	    		}
			
	    		for (int i = urutan; i < riwayatTransaksi.size(); i++) {
	                Transaksi transaksi = riwayatTransaksi.get(i);
	                transaksi.cetakDetail();
    		
    	}
			}
    		}
    	
    public String gantiPin(String pinLama, String pinBaru) {
        if (!this.pin.equals(pinLama)) {
            return "PIN lama salah!";
        }

        if (!pinValid(pinBaru)) {
            return "PIN baru harus 6 digit angka!";
        }

        if (this.pin.equals(pinBaru)) {
            return "PIN baru tidak boleh sama dengan PIN lama!";
        }

        this.pin = pinBaru;
        return "PIN berhasil diganti!";
    }

    private boolean pinValid(String pin) {
        return pin.matches("\\d{6}");
    }
    	
    	}
    
