package bai_tap_game_doan_so.model;

public class Player {
	private String hoTen;
	private int solan=0;
	
	
	public Player(String hoTen, int solan) {
		this.hoTen = hoTen;
		this.solan = solan;
	}
	
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public int getSolan() {
		return solan;
	}
	public void setSolan(int solan) {
		this.solan = solan;
	}
	
}
