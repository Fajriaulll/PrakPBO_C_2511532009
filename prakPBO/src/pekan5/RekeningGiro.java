package pekan5;

public class RekeningGiro extends Rekening { 
	private double batasOverdraft;
	
	public RekeningGiro (String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		// memanggil inisialisasi dasar dari superclass
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	
	// implementasi polimorfisme (overriding)
	@Override
	public boolean tarikTunai(double nominal) {
		if (nominal <= 0) {
			System.out.println("Gagal: Nominal tarik Giro harus lebih dari 0!");
			return false;
		}
		// Logika Khusus Giro: Boleh ditarik asalkan nominal tidak melebihi (saldo + batasOverdraft)
		else if (nominal > (saldo + batasOverdraft)) {
			System.out.println("Transaksi Gagal: Melebihi batas overdraft! Maksimal penarika: Rp" + (saldo + batasOverdraft));
			return false;
		} else {
			saldo -= nominal; // saldo bisa bernilai negatif disini (memakan overdraft)
			
			String idTrx = "TRX-G-" + System.currentTimeMillis();
			riwayatTransaksi.add(new Transaksi(idTrx, "Debit Giro", nominal));
			
			System.out.println("Tarik tunai Giro Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
			return true;
		}
	}
	
	// getter khusus giro
	public double getBatasOverdraft() {
		return batasOverdraft;
	}
	
	// Catatan : penarikan hingga limit overdraft akan kita selesaikan di modul 5

}
