package pekan4;

public class RekeningTabungan extends Rekening {
	
	// Atribut spesifik yang hanya dimiliki oleh Tabungan 
	private double sukuBunga;
	
	// Constructor Subclass
	public RekeningTabungan (String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
		// super() memanggil construktur kelas indung (rekening). WAJIB berada di baris pertama
		super(nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga = sukuBunga;
	}
	
	public void tambahBungaAkhirBulan() {
		// menghitung bunga
		// mengapa bisa mengakses saldo secara langsung dari class rekeningtabungan ?
		double nominalBunga = saldo * (sukuBunga / 100);
		saldo += nominalBunga;
		
		// mencatat riwayat transasksi
		String idTrx = "TRX-B-" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi(idTrx, "Bunga", nominalBunga));
		
		System.out.println("Bunga " + sukuBunga + "% berhasil ditambahkan: Rp" + nominalBunga);
	}
}
