package pekan4;

public class RekeningGiro extends Rekening { 
	private double batasOverdraft;
	
	public RekeningGiro (String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		// memanggil inisialisasi dasar dari superclass
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	
	// getter khusus giro
	public double getBatasOverdraft() {
		return batasOverdraft;
	}
	
	// Catatan : penarikan hingga limit overdraft akan kita selesaikan di modul 5

}
