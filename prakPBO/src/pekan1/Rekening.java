package pekan1;
import java.util.*;

public class Rekening {
    String nomorRekening;
    String namaPemilik;
    double saldo;

    public Rekening (String nomor, String nama, double saldoAwal) {
        nomorRekening = nomor;
        namaPemilik = nama;
        saldo = saldoAwal;
        
        if (saldoAwal >= 50000) {
            System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
        } else {
            System.out.println("Saldo tidak boleh kurang dari Rp.50.000");
        }
    }

    public void setorTunai(double nominal) {
        if (nominal >= 10000) {
            saldo += nominal;
            System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
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
    		} else {
    			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo anda: Rp." + saldo);
    		}
    		
    	} else {
    		System.out.println("Transaksi Gagal : Minimal nomonal penarikan 10.000");
    	}
    }
}