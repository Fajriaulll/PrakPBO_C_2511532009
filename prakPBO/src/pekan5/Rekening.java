package pekan5;
import java.util.*;

public class Rekening {
    private String nomorRekening;
    private String namaPemilik;
    private String pin; // data sensitif
    
    // Gunakana progtected agar Subclass bisa mengaksesknya langsung
    protected double saldo;
    protected ArrayList<Transaksi> riwayatTransaksi;
    

    // 2. Modifikasi Constructor untuk menerima Pin awal: 
    public Rekening (String nomor, String nama, double saldoAwal, String pinAwal) {
        nomorRekening = nomor;
        namaPemilik = nama;
        saldo = saldoAwal;
        
        // validasi pin di dalam constructor
        if (pinAwal.length() == 6) {
        	this.pin = pinAwal;
        } else {
        	System.out.println("Peringatan : PIN harus 6 digit! Menggunakan PIN default 123456");
        	this.pin = "123456";
        }
        
        //wajib menginisialisasi arraylist didalam constructor agar tidak null pointer exception
        this.riwayatTransaksi = new ArrayList<>();
        
        if (saldoAwal > 50000) {
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
        	if (nominal > 500000) {
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
    
    public boolean tarikTunai(double nominal) {
    	if (nominal <= 0) {
    		System.out.println("Gagal : Nominal tarik harus lebih dari 0!");
    		return false;
    	} else if (nominal > saldo ) {
    		System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
    		return false;
    	} else {
    		saldo -= nominal;
    		
    		String idTrx = "TRX-T-" + System.currentTimeMillis();
    		riwayatTransaksi.add(new Transaksi(idTrx, "Debit", nominal));
    		
    		System.out.println("Tarik tunai Rp" + nominal + " berhasil. Sisa saldo: Rp" + saldo);
    		return true; // transaksi sukses
    	}
    }
    
    public void transfer(Rekening tujuan, double nominal, String pinInput) {
        // 1. Otentikasi dulu
        if (!otentikasi(pinInput)) {
            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah! Transfer dibatalkan.");
            return;
        }
 
        // Validasi tambahan (supaya uang tidak hilang di tengah jalan)
        if (tujuan == null) {
            System.out.println("Gagal: Rekening tujuan tidak valid!");
            return;
        }
        if (tujuan == this) {
            System.out.println("Gagal: Tidak bisa transfer ke rekening sendiri!");
            return;
        }
        // Setor ke tujuan punya batas (>= 10.000 dan < 500.000, sama seperti aturan setorTunai).
        // Dicek SEBELUM menarik, kalau tidak saldo pengirim berkurang tapi setoran ditolak.
        if (nominal < 10000 || nominal >= 500000) {
            System.out.println("Gagal: Nominal transfer harus minimal Rp10.000 dan kurang dari Rp500.000 (mengikuti batas setor rekening tujuan).");
            return;
        }
 
        // 2. Tarik dari rekening ini (dynamic dispatch: kalau this = Giro, aturan overdraft dipakai)
        boolean berhasilTarik = this.tarikTunai(nominal);
 
        // 3. Kalau penarikan sukses, setor ke tujuan
        if (berhasilTarik) {
            tujuan.setorTunai(nominal);
            System.out.println("Transfer Rp" + nominal + " dari " + this.nomorRekening
                    + " ke " + tujuan.getNomorRekening() + " (" + tujuan.getNamaPemilik() + ") berhasil.");
        } else {
            System.out.println("Transfer dibatalkan karena penarikan gagal.");
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
    
